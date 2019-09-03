package com.xiao.common.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CommonMethods {
    /**
     * 生成20位形如yyyyMMddHHmmssSSS+3位数随机数
     */
    private static Lock lock = new ReentrantLock();
    private static ArrayList<String> ids = new ArrayList<>();

    public static String generate22UniqueString() {
        lock.lock();
        try {
            String id;
            String suffix = String.format("%03d", new Random().nextInt(1000));
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
            id = time + suffix;
            while (ids.contains(id)) {
                time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
                id = time + String.format("%03d", new Random().nextInt(1000));
            }
            if (ids.size() > 1000) {
                ids.subList(0, 300).clear();
            }
            ids.add(id);
            return id;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 判断集合是否有重复元素
     */
    public static boolean haveRepeatItem(Collection<?> col) {
        if (col == null) {
            return false;
        }
        if (col instanceof Set) {
            return false;
        }
        Set<?> noRepeatSet = new HashSet<>(col);
        return !(col.size() == noRepeatSet.size());
    }

    /**
     * 查询重复的元素
     */
    public static <T> List<T> findRepeatItem(Collection<T> col){
        if (col instanceof Set){
            return new ArrayList<>();
        }
        HashSet<T> set = new HashSet<>();
        List<T> repeatItems = new ArrayList<>();
        for (T t : col){
            if (set.contains(t)){
                repeatItems.add(t);
            } else {
                set.add(t);
            }
        }
        return repeatItems;
    }
}
