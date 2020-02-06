package androidx.browser.browseractions;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.browser.C0127R;
import com.google.common.primitives.Ints;

@RestrictTo({Scope.LIBRARY_GROUP})
public class BrowserActionsFallbackMenuView extends LinearLayout {
    private final int mBrowserActionsMenuMaxWidthPx = getResources().getDimensionPixelOffset(C0127R.dimen.browser_actions_context_menu_max_width);
    private final int mBrowserActionsMenuMinPaddingPx = getResources().getDimensionPixelOffset(C0127R.dimen.browser_actions_context_menu_min_padding);

    public BrowserActionsFallbackMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(Math.min(getResources().getDisplayMetrics().widthPixels - (this.mBrowserActionsMenuMinPaddingPx * 2), this.mBrowserActionsMenuMaxWidthPx), Ints.MAX_POWER_OF_TWO), i2);
    }
}
