package com.willing.base.config.redis.kryo;

import java.io.ByteArrayOutputStream;

/**
 * @author Szg
 * @date 2021/1/6
 */
public class BufferAwareByteArrayOutputStream extends ByteArrayOutputStream {

    BufferAwareByteArrayOutputStream(int size) {
        super(size);
    }

    int getBufferSize() {
        return buf.length;
    }
}