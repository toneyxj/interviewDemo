package com.xj.interview.inter;

/**
 * 界面展示
 */
public  interface  PageViewInter {
    /**
     * 创建
     */
    void  onPageCreate();

    /**
     * 准备
     */
    void onPageResume();

    /**
     * 停止
     */
    void onPageStop();

    /**
     * 销毁
     */
    void onPageDestroy();
}
