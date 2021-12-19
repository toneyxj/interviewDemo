package com.xj.interview.xjView.BaseView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by xj on 2018/9/13.
 */

@SuppressLint("AppCompatCustomView")
public class XJEditeView extends EditText {
    public XJEditeView(Context context) {
        super(context);
    }

    public XJEditeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XJEditeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setTextV(CharSequence text) {
        if (null == text) return;
        setText(text);

    }
    public void setHintV(CharSequence tex){
        setHint(tex);
    }

}
