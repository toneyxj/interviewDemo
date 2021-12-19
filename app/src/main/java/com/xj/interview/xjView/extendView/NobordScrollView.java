package com.xj.interview.xjView.extendView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class NobordScrollView extends HorizontalScrollView {


    public NobordScrollView(Context context) {
        super(context);
    }

    public NobordScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NobordScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NobordScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, // 设计一个较大的值和AT_MOST模式
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);//再调用原方法测量
    }
}
