package com.xj.interview.main.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;

import com.xj.interview.R;
import com.xj.interview.utils.Applog;
import com.xj.interview.xjView.utils.DensityUtil;

/**
 * 显示进度比列--这里的数据保存
 */
public class ProportionView extends View {

    public ProportionView(Context context) {
        this(context, null);
    }

    public ProportionView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProportionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 圆弧与线条的倍数
     */
    private final int roundMultiple = 3;
    private float showValue;
    private float maxValue;
    private float percentage;
    private int paintWidth;//画笔宽度-绘制边框
    private int roundColor;//边框颜色
    private int progressColor;//进度颜色值
    private ValueAnimator va;//动画触发器

    //跟随改变文字颜色字体大小
    private int textColor;//文字颜色
    private int textSize;//文字大小
    //年份文字大小以及颜色
    private int yearColor;//文字颜色
    private int yearSize;//文字大小
    private String year;


    private void init(Context context, AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.ProportionView);
        paintWidth = (int) array.getDimension(R.styleable.ProportionView_paint_bound_width, 2);
        roundColor = (int) array.getColor(R.styleable.ProportionView_round_color, Color.BLACK);
        progressColor = (int) array.getColor(R.styleable.ProportionView_progress_color, Color.GRAY);
        textColor = (int) array.getColor(R.styleable.ProportionView_text_color, Color.GRAY);
        textSize = (int) array.getDimension(R.styleable.ProportionView_text_size, 10);
        yearColor = (int) array.getColor(R.styleable.ProportionView_year_color, Color.GRAY);
        yearSize = (int) array.getDimension(R.styleable.ProportionView_year_size, 10);
        array.recycle();

        if (textSize>yearSize){
            yearSize=textSize;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed){
            //开启动画
            if (showValue>0&&maxValue>0){
                startAnimation();
            }
        }
    }

    /**
     * 开始执行动画，自动运行动画
     */
    public void startAnimation() {
        if (va != null && va.isRunning()) {
            //如果刷新界面中设备正在动画直接取消动画
            va.cancel();
        }
        va = ValueAnimator.ofFloat(0, showValue);
        va.setDuration(800);
        va.setInterpolator(new AccelerateInterpolator());
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                percentage = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        va.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (maxValue == 0 || showValue == 0) return;
        int width = getWidth();
        int height = getHeight();
        if (width == 0 || height == 0) return;
        initMeasure();//分配界面基本数据
        //创建画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //这里绘制年份
        drawYearText(canvas, paint);
        //绘制跟随进度变化文字
        drawProgressText(canvas, paint);

        //绘制显示文字
        int textPadding = yearSize + 5;//文字与进度框的距离

        //绘制外边框
        drawRound(canvas, paint, textPadding);
        //下面开始绘制进度条
        drawProgress(canvas, paint, textPadding);
    }

    private int width, height, left, top;

    /**
     * 界面基本数据初始化
     */
    private void initMeasure() {
        width = getWidth() - getPaddingLeft() - getPaddingRight();
        height = getHeight() - getPaddingTop() - getPaddingBottom();
        left = getPaddingLeft();
        top = getPaddingTop();
    }

    /**
     * 绘制年度文字-绘制在中间
     *
     * @param canvas 画布
     * @param paint  画笔
     */
    private void drawYearText(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(yearColor);
        paint.setTextSize(yearSize);
        paint.setStrokeWidth(2);

        String value=year;
        Rect rect = getTextSize(value,yearSize);
        float drawX=(getWidth()-rect.width())/2;
        float drawY=rect.height();
        canvas.drawText(value, drawX, drawY, paint);
    }
    /**
     * 绘制进度文字-文字显示在界面的右侧
     *
     * @param canvas 画布
     * @param paint  画笔
     */
    private void drawProgressText(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(1);

        String value=String.valueOf(percentage);
        Rect rect = getTextSize(value,textSize);
        float drawX=getPaddingLeft()+width-rect.width()-paintWidth;
        float drawY=yearSize-textSize+rect.height();
        canvas.drawText(value, drawX, drawY, paint);
    }

    /**
     * 获取跟随显示的文字大小
     *
     * @return 显示文字的矩形框
     */
    private Rect getTextSize(String value,int size) {
        Paint paint = new Paint();
        paint.setTextSize(size);
        paint.setStrokeWidth(2);
        Rect rect = new Rect();
        paint.getTextBounds(value, 0, value.length(), rect);
        return rect;
    }

    /**
     * 绘制外边框
     *
     * @param canvas     画布
     * @param paint      画笔
     * @param paddingTop padding 顶部
     */
    private void drawRound(Canvas canvas, Paint paint, int paddingTop) {

        paint.setStrokeWidth(paintWidth);//设置画笔宽度
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(roundColor);
        //绘制显示进度外边框
        RectF rf = new RectF(paintWidth / 2 + left,
                paintWidth / 2 + paddingTop + top,
                width - paintWidth / 2,
                height - paintWidth / 2);
        //绘制边框
        canvas.drawRoundRect(rf, paintWidth * roundMultiple, paintWidth * roundMultiple, paint);
    }

    /**
     * 绘制进度参数
     *
     * @param canvas     画布
     * @param paint      画笔
     * @param paddingTop padding 顶部
     */
    private void drawProgress(Canvas canvas, Paint paint, int paddingTop) {
        paint.setColor(progressColor);
        paint.setStyle(Paint.Style.FILL);

        int interval = paintWidth * 2;//内部pading距离
        int totalW = width - interval * 2;

        float progressWidth = (percentage * totalW) / maxValue;
        //绘制框
        RectF pf = new RectF(left + interval,
                interval + paddingTop + top,
                interval + progressWidth,
                height - interval);
        //绘制内容框
        canvas.drawRoundRect(pf, paintWidth * roundMultiple, paintWidth * roundMultiple, paint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //销毁动画
        if (va != null && va.isRunning()) {
            va.cancel();
        }
    }

    //-----下面为参数设置项目

    /**
     * 外边框线宽
     * @param paintWidth
     * @return 线宽
     */
    public ProportionView setPaintWidth(int paintWidth) {
        this.paintWidth = DensityUtil.dip2px(getContext(),paintWidth);
        return this;
    }

    /**
     * 外边框颜色
     * @param roundColor 颜色
     */
    public ProportionView setRoundColor(@ColorRes int roundColor) {
        this.roundColor = getContext().getResources().getColor(roundColor);
        return this;
    }

    /**
     * @param progressColor 进度条颜色
     */
    public ProportionView setProgressColor(@ColorRes int progressColor) {
        this.progressColor = getContext().getResources().getColor( progressColor);
        return this;
    }

    /**
     * @param textColor 进度变化文字颜色
     */
    public ProportionView setTextColor(@ColorRes int textColor) {
        this.textColor =  getContext().getResources().getColor(textColor);
        return this;
    }

    /**
     * @param textSize 进度变化文字大小
     */
    public ProportionView setTextSize(int textSize) {
        this.textSize = DensityUtil.dip2px(getContext(),textSize);
        return this;
    }

    /**
     * @param yearColor 年度值颜色
     */
    public ProportionView setYearColor( @ColorRes int yearColor) {
        this.yearColor = getContext().getResources().getColor( yearColor);
        return this;
    }

    /**
     * @param yearSize 年度文字大小
     */
    public ProportionView setYearSize(int yearSize) {
        this.yearSize = DensityUtil.dip2px(getContext(),yearSize);
        return this;
    }
    /**
     * 设置年份
     * @param year 年份
     */
    public ProportionView setYear(String year) {
        this.year = year;
        return this;
    }

    /**
     * @param showValue
     * @param maxValue
     */
    public ProportionView setProportionValue(float showValue, float maxValue) {
        this.showValue = showValue;
        this.maxValue = maxValue;
        return this;
    }
    /**
     * 神奇界面重新绘制
     */
    public void BuilderView(){
        invalidate();
        startAnimation();
    }
}
