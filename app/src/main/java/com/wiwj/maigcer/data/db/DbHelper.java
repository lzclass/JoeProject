package com.wiwj.maigcer.data.db;

import android.content.Context;

import com.wiwj.maigcer.utils.Constants;
import com.wiwj.maigcer.datebase.DBManager;
import com.wiwj.maigcer.data.db.dbmodel.UserModel;
import com.wiwj.maigcer.data.db.gen.DaoMaster;
import com.wiwj.maigcer.data.db.gen.DaoSession;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

/**
 * @Description: 数据库操作类，由于greenDao的特殊性，不能在框架中搭建，
 * 所有数据库操作都可以参考该类实现自己的数据库操作管理类，不同的Dao实现
 * 对应的getAbstractDao方法就行。
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 17/1/18 23:18.
 */
public class DbHelper {
    private static final String DB_NAME = "wiwj_magicer.db";//数据库名称
    private static DbHelper instance;
    private static DBManager<UserModel, Long> userModelDBManager;
    private DaoMaster.DevOpenHelper mHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DbHelper() {

    }

    public static DbHelper getInstance() {
        if (instance == null) {
            synchronized (DbHelper.class) {
                if (instance == null) {
                    instance = new DbHelper();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        mHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        mDaoMaster = new DaoMaster(mHelper.getWritableDb());
        mDaoSession = mDaoMaster.newSession();
        QueryBuilder.LOG_SQL = Constants.IS_DEBUG;//打印sql日志
        QueryBuilder.LOG_VALUES = Constants.IS_DEBUG;
    }

    public void init(Context context, String dbName) {
        mHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        mDaoMaster = new DaoMaster(mHelper.getWritableDb());
        mDaoSession = mDaoMaster.newSession();

    }

    public DBManager<UserModel, Long> getUserModelDBManager() {
        if (userModelDBManager == null) {
            userModelDBManager = new DBManager<UserModel, Long>() {
                @Override
                public AbstractDao<UserModel, Long> getAbstractDao() {
                    return mDaoSession.getUserModelDao();
                }
            };
        }
        return userModelDBManager;
    }
    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public void clear() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    public void close() {
        clear();
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }
}
