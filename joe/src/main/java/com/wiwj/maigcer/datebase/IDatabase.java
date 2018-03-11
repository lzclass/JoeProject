package com.wiwj.maigcer.datebase;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Joe liuzhaojava@foxmail.com 2018/3/10 17:25
 * @Description: 数据操库作接口
 */
public interface IDatabase<M, K> {
    // --------数据插入相关----------
    //将给定的实体插入数据库
    boolean insert(@NotNull M m);

    //将给定的实体插入数据库，若此实体类存在，则覆盖
    boolean insertOrReplace(@NotNull M m);

    //将给定的实体集合插入数据库
    boolean insertInTx(@NotNull List<M> list);

    //将给定的实体集合插入数据库，若此实体类存在，则覆盖
    boolean insertOrReplaceInTx(@NotNull List<M> list);

    // --------数据删除相关----------
    //从数据库中删除给定的实体
    boolean delete(@NotNull M m);

    //删除数据库中全部数据
    boolean deleteAll();

    //从数据库中删除给定Key所对应的实体
    boolean deleteByKey(@NotNull K key);

    //使用事务操作删除数据库中给定的所有的实体
    boolean deleteInTx(@NotNull List<M> list);

    //使用事务操作删除数据库中给定的所有key所对应的实体
    boolean deleteByKeyInTx(@NotNull K... key);

    // --------数据更新相关----------
//    更新给定的实体
    boolean update(@NotNull M m);

    boolean updateInTx(@NotNull M... m);

    boolean updateInTx(@NotNull List<M> list);

    // --------数据加载相关----------
    //加载给定主键的实体
    M load(@NotNull K key);

    //加载数据库中所有的实体
    List<M> loadAll();

    boolean refresh(@NotNull M m);

    void runInTx(@NotNull Runnable runnable);

    AbstractDao<M, K> getAbstractDao();

    //--------数据库查询相关--------
    QueryBuilder<M> queryBuilder();

    List<M> queryRaw(@NotNull String where, @NotNull String... selectionArg);

    Query<M> queryRawCreate(@NotNull String where, @NotNull Object... selectionArg);

    Query<M> queryRawCreateListArgs(@NotNull String where, @NotNull Collection<Object> selectionArg);

}
//常用的API
//        long    count()：获取数据库中数据的数量
//
//// 数据删除相关
//        void    delete(T entity)：从数据库中删除给定的实体
//        void    deleteAll() ：删除数据库中全部数据
//        void    deleteByKey(K key)：从数据库中删除给定Key所对应的实体
//        void    deleteByKeyInTx(java.lang.Iterable<K> keys)：使用事务操作删除数据库中给定的所有key所对应的实体
//        void    deleteByKeyInTx(K... keys)：使用事务操作删除数据库中给定的所有key所对应的实体
//        void    deleteInTx(java.lang.Iterable<T> entities)：使用事务操作删除数据库中给定实体集合中的实体
//        void    deleteInTx(T... entities)：使用事务操作删除数据库中给定的实体
//
//// 数据插入相关
//        long    insert(T entity)：将给定的实体插入数据库
//        void    insertInTx(java.lang.Iterable<T> entities)：使用事务操作，将给定的实体集合插入数据库
//        void    insertInTx(java.lang.Iterable<T> entities, boolean setPrimaryKey)：使用事务操作，将给定的实体集合插入数据库，
//        并设置是否设定主键
//        void    insertInTx(T... entities)：将给定的实体插入数据库
//        long    insertOrReplace(T entity)：将给定的实体插入数据库，若此实体类存在，则覆盖
//        void    insertOrReplaceInTx(java.lang.Iterable<T> entities)：使用事务操作，将给定的实体插入数据库，若此实体类存在，则覆盖
//        void    insertOrReplaceInTx(java.lang.Iterable<T> entities, boolean setPrimaryKey)：使用事务操作，将给定的实体插入数据库，若此实体类存在，则覆盖
//        并设置是否设定主键
//        void    insertOrReplaceInTx(T... entities)：使用事务操作，将给定的实体插入数据库，若此实体类存在，则覆盖
//        long    insertWithoutSettingPk(T entity)：将给定的实体插入数据库,但不设定主键
//
//// 新增数据插入相关API
//        void    save(T entity)：将给定的实体插入数据库，若此实体类存在，则更新
//        void    saveInTx(java.lang.Iterable<T> entities)：将给定的实体插入数据库，若此实体类存在，则更新
//        void    saveInTx(T... entities)：使用事务操作，将给定的实体插入数据库，若此实体类存在，则更新
//
//// 加载相关
//        T   load(K key)：加载给定主键的实体
//        java.util.List<T>     loadAll()：加载数据库中所有的实体
//protected java.util.List<T>   loadAllAndCloseCursor(android.database.Cursor cursor) ：从cursor中读取、返回实体的列表，并关闭该cursor
//protected java.util.List<T>   loadAllFromCursor(android.database.Cursor cursor)：从cursor中读取、返回实体的列表
//        T   loadByRowId(long rowId) ：加载某一行并返回该行的实体
//protected T     loadUnique(android.database.Cursor cursor) ：从cursor中读取、返回唯一实体
//protected T     loadUniqueAndCloseCursor(android.database.Cursor cursor) ：从cursor中读取、返回唯一实体，并关闭该cursor
//
////更新数据
//        void    update(T entity) ：更新给定的实体
//protected void  updateInsideSynchronized(T entity, DatabaseStatement stmt, boolean lock)
//protected void  updateInsideSynchronized(T entity, android.database.sqlite.SQLiteStatement stmt, boolean lock)
//        void    updateInTx(java.lang.Iterable<T> entities) ：使用事务操作，更新给定的实体
//        void    updateInTx(T... entities)：使用事务操作，更新给定的实体

//       关于GreenDao insertOrReplace和save的区别：

//        insertOrReplace ： 传入的对象在数据库中，有则更新无则插入。推荐同步数据库时使用该方法。
//        save 类似于insertOrReplace，区别在于save会判断传入对象的key，有key的对象执行更新，无key的执行插入。当对象有key但并不在数据库时会执行失败.适用于保存本地列表。
//        适用场景
//        只有本地数据库，且key默认由数据库生成。直接使用save就好
//        插入的数据有key，其实这种情况通常是同步线上数据到本地数据库时的情况，因为直接使用了数据库的key，所以不能使用save，必须使用insertOrReplace。