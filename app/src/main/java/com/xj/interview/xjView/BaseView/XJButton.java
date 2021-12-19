package com.xj.interview.xjView.BaseView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by xj on 2018/9/13.
 */

@SuppressLint("AppCompatCustomView")
public class XJButton extends Button {
    public XJButton(Context context) {
        super(context);
    }

    public XJButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XJButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
