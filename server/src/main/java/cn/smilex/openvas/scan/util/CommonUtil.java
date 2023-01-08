package cn.smilex.openvas.scan.util;

import cn.smilex.openvas.scan.concurrent.CounterThreadFactory;
import cn.smilex.openvas.scan.config.CommonConfig;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * @author smilex
 * @date 2022/9/29/18:09
 * @since 1.0
 */
public final class CommonUtil {
    public static ConfigurableApplicationContext configurableApplicationContext;
    private static final ExecutorService COMMON_THREAD_POOL = Executors.newCachedThreadPool(
            new CounterThreadFactory("common-pool-thread")
    );

    static {
        configurableApplicationContext = null;
    }

    /**
     * 提交任务到公共线程池
     *
     * @param task 任务
     * @return Future
     */
    public static Future<?> submitTaskToCommonThreadPool(Runnable task) {
        return COMMON_THREAD_POOL.submit(task);
    }

    /**
     * 提交任务到公共线程池
     *
     * @param task 任务
     * @return Future
     */
    public static <T> Future<T> submitTaskToCommonThreadPool(Callable<T> task) {
        return COMMON_THREAD_POOL.submit(task);
    }

    public static boolean isInForArray(Class<?> clazz, Class<?>[] array) {
        for (Class<?> aClass : array) {
            if (aClass.equals(clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean signCookie(String body) {
        return body.contains("forbidden");
    }

    @SuppressWarnings("all")
    public static <T> void spinLock(Supplier<T> expression, boolean isNull) {
        T v;
        do {
            v = expression.get();
        } while (isNull ? v == null : v != null);
    }

    @FunctionalInterface
    public interface ListToStrTask<T> {
        String handler(T v);
    }

    /**
     * list转字符串
     *
     * @param list list
     * @param task 转换中间处理
     * @param <T>  unknown type
     * @return 字符串
     */
    public static <T> String listToStr(List<T> list, ListToStrTask<T> task) {
        if (list == null || list.size() == 0) {
            return CommonConfig.EMPTY_STRING;
        }

        StringBuilder sb = new StringBuilder();

        for (T t : list) {
            sb.append(task.handler(t));
        }

        return sb.toString();
    }

    /**
     * 解析UTC时间
     *
     * @param time 时间
     * @return 时间
     */
    public static LocalDateTime parseUtcTime(String time) {
        return LocalDateTime.from(CommonConfig.DATE_TIME_FORMATTER.parse(time.replace("Z", CommonConfig.EMPTY_STRING)));
    }
}
