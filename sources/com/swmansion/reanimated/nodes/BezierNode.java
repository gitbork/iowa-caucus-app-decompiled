package com.swmansion.reanimated.nodes;

import android.graphics.PointF;
import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.NodesManager;

public class BezierNode extends Node {
    private final int mInputID;
    private final CubicBezierInterpolator mInterpolator;

    private static class CubicBezierInterpolator {

        /* renamed from: a */
        protected PointF f456a;

        /* renamed from: b */
        protected PointF f457b;

        /* renamed from: c */
        protected PointF f458c;
        protected PointF end;
        protected PointF start;

        public CubicBezierInterpolator(PointF pointF, PointF pointF2) {
            this.f456a = new PointF();
            this.f457b = new PointF();
            this.f458c = new PointF();
            this.start = pointF;
            this.end = pointF2;
        }

        public CubicBezierInterpolator(float f, float f2, float f3, float f4) {
            this(new PointF(f, f2), new PointF(f3, f4));
        }

        public float getInterpolation(float f) {
            return getBezierCoordinateY(getXForTime(f));
        }

        /* access modifiers changed from: protected */
        public float getBezierCoordinateY(float f) {
            this.f458c.y = this.start.y * 3.0f;
            this.f457b.y = ((this.end.y - this.start.y) * 3.0f) - this.f458c.y;
            this.f456a.y = (1.0f - this.f458c.y) - this.f457b.y;
            return f * (this.f458c.y + ((this.f457b.y + (this.f456a.y * f)) * f));
        }

        /* access modifiers changed from: protected */
        public float getXForTime(float f) {
            float f2 = f;
            for (int i = 1; i < 14; i++) {
                float bezierCoordinateX = getBezierCoordinateX(f2) - f;
                if (((double) Math.abs(bezierCoordinateX)) < 0.001d) {
                    break;
                }
                f2 -= bezierCoordinateX / getXDerivate(f2);
            }
            return f2;
        }

        private float getXDerivate(float f) {
            return this.f458c.x + (f * ((this.f457b.x * 2.0f) + (this.f456a.x * 3.0f * f)));
        }

        private float getBezierCoordinateX(float f) {
            this.f458c.x = this.start.x * 3.0f;
            this.f457b.x = ((this.end.x - this.start.x) * 3.0f) - this.f458c.x;
            this.f456a.x = (1.0f - this.f458c.x) - this.f457b.x;
            return f * (this.f458c.x + ((this.f457b.x + (this.f456a.x * f)) * f));
        }
    }

    public BezierNode(int i, ReadableMap readableMap, NodesManager nodesManager) {
        super(i, readableMap, nodesManager);
        this.mInputID = readableMap.getInt("input");
        this.mInterpolator = new CubicBezierInterpolator((float) readableMap.getDouble("mX1"), (float) readableMap.getDouble("mY1"), (float) readableMap.getDouble("mX2"), (float) readableMap.getDouble("mY2"));
    }

    /* access modifiers changed from: protected */
    public Double evaluate() {
        return Double.valueOf((double) this.mInterpolator.getInterpolation(((Double) this.mNodesManager.getNodeValue(this.mInputID)).floatValue()));
    }
}
