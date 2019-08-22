package com.zbl.code.common.component;

import com.zbl.code.common.base.BaseException;
import com.zbl.code.common.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.genid.GenId;

/**
 * @Author: zbl
 * @Date: Created in 15:37 2019/8/20
 * @Description: Twitter_Snowflake
 *  SnowFlake的结构如下(每部分用 - 分开):
 *  0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 *  1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0
 *  41位时间截(毫秒级)（当前时间截 - 开始时间截)41位的时间截，可以使用69年
 *  10位的数据机器位，可以部署在1024个节点，包括5位dataCenterId和5位workerId
 *  12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号
 * @Version:
 */
@Slf4j
@Component
public class SnowflakeId implements GenId<Long> {
    // 工作机器ID(0~31)和数据中心ID(0~31)
    private static long workerId;
    private static long dataCenterId;

    // 开始时间戳
    private static final long START_TIME = 1420041600000L;
    // 机器id和数据中心id所占的位数
    private static final long WORKER_ID_BITS = 5L;
    private static final long DATA_CENTER_ID_BITS = 5L;
    // 序列在id中占的位数
    private static final long SEQUENCE_BITS = 12L;

    // 支持的最大机器id和最大数据中心id
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);

    // 各部分需要位移的位数
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    // 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    // 毫秒内序列(0~4095)
    private static long sequence = 0L;
    // 上次生成ID的时间戳
    private static long lastTimestamp = -1L;

    @Value("${zbl-code.worker-id}")
    public void setWorkerId(String workerId) {
        SnowflakeId.workerId = Long.parseLong(workerId);
        if (SnowflakeId.workerId > MAX_WORKER_ID) {
            throw new BaseException(ErrorCodeEnum.GL_ID_BAD_WORK_ID);
        }

    }

    @Value("${zbl-code.data-center-id}")
    public void setDataCenterId(String dataCenterId) {
        SnowflakeId.dataCenterId = Long.parseLong(dataCenterId);
        if (SnowflakeId.dataCenterId > MAX_DATA_CENTER_ID) {
            throw new BaseException(ErrorCodeEnum.GL_ID_BAD_DATA_ID);
        }
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     */
    public static synchronized Long nextId() {
        long timestamp = System.currentTimeMillis();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new BaseException(ErrorCodeEnum.GL_ID_BAD_CLOCK);
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                while (timestamp <= lastTimestamp) {
                    timestamp = System.currentTimeMillis();
                }
            }
        } else {
            //时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - START_TIME) << TIMESTAMP_LEFT_SHIFT)
                | (dataCenterId << DATA_CENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }

    @Override
    public Long genId(String s, String s1) {
        return SnowflakeId.nextId();
    }

    public static void main(String[] args) {
        System.out.print(nextId() + "");
    }
}
