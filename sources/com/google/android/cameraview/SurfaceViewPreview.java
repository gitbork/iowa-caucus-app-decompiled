package com.google.android.cameraview;

import android.content.Context;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import org.reactnative.camera.C2436R;

class SurfaceViewPreview extends PreviewImpl {
    final SurfaceView mSurfaceView;

    /* access modifiers changed from: 0000 */
    public void setDisplayOrientation(int i) {
    }

    SurfaceViewPreview(Context context, ViewGroup viewGroup) {
        this.mSurfaceView = (SurfaceView) View.inflate(context, C2436R.layout.surface_view, viewGroup).findViewById(C2436R.C2438id.surface_view);
        SurfaceHolder holder = this.mSurfaceView.getHolder();
        holder.setType(3);
        holder.addCallback(new Callback() {
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
            }

            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                SurfaceViewPreview.this.setSize(i2, i3);
                if (!ViewCompat.isInLayout(SurfaceViewPreview.this.mSurfaceView)) {
                    SurfaceViewPreview.this.dispatchSurfaceChanged();
                }
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                SurfaceViewPreview.this.setSize(0, 0);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public Surface getSurface() {
        return getSurfaceHolder().getSurface();
    }

    /* access modifiers changed from: 0000 */
    public SurfaceHolder getSurfaceHolder() {
        return this.mSurfaceView.getHolder();
    }

    /* access modifiers changed from: 0000 */
    public View getView() {
        return this.mSurfaceView;
    }

    /* access modifiers changed from: 0000 */
    public Class getOutputClass() {
        return SurfaceHolder.class;
    }

    /* access modifiers changed from: 0000 */
    public boolean isReady() {
        return (getWidth() == 0 || getHeight() == 0) ? false : true;
    }
}
