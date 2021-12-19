package com.xj.interview.main;

import androidx.annotation.Nullable;

import android.animation.TimeAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xj.interview.BaseActivity;
import com.xj.interview.Model.ShowModel;
import com.xj.interview.R;
import com.xj.interview.main.view.ProportionView;
import com.xj.interview.main.viewImp.MainPageViewImp;
import com.xj.interview.xjView.utils.DensityUtil;
import com.xj.network.httpRequest.model.check.Record;

import java.util.List;

public class MainActivity extends BaseActivity implements MainPageViewImp {

    private MainPageDataImp dataImp;
    private LinearLayout add_view;
    @Override
    protected void onMeCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        dataImp =new MainPageDataImp(this);
        add_view=findViewById(R.id.add_view);
        setViewInter(dataImp);
    }

    @Override
    public String getHttpTAG() {
        return "MainActivity-http";
    }

    @Override
    public void showRecord(float maxValue,List<ShowModel> records) {
    //列表展示这里选择使用动态加载以展示不同的显示要求
        add_view.removeAllViews();
        for (ShowModel record : records) {
            ProportionView view=new ProportionView(getContext());
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(getContext(),42));
            params.setMargins(0,20,0,0);
            view.setLayoutParams(params);
            view.setPaintWidth(1)
                    .setProgressColor(R.color.purple_200)
                    .setRoundColor(R.color.purple_500)
                    .setTextColor(R.color.purple_200)
                    .setTextSize(12)
                    .setYearColor(R.color.purple_700)
                    .setYearSize(18)
                    .setProportionValue(record.getData(),maxValue)
                    .setYear(String.valueOf(record.getYear())).BuilderView();
            //动态添加布局-这里适合显示条数比较固定的情况
            add_view.addView(view);
        }
    }
    public void photo(View v){
showToast("点击图片统一效果");
    }
}