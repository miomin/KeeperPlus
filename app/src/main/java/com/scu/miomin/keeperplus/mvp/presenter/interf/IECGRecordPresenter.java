package com.scu.miomin.keeperplus.mvp.presenter.interf;

import java.io.File;

/**
 * Created by miomin on 16/11/16.
 */

public interface IECGRecordPresenter {

    void readEcgFileDir();

    void initECGRecordAdapter();

    String getClickItemFileName(int index);

    void uploadECGFile(File file);
}
