package com.scu.miomin.keeperplus.mvp.cache;

import com.scu.miomin.keeperplus.mvp.model.Userbean;

/**
 * Created by miomin on 16/11/21.
 */

public class KeeperPlusCache {

    private static KeeperPlusCache instance;
    private Userbean currentUser;

    private KeeperPlusCache() {

    }

    public static KeeperPlusCache getInstance() {
        if (instance == null) {
            synchronized (KeeperPlusCache.class) {
                if (instance == null)
                    instance = new KeeperPlusCache();
            }
        }
        return instance;
    }

    public void setCurrentUser(Userbean userbean) {
        this.currentUser = userbean;
    }

    public Userbean getCurrentUser() {
        return currentUser;
    }
}
