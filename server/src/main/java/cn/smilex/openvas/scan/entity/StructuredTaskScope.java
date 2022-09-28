package cn.smilex.openvas.scan.entity;

import cn.smilex.openvas.scan.util.CommonUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author smilex
 * @date 2022/11/7/23:01
 * @since 1.0
 */
@SuppressWarnings("unused")
@Data
@Slf4j
public class StructuredTaskScope implements AutoCloseable {
    private static final int FUTURE_LIST_DEFAULT_SIZE = 10;

    private boolean commonPool;
    private ExecutorService threadPool;
    private List<Future<?>> futureList;

    public StructuredTaskScope() {
        this.futureList = new ArrayList<>();
        this.commonPool = true;
        this.threadPool = null;
    }

    public StructuredTaskScope(boolean commonPool) {
        init(this, commonPool, 0);
    }

    public StructuredTaskScope(boolean commonPool, int capacity) {
        init(this, commonPool, capacity);
    }

    private static void init(StructuredTaskScope structuredTaskScope, boolean commonPool, int capacity) {
        structuredTaskScope.futureList = new ArrayList<>(capacity <= 0 ? FUTURE_LIST_DEFAULT_SIZE : capacity);
        structuredTaskScope.commonPool = commonPool;

        if (commonPool) {
            structuredTaskScope.threadPool = null;
        } else {
            structuredTaskScope.threadPool = Executors.newCachedThreadPool();
        }
    }

    public void fork(Runnable task) {
        if (this.commonPool) {
            this.futureList.add(
                    CommonUtil.submitTaskToCommonThreadPool(task)
            );
        } else {
            this.futureList.add(
                    this.threadPool.submit(task)
            );
        }
    }

    @Override
    public void close() {
        for (Future<?> future : this.futureList) {
            try {
                future.get();
            } catch (Exception ignore) {
            }
        }
    }
}
