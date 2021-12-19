package com.xj.interview.xjView.BaseView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by xj on 2018/9/13.
 */

@SuppressLint("AppCompatCustomView")
public class XJTextView extends TextView {
    public XJTextView(Context context) {
        super(context);
    }

    public XJTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XJTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTextV(CharSequence text) {
        if (null == text) return;
        setText(text);
    }
}
