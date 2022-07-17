package com.willing.base.config.redis.kryo;

import java.lang.ref.SoftReference;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Function;

/**
 * @author Szg
 * @date 2021/1/6
 */
public abstract class KryoIOPool<T> {

    private final Queue<SoftReference<T>> queue = new ConcurrentLinkedQueue<>();

    private T borrow(final int bufferSize) {
        T element;
        SoftReference<T> reference;
        while ((reference = queue.poll()) != null) {
            if ((element = reference.get()) != null) {
                return element;
            }
        }
        return create(bufferSize);
    }

    protected abstract T create(final int bufferSize);

    protected abstract boolean recycle(final T element);

    <R> R run(final Function<T, R> function, final int bufferSize) {
        final T element = borrow(bufferSize);
        try {
            return function.apply(element);
        } finally {
            if (recycle(element)) {
                queue.offer(new SoftReference<>(element));
            }
        }
    }
}
