package com.willing.base.config.redis.kryo;

import com.esotericsoftware.kryo.io.Output;

/**
 * @author Szg
 * @date 2021/1/6
 */
public class ByteArrayOutput extends Output {

    private final BufferAwareByteArrayOutputStream stream;

    ByteArrayOutput(final int bufferSize, final int maxBufferSize, final BufferAwareByteArrayOutputStream stream) {
        super(bufferSize, maxBufferSize);
        super.setOutputStream(stream);
        this.stream = stream;
    }

    BufferAwareByteArrayOutputStream getByteArrayOutputStream() {
        return stream;
    }
}
