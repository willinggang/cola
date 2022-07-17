package com.willing.base.service.impl;

import com.willing.base.service.CacheService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Szg
 * @ClassName: RedisCacheServiceImpl
 * @Description: redis 缓存服务
 * @date 2021/12/1616:03
 */
@Service
public class RedisCacheServiceImpl implements CacheService {

    @Resource
    private RedisTemplate fastRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public <T> void save(Integer expireTimeOut, TimeUnit expireTimeUnit, String key, T data) {
        ValueOperations<String, T> opsForValue = fastRedisTemplate.opsForValue();
        opsForValue.set(key, data, expireTimeOut, expireTimeUnit);
    }

    @Override
    public <T> T read(String key) {
        ValueOperations<String, T> opsForValue = fastRedisTemplate.opsForValue();
        return opsForValue.get(key);
    }

    @Override
    public <T> void saveKyro(Integer expireTimeOut, TimeUnit expireTimeUnit, String key, T data) {
        ValueOperations<String, T> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, data, expireTimeOut, expireTimeUnit);
    }

    @Override
    public <T> T readKryo(String key) {
        ValueOperations<String, T> opsForValue = redisTemplate.opsForValue();
        return opsForValue.get(key);
    }
}
