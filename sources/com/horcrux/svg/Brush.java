package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.ReactConstants;
import com.horcrux.svg.SVGLength.UnitType;

class Brush {
    private ReadableArray mColors;
    private Matrix mMatrix;
    private PatternView mPattern;
    private final SVGLength[] mPoints;
    private final BrushType mType;
    private boolean mUseContentObjectBoundingBoxUnits;
    private final boolean mUseObjectBoundingBox;
    private Rect mUserSpaceBoundingBox;

    enum BrushType {
        LINEAR_GRADIENT,
        RADIAL_GRADIENT,
        PATTERN
    }

    enum BrushUnits {
        OBJECT_BOUNDING_BOX,
        USER_SPACE_ON_USE
    }

    Brush(BrushType brushType, SVGLength[] sVGLengthArr, BrushUnits brushUnits) {
        this.mType = brushType;
        this.mPoints = sVGLengthArr;
        this.mUseObjectBoundingBox = brushUnits == BrushUnits.OBJECT_BOUNDING_BOX;
    }

    /* access modifiers changed from: 0000 */
    public void setContentUnits(BrushUnits brushUnits) {
        this.mUseContentObjectBoundingBoxUnits = brushUnits == BrushUnits.OBJECT_BOUNDING_BOX;
    }

    /* access modifiers changed from: 0000 */
    public void setPattern(PatternView patternView) {
        this.mPattern = patternView;
    }

    private static void parseGradientStops(ReadableArray readableArray, int i, float[] fArr, int[] iArr, float f) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 2;
            fArr[i2] = (float) readableArray.getDouble(i3);
            int i4 = readableArray.getInt(i3 + 1);
            iArr[i2] = (i4 & ViewCompat.MEASURED_SIZE_MASK) | (Math.round(((float) (i4 >>> 24)) * f) << 24);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setUserSpaceBoundingBox(Rect rect) {
        this.mUserSpaceBoundingBox = rect;
    }

    /* access modifiers changed from: 0000 */
    public void setGradientColors(ReadableArray readableArray) {
        this.mColors = readableArray;
    }

    /* access modifiers changed from: 0000 */
    public void setGradientTransform(Matrix matrix) {
        this.mMatrix = matrix;
    }

    private RectF getPaintRect(RectF rectF) {
        float f;
        if (!this.mUseObjectBoundingBox) {
            rectF = new RectF(this.mUserSpaceBoundingBox);
        }
        float width = rectF.width();
        float height = rectF.height();
        float f2 = 0.0f;
        if (this.mUseObjectBoundingBox) {
            f2 = rectF.left;
            f = rectF.top;
        } else {
            f = 0.0f;
        }
        return new RectF(f2, f, width + f2, height + f);
    }

    private double getVal(SVGLength sVGLength, double d, float f, float f2) {
        double d2;
        if (!this.mUseObjectBoundingBox) {
            SVGLength sVGLength2 = sVGLength;
        } else if (sVGLength.unit == UnitType.NUMBER) {
            d2 = d;
            return PropHelper.fromRelative(sVGLength, d, 0.0d, d2, (double) f2);
        }
        d2 = (double) f;
        return PropHelper.fromRelative(sVGLength, d, 0.0d, d2, (double) f2);
    }

    /* access modifiers changed from: 0000 */
    public void setupPaint(Paint paint, RectF rectF, float f, float f2) {
        float[] fArr;
        int[] iArr;
        Paint paint2 = paint;
        float f3 = f2;
        RectF paintRect = getPaintRect(rectF);
        float width = paintRect.width();
        float height = paintRect.height();
        float f4 = paintRect.left;
        float f5 = paintRect.top;
        float textSize = paint.getTextSize();
        if (this.mType == BrushType.PATTERN) {
            double d = (double) width;
            double val = getVal(this.mPoints[0], d, f, textSize);
            double d2 = (double) height;
            double d3 = d2;
            double d4 = val;
            double val2 = getVal(this.mPoints[1], d2, f, textSize);
            double d5 = d;
            double d6 = val2;
            double val3 = getVal(this.mPoints[2], d5, f, textSize);
            double d7 = val3;
            double val4 = getVal(this.mPoints[3], d3, f, textSize);
            if (d7 > 1.0d && val4 > 1.0d) {
                Bitmap createBitmap = Bitmap.createBitmap((int) d7, (int) val4, Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                RectF viewBox = this.mPattern.getViewBox();
                if (viewBox != null && viewBox.width() > 0.0f && viewBox.height() > 0.0f) {
                    canvas.concat(ViewBox.getTransform(viewBox, new RectF((float) d4, (float) d6, (float) d7, (float) val4), this.mPattern.mAlign, this.mPattern.mMeetOrSlice));
                }
                if (this.mUseContentObjectBoundingBoxUnits) {
                    canvas.scale(width / f, height / f);
                }
                this.mPattern.draw(canvas, new Paint(), f2);
                Matrix matrix = new Matrix();
                Matrix matrix2 = this.mMatrix;
                if (matrix2 != null) {
                    matrix.preConcat(matrix2);
                }
                BitmapShader bitmapShader = new BitmapShader(createBitmap, TileMode.REPEAT, TileMode.REPEAT);
                bitmapShader.setLocalMatrix(matrix);
                paint.setShader(bitmapShader);
            }
            return;
        }
        float f6 = f3;
        int size = this.mColors.size();
        String str = ReactConstants.TAG;
        if (size == 0) {
            FLog.m84w(str, "Gradient contains no stops");
            return;
        }
        int i = size / 2;
        int[] iArr2 = new int[i];
        float[] fArr2 = new float[i];
        parseGradientStops(this.mColors, i, fArr2, iArr2, f6);
        if (fArr2.length == 1) {
            int[] iArr3 = {iArr2[0], iArr2[0]};
            float[] fArr3 = {fArr2[0], fArr2[0]};
            FLog.m84w(str, "Gradient contains only one stop");
            iArr = iArr3;
            fArr = fArr3;
        } else {
            iArr = iArr2;
            fArr = fArr2;
        }
        if (this.mType == BrushType.LINEAR_GRADIENT) {
            double d8 = (double) width;
            double d9 = d8;
            double val5 = getVal(this.mPoints[0], d8, f, textSize);
            double d10 = (double) f4;
            Double.isNaN(d10);
            double d11 = val5 + d10;
            double d12 = (double) height;
            double d13 = d11;
            double d14 = d10;
            double val6 = getVal(this.mPoints[1], d12, f, textSize);
            double d15 = (double) f5;
            Double.isNaN(d15);
            double d16 = val6 + d15;
            double d17 = d16;
            double val7 = getVal(this.mPoints[2], d9, f, textSize);
            Double.isNaN(d14);
            double d18 = val7 + d14;
            double d19 = d12;
            double d20 = d18;
            double val8 = getVal(this.mPoints[3], d19, f, textSize);
            Double.isNaN(d15);
            LinearGradient linearGradient = new LinearGradient((float) d13, (float) d17, (float) d20, (float) (val8 + d15), iArr, fArr, TileMode.CLAMP);
            if (this.mMatrix != null) {
                Matrix matrix3 = new Matrix();
                matrix3.preConcat(this.mMatrix);
                linearGradient.setLocalMatrix(matrix3);
            }
            paint2.setShader(linearGradient);
        } else if (this.mType == BrushType.RADIAL_GRADIENT) {
            double d21 = (double) width;
            double val9 = getVal(this.mPoints[2], d21, f, textSize);
            double d22 = (double) height;
            int[] iArr4 = iArr;
            double d23 = val9;
            double val10 = getVal(this.mPoints[3], d22, f, textSize) / d23;
            double d24 = d21;
            double d25 = val10;
            double val11 = getVal(this.mPoints[4], d24, f, textSize);
            double d26 = (double) f4;
            Double.isNaN(d26);
            double d27 = val11 + d26;
            double d28 = d22;
            double d29 = d27;
            double val12 = getVal(this.mPoints[5], d28, f, textSize);
            double d30 = (double) f5;
            Double.isNaN(d30);
            RadialGradient radialGradient = new RadialGradient((float) d29, (float) (val12 + (d30 / d25)), (float) d23, iArr4, fArr, TileMode.CLAMP);
            Matrix matrix4 = new Matrix();
            matrix4.preScale(1.0f, (float) d25);
            Matrix matrix5 = this.mMatrix;
            if (matrix5 != null) {
                matrix4.preConcat(matrix5);
            }
            radialGradient.setLocalMatrix(matrix4);
            paint.setShader(radialGradient);
        }
    }
}
