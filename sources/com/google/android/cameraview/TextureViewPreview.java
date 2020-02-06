package com.google.android.cameraview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.ViewGroup;
import org.reactnative.camera.C2436R;

@TargetApi(14)
class TextureViewPreview extends PreviewImpl {
    private int mDisplayOrientation;
    private final TextureView mTextureView;

    TextureViewPreview(Context context, ViewGroup viewGroup) {
        this.mTextureView = (TextureView) View.inflate(context, C2436R.layout.texture_view, viewGroup).findViewById(C2436R.C2438id.texture_view);
        this.mTextureView.setSurfaceTextureListener(new SurfaceTextureListener() {
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }

            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                TextureViewPreview.this.setSize(i, i2);
                TextureViewPreview.this.configureTransform();
                TextureViewPreview.this.dispatchSurfaceChanged();
            }

            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                TextureViewPreview.this.setSize(i, i2);
                TextureViewPreview.this.configureTransform();
                TextureViewPreview.this.dispatchSurfaceChanged();
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                TextureViewPreview.this.setSize(0, 0);
                TextureViewPreview.this.dispatchSurfaceDestroyed();
                return true;
            }
        });
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(15)
    public void setBufferSize(int i, int i2) {
        this.mTextureView.getSurfaceTexture().setDefaultBufferSize(i, i2);
    }

    /* access modifiers changed from: 0000 */
    public Surface getSurface() {
        return new Surface(this.mTextureView.getSurfaceTexture());
    }

    /* access modifiers changed from: 0000 */
    public SurfaceTexture getSurfaceTexture() {
        return this.mTextureView.getSurfaceTexture();
    }

    /* access modifiers changed from: 0000 */
    public View getView() {
        return this.mTextureView;
    }

    /* access modifiers changed from: 0000 */
    public Class getOutputClass() {
        return SurfaceTexture.class;
    }

    /* access modifiers changed from: 0000 */
    public void setDisplayOrientation(int i) {
        this.mDisplayOrientation = i;
        configureTransform();
    }

    /* access modifiers changed from: 0000 */
    public boolean isReady() {
        return this.mTextureView.getSurfaceTexture() != null;
    }

    /* access modifiers changed from: 0000 */
    public void configureTransform() {
        Matrix matrix = new Matrix();
        int i = this.mDisplayOrientation;
        if (i % 180 == 90) {
            float width = (float) getWidth();
            float height = (float) getHeight();
            matrix.setPolyToPoly(new float[]{0.0f, 0.0f, width, 0.0f, 0.0f, height, width, height}, 0, this.mDisplayOrientation == 90 ? new float[]{0.0f, height, 0.0f, 0.0f, width, height, width, 0.0f} : new float[]{width, 0.0f, width, height, 0.0f, 0.0f, 0.0f, height}, 0, 4);
        } else if (i == 180) {
            matrix.postRotate(180.0f, (float) (getWidth() / 2), (float) (getHeight() / 2));
        }
        this.mTextureView.setTransform(matrix);
    }
}
