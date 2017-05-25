package com.nbhope.hopelauncher.lib.network;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.nbhope.hopelauncher.lib.network.observer.Observer;
import com.nbhope.hopelauncher.lib.network.observer.Type;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @Description response Body 拦截器
 * Created by EthanCo on 2016/7/14.
 */
class BodyInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = null;
        try {
            Request request = chain.request();
            response = chain.proceed(request);

            ResponseBody responseBody = response.body();
            long contentLength = responseBody.contentLength();
            if (!HttpHeaders.hasBody(response)) {
                //END HTTP
            } else if (bodyEncoded(response.headers())) {
                //HTTP (encoded body omitted)
            } else {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    try {
                        charset = contentType.charset(UTF8);
                    } catch (UnsupportedCharsetException e) {
                        //Couldn't decode the response body; charset is likely malformed.
                        return response;
                    }
                }

                if (!isPlaintext(buffer)) {
                    //L.i("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)");
                    return response;
                }

                if (contentLength != 0) {
                    String result = buffer.clone().readString(charset);
                    handle(result);
                }

                //<-- END HTTP (" + buffer.size() + "-byte body)
            }
        } catch (Exception e) {
            Log.e(TAG, "BodyInterceptor:" + e.getMessage());
        }


        return response;
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }


    private static final String TAG = "BodyInterceptor";
    private List<Observer> observerList = new ArrayList<>();

    /**
     * 注册 响应观察者
     *
     * @param observer
     */
    public void register(Observer observer) {
        observerList.add(observer);
    }

    public void handle(String body) {
        Type type = filterError(body);
        notifyObservers(type);
    }

    private Type filterError(String body) {
        Type type = null;
        if (body.contains("无权操作")) {
            type = Type.NO_OPERATE;
        }
        return type;
    }

    public static final int TYPE_NOTIFY_OBSERVER = 8945;

    private static Handler mH = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == TYPE_NOTIFY_OBSERVER) {
                Observer observer = (Observer) msg.obj;
                Type type = Type.valueOf(msg.arg1);
                observer.notify(type);
            }
        }
    };

    private void notifyObservers(final Type type) {
        if (null != type) {
            new Thread() {
                @Override
                public void run() {
                    for (Observer observer : observerList) {
                        Message msg = mH.obtainMessage(TYPE_NOTIFY_OBSERVER, type.getValue(), 0, observer);
                        mH.sendMessage(msg);
                    }
                }
            }.start();
        }
    }
}
