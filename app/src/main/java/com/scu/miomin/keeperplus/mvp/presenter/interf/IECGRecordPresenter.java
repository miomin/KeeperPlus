package com.scu.miomin.keeperplus.mvp.presenter.interf;

/**
 * Created by miomin on 16/11/16.
 */

public interface IECGRecordPresenter {

    void readEcgFileDir();

    void initECGRecordAdapter();

    String getClickItemFileName(int index);

    void uploadECGFile(String filename);
}
