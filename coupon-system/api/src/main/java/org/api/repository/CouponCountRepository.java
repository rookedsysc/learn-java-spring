package org.api.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CouponCountRepository {
    private final RedisTemplate<String, Integer> redisTemplate;

    public CouponCountRepository(@Qualifier("stringRedisTemplate") RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long increment() {
        return redisTemplate.
                opsForValue().
                increment("couponCount", 1);
    }
}
