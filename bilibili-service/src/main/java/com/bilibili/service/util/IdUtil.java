package com.bilibili.service.util;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description : ID生成工具类
 *
 * @author kunlunrepo
 * date :  2024-01-12 18:15
 */
public class IdUtil {


    private static final Lock lock = new ReentrantLock();
    private static final Random random = new Random();
    private static long lastTimestamp = System.currentTimeMillis();

    public static long generate() {
        lock.lock();
        try {
            long currentTimestamp = Instant.now().toEpochMilli() / 1000L;
            if (currentTimestamp <= lastTimestamp) { // 注意这里可能不再需要处理时间回拨问题，因为我们只关注秒级变化
                currentTimestamp = lastTimestamp + 1;
            }
            lastTimestamp = currentTimestamp;

            // 调整随机数部分，假设我们使用的是5位随机数
            long keyPart = random.nextInt((int) Math.pow(10, 5)); // 可以生成从0到99999的随机数
            return (currentTimestamp << 5) | keyPart;
        } finally {
            lock.unlock();
        }
    }

}
