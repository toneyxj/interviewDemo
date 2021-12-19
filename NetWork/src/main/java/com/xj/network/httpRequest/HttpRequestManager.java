package com.xj.network.httpRequest;

import android.os.Handler;
import android.os.Looper;

import com.xj.network.httpRequest.header.HeaderInterceptor;
import com.xj.network.httpRequest.helper.RxHelper;
import com.xj.network.httpRequest.inter.CheckUtil;
import com.xj.network.httpRequest.inter.HttpRequestImp;
import com.xj.network.httpRequest.inter.checkAddressIPListener;
import com.xj.network.httpRequest.service.CheckHttp;
import com.xj.network.httpRequest.service.CheckServer;
import com.xj.network.httpRequest.utils.NetLog;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * http请求管理类，里面包含项目的请求以及规范
 */
public class HttpRequestManager implements HttpRequestImp {
    private static HttpRequestImp install;

    public HttpRequestManager() {
    }

    public static HttpRequestImp getInstall() {
        if (install == null) {
            synchronized (HttpRequestManager.class) {
                if (install == null) {
                    install = new HttpRequestManager();
                }
            }
        }
        return install;
    }

    private String address = "https://data.gov.sg";
    private String token = "";
    private Retrofit mRetrofit;
    private CheckUtil checkUtil;
    private CheckServer checkServer;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setDebug(boolean debug) {
        NetLog.isDebug = debug;
    }

    public CheckServer getBookServer() {
        initRetrofit();
        if (checkServer == null) {
            checkServer = mRetrofit.create(CheckServer.class);
        }
        return checkServer;
    }

    private void initRetrofit() {
        mRetrofit = initRetrofit(address, mRetrofit);
    }

    private Retrofit initRetrofit(String IP, Retrofit retrofit) {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            if (getToken()!=null&&!getToken().equals("")) {
                builder.addInterceptor(new HeaderInterceptor());
            }
            if (getDebug()) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }
            retrofit = new Retrofit.Builder()
                    .baseUrl(IP)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    @Override
    public CheckUtil getCheckUtil() {
        if (checkUtil == null) {
            checkUtil = new CheckHttp();
        }
        return checkUtil;
    }


    @Override
    public void unSubscribeTask(String TAG) {
        RxHelper.getInstance().unSubscribeTask(TAG);
    }

    public boolean getDebug() {
        return NetLog.isDebug;
    }

    @Override
    public void checkAddressIP(final String ip, final int port, final int time, final checkAddressIPListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                try {
                    //创立连接，连接成功后进入下一行代码，否则停止，直到连接超时报错
                    socket = new Socket();
                    SocketAddress socAddress = new InetSocketAddress(ip, port);
                    socket.connect(socAddress, time);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onCheckSuccess(ip, port);
                        }
                    });
                } catch (IOException e) {
                    listener.onCheckFail(ip, port, e.getMessage());
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                    }
                }
            }
        }).start();
    }


}
