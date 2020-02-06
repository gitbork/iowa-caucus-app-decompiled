package com.facebook.react.uimanager;

import android.widget.ImageView.ScaleType;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.events.TouchEventType;
import com.facebook.react.views.picker.events.PickerItemSelectEvent;
import java.util.HashMap;
import java.util.Map;

class UIManagerModuleConstants {
    public static final String ACTION_DISMISSED = "dismissed";
    public static final String ACTION_ITEM_SELECTED = "itemSelected";

    UIManagerModuleConstants() {
    }

    static Map getBubblingEventTypeConstants() {
        String str = "captured";
        String str2 = "bubbled";
        String str3 = "phasedRegistrationNames";
        String str4 = "topChange";
        return MapBuilder.builder().put(str4, MapBuilder.m119of(str3, MapBuilder.m120of(str2, "onChange", str, "onChangeCapture"))).put(PickerItemSelectEvent.EVENT_NAME, MapBuilder.m119of(str3, MapBuilder.m120of(str2, "onSelect", str, "onSelectCapture"))).put(TouchEventType.getJSEventName(TouchEventType.START), MapBuilder.m119of(str3, MapBuilder.m120of(str2, "onTouchStart", str, "onTouchStartCapture"))).put(TouchEventType.getJSEventName(TouchEventType.MOVE), MapBuilder.m119of(str3, MapBuilder.m120of(str2, "onTouchMove", str, "onTouchMoveCapture"))).put(TouchEventType.getJSEventName(TouchEventType.END), MapBuilder.m119of(str3, MapBuilder.m120of(str2, "onTouchEnd", str, "onTouchEndCapture"))).put(TouchEventType.getJSEventName(TouchEventType.CANCEL), MapBuilder.m119of(str3, MapBuilder.m120of(str2, "onTouchCancel", str, "onTouchCancelCapture"))).build();
    }

    static Map getDirectEventTypeConstants() {
        String str = "registrationName";
        String str2 = "topLayout";
        String str3 = "topLoadingError";
        String str4 = "topLoadingFinish";
        String str5 = "topLoadingStart";
        String str6 = "topSelectionChange";
        String str7 = "topMessage";
        String str8 = "topClick";
        String str9 = "topScrollBeginDrag";
        String str10 = "topScrollEndDrag";
        String str11 = "topScroll";
        String str12 = "topMomentumScrollBegin";
        return MapBuilder.builder().put("topContentSizeChange", MapBuilder.m119of(str, "onContentSizeChange")).put(str2, MapBuilder.m119of(str, ViewProps.ON_LAYOUT)).put(str3, MapBuilder.m119of(str, "onLoadingError")).put(str4, MapBuilder.m119of(str, "onLoadingFinish")).put(str5, MapBuilder.m119of(str, "onLoadingStart")).put(str6, MapBuilder.m119of(str, "onSelectionChange")).put(str7, MapBuilder.m119of(str, "onMessage")).put(str8, MapBuilder.m119of(str, "onClick")).put(str9, MapBuilder.m119of(str, "onScrollBeginDrag")).put(str10, MapBuilder.m119of(str, "onScrollEndDrag")).put(str11, MapBuilder.m119of(str, "onScroll")).put(str12, MapBuilder.m119of(str, "onMomentumScrollBegin")).put("topMomentumScrollEnd", MapBuilder.m119of(str, "onMomentumScrollEnd")).build();
    }

    public static Map<String, Object> getConstants() {
        HashMap newHashMap = MapBuilder.newHashMap();
        newHashMap.put("UIView", MapBuilder.m119of("ContentMode", MapBuilder.m121of("ScaleAspectFit", Integer.valueOf(ScaleType.FIT_CENTER.ordinal()), "ScaleAspectFill", Integer.valueOf(ScaleType.CENTER_CROP.ordinal()), "ScaleAspectCenter", Integer.valueOf(ScaleType.CENTER_INSIDE.ordinal()))));
        newHashMap.put("StyleConstants", MapBuilder.m119of("PointerEventsValues", MapBuilder.m122of(ViewProps.NONE, Integer.valueOf(PointerEvents.NONE.ordinal()), "boxNone", Integer.valueOf(PointerEvents.BOX_NONE.ordinal()), "boxOnly", Integer.valueOf(PointerEvents.BOX_ONLY.ordinal()), "unspecified", Integer.valueOf(PointerEvents.AUTO.ordinal()))));
        String str = ACTION_ITEM_SELECTED;
        String str2 = ACTION_DISMISSED;
        newHashMap.put("PopupMenu", MapBuilder.m120of(str2, str2, str, str));
        newHashMap.put("AccessibilityEventTypes", MapBuilder.m121of("typeWindowStateChanged", Integer.valueOf(32), "typeViewFocused", Integer.valueOf(8), "typeViewClicked", Integer.valueOf(1)));
        return newHashMap;
    }
}
