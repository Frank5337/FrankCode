package com.zbl.dds;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * @Author: zbl
 * @Date: Created in 2020/11/11
 * @Description: 动态数据源类持有者，线程安全
 * 持有动态数据源的信息，以及修改清理数据源
 * @Version: $
 */
@Slf4j
public class DynamicDataSourceHolder {

    /**
     * 线程安全
     */
    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger COUNTER = new AtomicInteger(-1);

    /**
     * 默认写数据源
     */
    public static DBTypeEnum getDbType() {
        DBTypeEnum db = contextHolder.get();
        if (StringUtils.isEmpty(db)) {
            db = DBTypeEnum.MASTER;
        }
        return db;
    }

    /**
     * 设置线程的dbType
     *
     * @param dbType 数据源类型
     */
    public static void setDbType(DBTypeEnum dbType) {

        contextHolder.set(dbType);
    }

    /**
     * 清理连接类型
     */
    public static void clearDbType() {
        contextHolder.remove();
    }

    /**
     * 默认 master
     */
    public static void setDefateDb() {
        contextHolder.set(DBTypeEnum.MASTER);
    }

    public static void master() {
        setDbType(DBTypeEnum.MASTER);
        log.info("切换到master");
    }


    /**
     * 轮询获取从库, 测试先只用一个从库
     */
    public static void slave() {
        //get 并 +1
        int index = COUNTER.getAndIncrement() % 2;
        if (COUNTER.get() > 9999) {
            COUNTER.set(-1);
        }
        if (index == 0) {
            setDbType(DBTypeEnum.SLAVE);
            log.info("切换到slave");
        } else {
            setDbType(DBTypeEnum.SLAVE);
            log.info("切换到slave");
        }
    }


}
