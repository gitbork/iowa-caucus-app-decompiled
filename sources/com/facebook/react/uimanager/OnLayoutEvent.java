package com.facebook.react.uimanager;

import androidx.core.util.Pools.SynchronizedPool;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.TouchesHelper;

public class OnLayoutEvent extends Event<OnLayoutEvent> {
    private static final SynchronizedPool<OnLayoutEvent> EVENTS_POOL = new SynchronizedPool<>(20);
    private int mHeight;
    private int mWidth;

    /* renamed from: mX */
    private int f54mX;

    /* renamed from: mY */
    private int f55mY;

    public String getEventName() {
        return "topLayout";
    }

    public static OnLayoutEvent obtain(int i, int i2, int i3, int i4, int i5) {
        OnLayoutEvent onLayoutEvent = (OnLayoutEvent) EVENTS_POOL.acquire();
        if (onLayoutEvent == null) {
            onLayoutEvent = new OnLayoutEvent();
        }
        onLayoutEvent.init(i, i2, i3, i4, i5);
        return onLayoutEvent;
    }

    public void onDispose() {
        EVENTS_POOL.release(this);
    }

    private OnLayoutEvent() {
    }

    /* access modifiers changed from: protected */
    public void init(int i, int i2, int i3, int i4, int i5) {
        super.init(i);
        this.f54mX = i2;
        this.f55mY = i3;
        this.mWidth = i4;
        this.mHeight = i5;
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("x", (double) PixelUtil.toDIPFromPixel((float) this.f54mX));
        createMap.putDouble("y", (double) PixelUtil.toDIPFromPixel((float) this.f55mY));
        createMap.putDouble("width", (double) PixelUtil.toDIPFromPixel((float) this.mWidth));
        createMap.putDouble("height", (double) PixelUtil.toDIPFromPixel((float) this.mHeight));
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putMap("layout", createMap);
        createMap2.putInt(TouchesHelper.TARGET_KEY, getViewTag());
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), createMap2);
    }
}
