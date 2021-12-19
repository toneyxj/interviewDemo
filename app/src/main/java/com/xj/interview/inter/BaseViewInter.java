package com.xj.interview.inter;

import android.content.Context;

/**
 * 界面基本控制接口
 */
public interface BaseViewInter {
    /**
     * 显示提示
     * @param toast 提示内容
     */
    void showToast(String toast);

    /**
     * 退出
     */
    void onBackPressed();

    /**
     * 获取上下文
     * @return
     */
    Context getContext();
}
