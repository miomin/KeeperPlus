package com.scu.miomin.keeperplus.mvp.module;

import java.io.Serializable;

/**
 * 描述:病人病情自述的实体类，侧心电图前需要病人完成病情自述的填写，查看心电图记录时可以看到这些信息 创建日期:2015/11/11
 *
 * @author 莫绪旻
 */
public class HealthyDescribeByPatientBean implements Serializable {

    private boolean XIONGMEN;
    private boolean XINHUANG;
    private boolean HUXIKUNNAN;
    private boolean TOUYUN;
    private boolean QUANSHENFALI;
    private boolean SHUIJIAOBIEMEN;

    public HealthyDescribeByPatientBean(boolean HUXIKUNNAN, boolean QUANSHENFALI, boolean SHUIJIAOBIEMEN,
                                        boolean TOUYUN, boolean XINHUANG, boolean XIONGMEN) {
        this.HUXIKUNNAN = HUXIKUNNAN;
        this.QUANSHENFALI = QUANSHENFALI;
        this.SHUIJIAOBIEMEN = SHUIJIAOBIEMEN;
        this.TOUYUN = TOUYUN;
        this.XINHUANG = XINHUANG;
        this.XIONGMEN = XIONGMEN;
    }

    public boolean isHUXIKUNNAN() {
        return HUXIKUNNAN;
    }

    public void setHUXIKUNNAN(boolean HUXIKUNNAN) {
        this.HUXIKUNNAN = HUXIKUNNAN;
    }

    public boolean isQUANSHENFALI() {
        return QUANSHENFALI;
    }

    public void setQUANSHENFALI(boolean QUANSHENFALI) {
        this.QUANSHENFALI = QUANSHENFALI;
    }

    public boolean isSHUIJIAOBIEMEN() {
        return SHUIJIAOBIEMEN;
    }

    public void setSHUIJIAOBIEMEN(boolean SHUIJIAOBIEMEN) {
        this.SHUIJIAOBIEMEN = SHUIJIAOBIEMEN;
    }

    public boolean isTOUYUN() {
        return TOUYUN;
    }

    public void setTOUYUN(boolean TOUYUN) {
        this.TOUYUN = TOUYUN;
    }

    public boolean isXINHUANG() {
        return XINHUANG;
    }

    public void setXINHUANG(boolean XINHUANG) {
        this.XINHUANG = XINHUANG;
    }

    public boolean isXIONGMEN() {
        return XIONGMEN;
    }

    public void setXIONGMEN(boolean XIONGMEN) {
        this.XIONGMEN = XIONGMEN;
    }
}
