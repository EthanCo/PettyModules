package com.lib.utils.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.IntRange;
import android.widget.Toast;

import java.io.File;
import java.io.Serializable;

import static com.lib.utils.io.FileUtil.openFile;

/**
 * @Description 操作 工具类
 * Created by EthanCo on 2016/10/10.
 */

public class OperateUtil {

    /**
     * Don't let anyone instantiate this class.
     */
    public OperateUtil() {
        throw new Error("Do not need instantiate!");
    }

    private static long lastClickTime;

    /**
     * 判断是否为多次点击 (可用作频率控制)
     * 500毫秒内按钮无效，可自己调整频率
     *
     * @return boolean
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 600) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static boolean isFastDoubleClick(int duration) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < duration) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 调用系统发短信界面
     *
     * @param activity    Activity
     * @param phoneNumber 手机号码
     * @param smsContent  短信内容
     */
    public static void sendMessage(Context activity, String phoneNumber, String smsContent) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return;
        }
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", smsContent);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(it);
    }


    /**
     * 拍照打开照相机！
     *
     * @param requestcode 返回值
     * @param activity    上下文
     * @param fileName    生成的图片文件的路径
     */
    public static void toTakePhoto(int requestcode, Activity activity, String fileName) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("camerasensortype", 2);// 调用前置摄像头
        intent.putExtra("autofocus", true);// 自动对焦
        intent.putExtra("fullScreen", false);// 全屏
        intent.putExtra("showActionIcons", false);
        try {//创建一个当前任务id的文件然后里面存放任务的照片的和路径！这主文件的名字是用uuid到时候在用任务id去查路径！
            File file = new File(fileName);
            Uri uri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            activity.startActivityForResult(intent, requestcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 打开相册
     *
     * @param requestcode 响应码
     * @param activity    上下文
     */
    public static void toTakePicture(int requestcode, Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        activity.startActivityForResult(intent, requestcode);
    }

    /**
     * @param context 上下文
     * @param file    文件对象
     */
    public static void openMedia(Context context, File file) {

        if (file.getName().endsWith(".png") ||
                file.getName().endsWith(".jpg") ||
                file.getName().endsWith(".jpeg")) {
            viewPhoto(context, file);
        } else {
            openFile(context, file);
        }
    }


    /**
     * 打开多媒体文件.
     *
     * @param context 上下文
     * @param file    多媒体文件
     */
    public static void viewPhoto(Context context, String file) {

        viewPhoto(context, new File(file));
    }


    /**
     * 打开照片
     *
     * @param context 上下文
     * @param file    文件对象
     */
    public static void viewPhoto(Context context, File file) {

        try {
            // 调用系统程序打开文件.
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file), "image/*");
            context.startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(context, "打开失败.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 打开多媒体文件.
     *
     * @param context 上下文
     * @param file    多媒体文件
     */
    public static void playSound(Context context, String file) {

        playSound(context, new File(file));
    }


    /**
     * 打开多媒体文件.
     *
     * @param context 上下文
     * @param file    多媒体文件
     */
    public static void playSound(Context context, File file) {

        try {
            // 调用系统程序打开文件.
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //			intent.setClassName("com.android.music", "com.android.music.MediaPlaybackActivity");
            intent.setDataAndType(Uri.fromFile(file), "audio/*");
            context.startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(context, "打开失败.", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 打开视频文件.
     *
     * @param context 上下文
     * @param file    视频文件
     */
    public static void playVideo(Context context, String file) {

        playVideo(context, new File(file));
    }


    /**
     * 打开视频文件.
     *
     * @param context 上下文
     * @param file    视频文件
     */
    public static void playVideo(Context context, File file) {
        try {
            // 调用系统程序打开文件.
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file), "video/*");
            context.startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(context, "打开失败.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 打开已安装的应用
     *
     * @param context
     * @param packageName 包名
     */
    public static void startExtraApp(Context context, String packageName) throws Exception {
        try {
            Intent LaunchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
            context.startActivity(LaunchIntent);
        } catch (Exception e) {
            throw e;
        }
    }


    /* ************************************************** startActivity ******************************************************* */

    /**
     * startActivity
     *
     * @param context
     * @param cls     目标Activity class
     */
    public static void startAty(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        if (!(context instanceof Activity))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * startActivity
     *
     * @param context
     * @param cls     目标Activity class
     * @param params  可变参数 (String)，用作掺入Bundle的键值对，奇数为key，偶数为value
     */
    public static void startAty(Context context, Class<?> cls, String... params) {
        Intent intent = new Intent(context, cls);
        if (!(context instanceof Activity))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (null != params) {
            for (int i = 0; i <= params.length / 2; i = i + 2) {
                intent.putExtra(params[i], params[i + 1]);
            }
        }
        context.startActivity(intent);
    }

    /**
     * startActivity
     *
     * @param context
     * @param cls     目标Activity class
     * @param params  可变参数 (Object)，用作掺入Bundle的键值对，奇数为key，偶数为value
     */
    public static void startAty(Context context, Class<?> cls, Object... params) {
        Intent intent = new Intent(context, cls);
        if (!(context instanceof Activity))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (null != params) {
            for (int i = 0; i <= params.length / 2; i = i + 2) {
                String key = params[i].toString();
                Object value = params[i + 1];
                if (value instanceof Parcelable) {
                    intent.putExtra(key, (Parcelable) value);
                } else if (value instanceof Serializable) {
                    intent.putExtra(key, (Serializable) value);
                } else if (value instanceof Bundle) {
                    intent.putExtra(key, (Bundle) value);
                } else if (value instanceof Integer) {
                    intent.putExtra(key, (Integer) value);
                } else if (value instanceof String) {
                    intent.putExtra(key, (String) value);
                } else {
                    throw new IllegalArgumentException("参数错误");
                }
            }
        }
        context.startActivity(intent);
    }
    /* ************************************************** startActivity End ******************************************************* */

    /* ************************************************** 安装卸载APK ******************************************************* */

    /**
     * 安装apk
     *
     * @param context 上下文
     * @param file    APK文件
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }


    /**
     * 安装apk
     *
     * @param context 上下文
     * @param file    APK文件uri
     */
    public static void installApk(Context context, Uri file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(file, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }


    /**
     * 卸载apk
     *
     * @param context     上下文
     * @param packageName 包名
     */
    public static void uninstallApk(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        Uri packageURI = Uri.parse("package:" + packageName);
        intent.setData(packageURI);
        context.startActivity(intent);
    }
    /* ************************************************** 安装卸载APK End ******************************************************* */


     /* ************************************************** 操作声音 ******************************************************* */

    /**
     * 增加声音
     *
     * @param context
     */
    public static void incVolume(Context context) {
        AudioManager audioManager = ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE));
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
    }

    /**
     * 降低声音
     *
     * @param context
     */
    public static void decVolume(Context context) {
        AudioManager audioManager = ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE));
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
    }

    /**
     * 设置声音
     *
     * @param context
     * @param volume  音量 0-15
     */
    public static void setVolume(Context context, @IntRange(from = 0, to = 15) int volume) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FLAG_SHOW_UI);
    }

    public static int getVolume(Context context) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    /* ************************************************** 操作声音 End ******************************************************* */
}
