package com.caizhixing.poetrywidget;

import com.caizhixing.poetry.db.DaoMaster;
import com.caizhixing.poetry.db.DaoSession;

/**
 * Created on 2019/2/11.
 * Author caizhixing
 */
public class GreenDaoManager {
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static GreenDaoManager mInstance; //单例

    private GreenDaoManager(){
        if (mInstance == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new
                    DaoMaster.DevOpenHelper(CustomApplication.Companion.getContext(), "user1-db", null);//此处为自己需要处理的表
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {//保证异步处理安全操作

                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }
    public DaoSession getSession() {
        if(mDaoSession == null){
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }
}
