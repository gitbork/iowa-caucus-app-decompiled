package com.facebook.react.views.unimplementedview;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import androidx.appcompat.widget.AppCompatTextView;

public class ReactUnimplementedView extends LinearLayout {
    private AppCompatTextView mTextView;

    public ReactUnimplementedView(Context context) {
        super(context);
        this.mTextView = new AppCompatTextView(context);
        this.mTextView.setLayoutParams(new LayoutParams(-2, -1));
        this.mTextView.setGravity(17);
        this.mTextView.setTextColor(-1);
        setBackgroundColor(1442775040);
        setGravity(1);
        setOrientation(1);
        addView(this.mTextView);
    }

    public void setName(String str) {
        AppCompatTextView appCompatTextView = this.mTextView;
        StringBuilder sb = new StringBuilder();
        sb.append("'");
        sb.append(str);
        sb.append("' is not Fabric compatible yet.");
        appCompatTextView.setText(sb.toString());
    }
}
