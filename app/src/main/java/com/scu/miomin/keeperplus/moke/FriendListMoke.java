package com.scu.miomin.keeperplus.moke;

import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.model.DoctorBean;
import com.scu.miomin.keeperplus.mvp.model.PatientBean;

/**
 * Created by miomin on 16/11/30.
 */

public class FriendListMoke {

    private static FriendListMoke instance;

    private FriendListMoke() {

    }

    public static FriendListMoke getInstance() {
        if (instance == null) {
            synchronized (FriendListMoke.class) {
                if (instance == null) {
                    instance = new FriendListMoke();
                }
            }
        }

        return instance;
    }

    public void initFriendList() {
        DoctorBean doctorBean;

        doctorBean = (DoctorBean) KeeperDataMoke.getInstance().getUserByID("2013141463040");
        KeeperPlusCache.getInstance().addFriend(doctorBean);

        doctorBean = (DoctorBean) KeeperDataMoke.getInstance().getUserByID("2013141463002");
        KeeperPlusCache.getInstance().addFriend(doctorBean);

        doctorBean = (DoctorBean) KeeperDataMoke.getInstance().getUserByID("2013141463001");
        KeeperPlusCache.getInstance().addFriend(doctorBean);

        doctorBean = (DoctorBean) KeeperDataMoke.getInstance().getUserByID("2013141463003");
        KeeperPlusCache.getInstance().addFriend(doctorBean);

        PatientBean patientBean;

        patientBean = (PatientBean) KeeperDataMoke.getInstance().getUserByID("18000000000");
        KeeperPlusCache.getInstance().addFriend(patientBean);

        patientBean = (PatientBean) KeeperDataMoke.getInstance().getUserByID("18000000001");
        KeeperPlusCache.getInstance().addFriend(patientBean);

        patientBean = (PatientBean) KeeperDataMoke.getInstance().getUserByID("18084803926");
        KeeperPlusCache.getInstance().addFriend(patientBean);

        patientBean = (PatientBean) KeeperDataMoke.getInstance().getUserByID("13558868295");
        KeeperPlusCache.getInstance().addFriend(patientBean);

        KeeperPlusCache.getInstance().addFriend(KeeperPlusCache.getInstance().getCurrentUser());
    }

}
