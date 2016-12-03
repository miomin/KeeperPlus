package com.scu.miomin.keeperplus.mvp.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by miomin on 16/12/02.
 */

public class KeeperEcgCache {

    private static KeeperEcgCache instance;
    // 存储原始心电数据
    private List<Integer> list;
    public int size = 1;
    public List<Integer> tempList = new ArrayList<>(10);

    private KeeperEcgCache() {
        list = Collections.synchronizedList(new LinkedList<Integer>());
    }

    public static KeeperEcgCache getInstance() {
        if (instance == null) {
            synchronized (KeeperPlusCache.class) {
                if (instance == null)
                    instance = new KeeperEcgCache();
            }
        }
        return instance;
    }

    public void addValue(int value) {

        tempList.add(value);

        if (tempList.size() == size) {
            int sum = 0;
            for (int i = 0; i < tempList.size(); i++) {
                sum += tempList.get(i);
            }

            list.add(sum / size);
            tempList.clear();
        }
    }

    public void clearValue() {
        list.clear();
    }

    public int getValue() {
        int value = list.get(0);
        list.remove(0);
        return value;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int getSize() {
        return list.size();
    }
}
