package com.xj.network.httpRequest.helper;

import com.google.gson.JsonSyntaxException;
import com.xj.network.httpRequest.model.ResponseNoData;
import com.xj.network.httpRequest.model.TResponse;
import com.xj.network.httpRequest.utils.NetLog;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Calm on 2018/1/30.
 * RxHelper Rx单例类
 */

public class RxHelper {
    private HashMap<String, CompositeDisposable> mTaskDisposable = new HashMap<>();

    private static class RxHelperHolder {
        private static RxHelper instance = new RxHelper();
    }

    private RxHelper() {

    }

    public static RxHelper getInstance() {
        return RxHelperHolder.instance;
    }

    private void addTaskDisposable(String tag, Disposable disposable) {
        if (mTaskDisposable.get(tag) != null) {
            mTaskDisposable.get(tag).add(disposable);
        } else {
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(disposable);
            mTaskDisposable.put(tag, compositeDisposable);
        }
    }

    public <T> void sendRequest(String tag, Observable<TResponse<T>> observable, Consumer<TResponse<T>> onNext, Consumer<Throwable> onError) {
        Disposable disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError);
        addTaskDisposable(tag, disposable);
    }

    public void sendRequestNoData(String tag, Observable<ResponseNoData> observable, Consumer<ResponseNoData> onNext, Consumer<Throwable> onError) {
        Disposable disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError);
        addTaskDisposable(tag, disposable);
    }

    public void unSubscribeTask(String tag) {
        if (mTaskDisposable.get(tag) != null) {
            mTaskDisposable.get(tag).dispose();
            mTaskDisposable.remove(tag);
        }
    }

    public void unSubscribeTask(String tag, Disposable d) {
        if (mTaskDisposable.get(tag) != null) {
            mTaskDisposable.get(tag).remove(d);
            if (mTaskDisposable.get(tag).size() <= 0) {
                mTaskDisposable.remove(tag);
            }
        }
    }

    public String getErrorInfo(Throwable throwable) {

        NetLog.e(throwable.getMessage());
        String errorInfo;
        if (throwable instanceof SocketTimeoutException) {
            errorInfo = "请求超时，请重试";
        } else if (throwable instanceof UnknownHostException) {
            errorInfo = "当前网络异常，请检查网络后重试";
        } else if (throwable instanceof IOException) {
            errorInfo = "服务器掉线啦，工程师正在抓紧处理...";
        } else if (throwable instanceof JsonSyntaxException) {
            errorInfo = "服务端返回数据格式异常";
        } else if (throwable instanceof IllegalArgumentException) {
            errorInfo = "服务器Ip地址未设置";
        } else {
            errorInfo = "请求失败:"+throwable.getMessage();
        }
        return errorInfo;
    }
//    private String codeToMsg(int code) {
//        switch (code) {
//            case 402:
//                return App.getContext().getResources().getString(R.string._code_msg_account_pwd);
//            case 410:
//                return App.getContext().getResources().getString(R.string._code_msg_410);
//            case 411:
//                return App.getContext().getResources().getString(R.string._code_msg_411);
//            case 412:
//                return App.getContext().getString(R.string._code_msg_412);
//            case 413:
//                return App.getContext().getString(R.string._code_msg_413);
//            default:
//                return App.getContext().getResources().getString(R.string._code_msg_unknown);
//        }
//    }
}
