package com.example.administrator.permissiondemo.util;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by bean on 2017/1/4.
 * 管理所有的activity的工具类
 */
public class ActivityManger {
    //Stack 继承自vactor  线程安全
    public static Stack<Activity> activities = new Stack();

    /**
     * 添加当前的activity
     *
     * @param activity
     */
    public static void add(Activity activity) {
        activities.add(activity);
    }

    /**
     * 删除当前的activity
     *
     * @param activity
     */
    public static void remove(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 清除当前列表中的所有activity
     */
    public static void clear() {
        activities.clear();
    }

    /**
     * 清除当前列表的所有activity并且关闭activity
     */
    public static void killAll() {
        //遍历集合中的activity并finish掉所有的activity
        for (Activity activity : activities) {
            activity.finish();
        }
    }

    /**
     * 返回当前栈顶的activity
     */
    public static Activity getTopActivity() {
        if (activities.size() < 1) {
            return null;
        } else {
            return activities.get(activities.size() - 1);
        }
    }
}
