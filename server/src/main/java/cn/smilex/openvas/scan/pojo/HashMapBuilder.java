package cn.smilex.openvas.scan.pojo;

import java.util.HashMap;

/**
 * @author smilex
 * @date 2022/10/1/9:05
 * @since 1.0
 */
public final class HashMapBuilder<T, K> {
    private final HashMap<T, K> data;

    public HashMapBuilder() {
        this.data = new HashMap<>();
    }

    public HashMapBuilder(int initialCapacity) {
        this.data = new HashMap<>(initialCapacity);
    }

    public HashMapBuilder<T, K> put(T key, K value) {
        this.data
                .put(key, value);
        return this;
    }

    public HashMap<T, K> get() {
        return this.data;
    }
}
