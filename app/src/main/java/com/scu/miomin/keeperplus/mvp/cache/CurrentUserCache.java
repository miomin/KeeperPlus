package com.scu.miomin.keeperplus.mvp.cache;

import com.scu.miomin.keeperplus.mvp.model.Userbean;

/**
 * Created by miomin on 16/11/21.
 */

public class CurrentUserCache {

    private static CurrentUserCache instance;
    private Userbean currentUser;

    private CurrentUserCache() {

    }

    public static CurrentUserCache getInstance() {
        if (instance == null) {
            synchronized (CurrentUserCache.class) {
                if (instance == null)
                    instance = new CurrentUserCache();
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
