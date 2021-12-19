package com.xj.interview;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xj.interview.inter.BaseViewInter;
import com.xj.interview.inter.PageViewInter;
import com.xj.interview.utils.Applog;
import com.xj.network.httpRequest.HttpRequestManager;

public abstract class BaseActivity extends AppCompatActivity implements BaseViewInter {
    public abstract String getHttpTAG();

    @Override
    public void showToast(String value){
        Toast.makeText(this,value,Toast.LENGTH_SHORT).show();
    }

    private PageViewInter viewInter;

    public void setViewInter(PageViewInter viewInter) {
        this.viewInter = viewInter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onMeCreate(savedInstanceState);
        if (viewInter!=null)
        viewInter.onPageCreate();
    }
    protected abstract void onMeCreate(@Nullable Bundle savedInstanceState);

    @Override
    public void onResume() {
        super.onResume();
        if (viewInter!=null)
        viewInter.onPageResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (viewInter!=null)
        viewInter.onPageStop();
    }
    @Override
    public Context getContext() {
        return this;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Applog.e("进入销毁的程序");
        if (viewInter!=null) {
            viewInter.onPageDestroy();
        }
        viewInter=null;
        HttpRequestManager.getInstall().unSubscribeTask(getHttpTAG());
    }
}
