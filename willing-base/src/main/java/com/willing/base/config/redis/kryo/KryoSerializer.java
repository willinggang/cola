package com.willing.base.config.redis.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.pool.KryoPool;
import org.objenesis.strategy.StdInstantiatorStrategy;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.ByteArrayInputStream;

/**
 * @author Szg
 * @date 2021/1/6
 */
public class KryoSerializer<T> implements RedisSerializer<T> {
    public static final int DEFAULT_BUFFER_SIZE = 4096;
    KryoPool pool;
    private final KryoOutputPool kryoOutputPool = new KryoOutputPool();
    private final KryoInputPool kryoInputPool = new KryoInputPool();
    boolean preRegisterClass = false;

    public KryoSerializer() {
        pool = new KryoPool.Builder(() -> {
            final Kryo kryo = new Kryo();
            kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(
                    new StdInstantiatorStrategy()));
            kryo.setReferences(false); // 关闭循环引用检查
            return kryo;
        }).softReferences().build();
        pool.release(pool.borrow());
    }

  /*  public KryoSerializer(boolean preRegisterClass) {
        this.preRegisterClass = preRegisterClass;
        pool = new KryoPool.Builder(() -> {
            final Kryo kryo = new Kryo();
            kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(
                    new StdInstantiatorStrategy()));
            kryo.setReferences(false);    //关闭循环引用检查
            if (preRegisterClass) {
                KryoRegisterUtils.register(kryo);
            }
            return kryo;
        }).softReferences().build();
        pool.release(pool.borrow());
    }*/

    @Override
    public byte[] serialize(T t) throws SerializationException {
        return kryoOutputPool.run(output -> {
            return pool.run(kryo -> {
                kryo.writeClassAndObject(output, t);
                output.flush();
                return output.getByteArrayOutputStream().toByteArray();
            });
        }, DEFAULT_BUFFER_SIZE);

    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        return bytes == null || bytes.length == 0 ? null : kryoInputPool.run(input -> {
            input.setInputStream(new ByteArrayInputStream(bytes));
            return pool.run(kryo -> {
                @SuppressWarnings("unchecked")
                T obj = (T) kryo.readClassAndObject(input);
                return obj;
            });
        }, DEFAULT_BUFFER_SIZE);
    }
}
