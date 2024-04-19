package com.smart.pangu_ui_lib.util;


import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;


import java.io.File;

import static android.content.Context.MODE_PRIVATE;

/**
 * 本类的主要功能是 :  文件夹工具类
 *
 * 存储分为:
 *     1,手机内部存储(系统级)-->
 *                     {@link #getAppIntenalPublicDir(Context, String)} e.g. /data/user/0/{包名}/app_/{subPath}
 *                     {@link #getAppIntenalFilesDir(Context, String)}  e.g. /data/user/0/{包名}/files/{subPath}
 *                     {@link #getAppIntenalCacheDir(Context)}          e.g. /data/user/0/{包名}/cache
 *
 *     2,手机外部存储(自带存储或SD)-->
 *                     (1) app外,公共存储,可以被外部识别,卸载App不会被清除
 *                     {@link #getAppExternalPublicDir(String)}         e.g. /storage/emulated/0/{@link #EXTERNAL_PUBLIC_ROOT_DIR}/{subPath}
 *
 *                     (2) app内,本app专属目录,不可以被外部识别,卸载app会被清除
 *                     {@link #getAppExternalFilesDir(Context, String)} e.g. /storage/emulated/0/Android/data/{包名}/files/{subPath}
 *                     {@link #getAppExternalCacheDir(Context)}         e.g. /storage/emulated/0/Android/data/{包名}/cache
 *
 *
 *  @author jiang_zheng_yan  2024/4/12 23:18
 */
public class FilePathUtil {

    // json 文件
    public static final String JSON_FILE_DIR = "json-file";
    //上传图片文件夹名
    public static final String UPLOAD_IMAGE_DIR = "image-upload";
    //图片文件夹名
    public static final String IMAGE_DIR = "image";
    //保存图书文件夹名
    public static final String BOOK_DIR = "book";
    //保存Audio文件夹名
    public static final String AUDIO_DIR= "audio";
    // apk保存目录
    public static final String APK_DIR = "apk";
    // 文件根目录
    private static final String EXTERNAL_PUBLIC_ROOT_DIR = "pangu-lib";


    private static final FilePathUtil ourInstance = new FilePathUtil();

    private FilePathUtil() {
    }

    public static FilePathUtil getInstance() {
        return ourInstance;
    }


    /*******内部隐私存储**********************************************************************************/

    /**
     * 内部隐私存储缓存 /data/user/0/{包名}/cache
     *内部隐私存储
     * @param context
     * @return
     */
    public static String getAppIntenalCacheDir(Context context) {
        if (context == null) {
            return null;
        }
        //内部隐私存储缓存 /data/user/0/com.jingkai.lawapp/cache
        File dir = context.getCacheDir();
        if (dir == null) {
            return null;
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getAbsolutePath();
    }

    /**
     *  //内部隐私存储缓存 /data/user/0/{包名}/files/{subPath}
     *  内部隐私存储
     * @param context
     * @param subPath
     * @return
     */

    public static String getAppIntenalFilesDir(Context context, String subPath) {
        if (TextUtils.isEmpty(subPath)) {
            subPath = "";
        }
        if (context == null) {
            return null;
        }
        //内部隐私存储缓存 /data/user/0/com.jingkai.lawapp/files
        File dir = context.getFilesDir();
        if (dir == null) {
            return null;
        }
        File file = new File(dir, subPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * //内部隐私存储缓存 /data/user/0/{包名}/app_/{subPath}
     *内部隐私存储
     * @param context
     * @param subPath
     * @return
     */
    public static String getAppIntenalPublicDir(Context context, String subPath) {
        if (TextUtils.isEmpty(subPath)) {
            subPath = "";
        }
        if (context == null) {
            return null;
        }
        //内部隐私存储缓存 /data/user/0/com.jingkai.lawapp/app_
        File dir = context.getDir("", MODE_PRIVATE);
        if (dir == null) {
            return null;
        }
        File file = new File(dir, subPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }


    /******获取外部存储公共(根目录)***********************************************************************************/

    /**
     * 获取外部存储根目录或根目录自定义文件夹 /storage/emulated/0/{@link #EXTERNAL_PUBLIC_ROOT_DIR}/{subPath}
     *
     * @param subPath 自定义文件夹 可以为空 ,   abc/cde
     * @return
     */
    public static String getAppExternalPublicDir(String subPath) {
        if (TextUtils.isEmpty(subPath)) {
            subPath = "";
        }
        File dir = Environment.getExternalStoragePublicDirectory(EXTERNAL_PUBLIC_ROOT_DIR+"/"+subPath);
        if (dir == null) {
            return null;
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getAbsolutePath();
    }


    /******获取app外部存储(卸载app可以被清空掉)***********************************************************************************/
    /**
     * //卸载app可以被清空掉
     * //获取app外部存储专属路径 /storage/emulated/0/Android/data/{包名}/cache
     *
     * @param context
     * @return
     */
    public static String getAppExternalCacheDir(Context context) {
        if (context == null) {
            return null;
        }
        //获取app外部存储专属路径 /storage/emulated/0/Android/data/{包名}/cache
        File dir = context.getExternalCacheDir();
        if (dir == null) {
            return null;
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getAbsolutePath();
    }

    /**
     * //卸载app可以被清空掉
     * //获取app外部存储专属路径 /storage/emulated/0/Android/data/{包名}/files/{subPath}
     *
     * @param context
     * @param subPath 扩展路径 可以为 ""   abc/cde
     * @return
     */
    public static String getAppExternalFilesDir(Context context, String subPath) {
        if (TextUtils.isEmpty(subPath)) {
            subPath = "";
        }
        if (context == null) {
            return null;
        }
        //获取app外部存储专属路径 /storage/emulated/0/Android/data/{包名}/files/abcd
        File dir = context.getExternalFilesDir(subPath);
        if (dir == null) {
            return null;
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getAbsolutePath();
    }

}
