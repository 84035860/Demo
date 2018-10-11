package com.example.hspcadmin.htmlproject.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 工具类
 *
 * @author wangrufei
 */
public class ZipUtils {
    /**
     * 获取当前系统版本号
     */
    public static final int userSDKVersion = Build.VERSION.SDK_INT;

    /**
     * 在/data/data/下创建一个AppHome文件夹，存放
     */
    public static void copyDbFile(Context context, String fileName) {
        InputStream in = null;
        FileOutputStream out = null;
        String path = saveFiles() +"/AppHome/";
        File file = new File(path + fileName);

        //创建文件夹
        File filePath = new File(path);
        if (!filePath.exists())
            filePath.mkdirs();

        if (file.exists())
            return;

        try {
            in = context.getAssets().open(fileName); // 从assets目录下复制
            out = new FileOutputStream(file);
            int length = -1;
            byte[] buf = new byte[1024];
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 压缩
     * */
    public static void Zipys(Context context){
        //压缩到此处
        String path = saveFiles() +"/";
        //压缩文件的路径
        File file = new File(path + "APP_HOME.zip");

        //创建文件夹
        File filePath = new File(path);
        if (!filePath.exists())
            filePath.mkdirs();

        if (file.exists()){
            file.delete();
        }

        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new CheckedOutputStream(new FileOutputStream(file),new CRC32()));
            zip(zipOutputStream,"APP_HOME",new File(saveFiles() +"/AppHome/"));
            zipOutputStream.flush();
            zipOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(context,"压缩完成",Toast.LENGTH_SHORT).show();
    }
    static void zip(ZipOutputStream zipOutputStream,String name, File fileSrc) throws IOException {

        if (fileSrc.isDirectory()) {
            System.out.println("需要压缩的地址是目录");
            File[] files = fileSrc.listFiles();

            name = name+"/";
            zipOutputStream.putNextEntry(new ZipEntry(name));  // 建一个文件夹
            System.out.println("目录名: "+name);

            for (File f : files) {
                zip(zipOutputStream,name+f.getName(),f);
                System.out.println("目录: "+name+f.getName());
            }

        }else {
            System.out.println("需要压缩的地址是文件");
            zipOutputStream.putNextEntry(new ZipEntry(name));
            System.out.println("文件名: "+name);
            FileInputStream input = new FileInputStream(fileSrc);
            System.out.println("文件路径: "+fileSrc);
            byte[] buf = new byte[1024];
            int len = -1;

            while ((len = input.read(buf)) != -1) {
                zipOutputStream.write(buf, 0, len);
            }

            zipOutputStream.flush();
            input.close();
        }
    }

    /**
     * 解压
     * 1.解压目录下的zip
     * 2.解压assets下的zip
     * */
    public static void Zipjy(Context context){
        String PATH = saveFiles() +"/AppHome/";
        File FILE = new File(PATH+"APP_HOME.zip");
        try {
            upZipFile(FILE, PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(context,"解压完成",Toast.LENGTH_SHORT).show();
    }
    /**
     * 解压缩
     * 将zipFile文件解压到folderPath目录下.
     * @param zipFile zip文件
     * @param folderPath 解压到的地址
     * @throws IOException
     */
    private static void upZipFile(File zipFile, String folderPath) throws IOException {
        ZipFile zfile = new ZipFile(zipFile);
        Enumeration zList = zfile.entries();
        ZipEntry ze = null;
        byte[] buf = new byte[1024];
        while (zList.hasMoreElements()) {
            ze = (ZipEntry) zList.nextElement();
            if (ze.isDirectory()) {
                String dirstr = folderPath + ze.getName();
                dirstr = new String(dirstr.getBytes("8859_1"), "GB2312");
                File f = new File(dirstr);
                f.mkdir();
                continue;
            }
            OutputStream os = new BufferedOutputStream(new FileOutputStream(getRealFileName(folderPath, ze.getName())));
            InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
            int readLen = 0;
            while ((readLen = is.read(buf, 0, 1024)) != -1) {
                os.write(buf, 0, readLen);
            }
            is.close();
            os.close();
        }
        zfile.close();
    }
    /**
     * 给定根目录，返回一个相对路径所对应的实际文件名.
     * @param baseDir     指定根目录
     * @param absFileName 相对路径名，来自于ZipEntry中的name
     * @return java.io.File 实际的文件
     */
    public static File getRealFileName(String baseDir, String absFileName) {
        String[] dirs = absFileName.split("/");
        File ret = new File(baseDir);
        String substr = null;
        if (dirs.length > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                substr = dirs[i];
                try {
                    substr = new String(substr.getBytes("8859_1"), "GB2312");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                ret = new File(ret, substr);

            }
            if (!ret.exists())
                ret.mkdirs();
            substr = dirs[dirs.length - 1];
            try {
                substr = new String(substr.getBytes("8859_1"), "GB2312");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            ret = new File(ret, substr);
            return ret;
        }
        return ret;
    }

    /**
     * 图片文件到文件夹路径
     *
     * @return：保存图片的文件夹名称
     */
    public static String saveFiles() {
        if (!ExistSDCard())
            return "Error";
        String savePath = Environment.getExternalStorageDirectory() + "/EGoldDownload";
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getPath();
    }

    /**
     * 判断是否存在SDCard
     *
     * @return：如果存在则返回真，否则为假
     */
    public static boolean ExistSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

}
