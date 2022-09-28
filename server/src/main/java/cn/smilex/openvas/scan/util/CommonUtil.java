package cn.smilex.openvas.scan.util;

import cn.hutool.core.lang.Snowflake;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.format.DateTimeFormatter;
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
    public static final String EMPTY_STRING = "";
    public static ConfigurableApplicationContext configurableApplicationContext;
    public static final Snowflake SNOWFLAKE = new Snowflake(2, 3);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final ExecutorService COMMON_THREAD_POOL = Executors.newCachedThreadPool();

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
}
