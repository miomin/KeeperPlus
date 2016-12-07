package com.scu.miomin.keeperplus.util;

import android.net.Uri;
import android.os.Environment;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TakePhotoOptions;

import java.io.File;

import me.shaohui.advancedluban.Luban;

public class TakePhotoHelper {

    public static TakePhotoHelper of() {
        return new TakePhotoHelper();
    }

    private TakePhotoHelper() {
    }

    public void selectPhoto(TakePhoto takePhoto) {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);

        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        takePhoto.onPickFromDocumentsWithCrop(imageUri, getCropOptions());
    }

    public void takeCamera(TakePhoto takePhoto) {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);

        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
    }

    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true);
        builder.setCorrectImage(true);
        takePhoto.setTakePhotoOptions(builder.create());
    }

    private void configCompress(TakePhoto takePhoto) {
        int maxSize = 102400;
        int width = 800;
        int height = 800;
        boolean showProgressBar = true;
        boolean enableRawFile = true;
        CompressConfig config;
        LubanOptions option = new LubanOptions.Builder()
                .setGear(Luban.CUSTOM_GEAR)
                .setMaxHeight(height)
                .setMaxWidth(width)
                .setMaxSize(maxSize)
                .create();
        config = CompressConfig.ofLuban(option);
        config.enableReserveRaw(enableRawFile);
        takePhoto.onEnableCompress(config, showProgressBar);
    }

    private CropOptions getCropOptions() {
        int height = 800;
        int width = 800;

        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(width).setAspectY(height);
        builder.setWithOwnCrop(true);
        return builder.create();
    }

}
