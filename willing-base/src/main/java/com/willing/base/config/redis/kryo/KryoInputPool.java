package com.willing.base.config.redis.kryo;

import com.esotericsoftware.kryo.io.Input;

/**
 * @author Szg
 * @date 2021/1/6
 */
public class KryoInputPool extends KryoIOPool<Input> {

    static final int MAX_POOLED_BUFFER_SIZE = 512 * 1024;

    @Override
    protected Input create(int bufferSize) {
        return new Input(bufferSize);
    }


    @Override
    protected boolean recycle(Input input) {
        if (input.getBuffer().length < MAX_POOLED_BUFFER_SIZE) {
            input.setInputStream(null);
            return true;
        }
        return false;
    }

}
