package com.example.jediscache;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

@Component
public class RedisConfig {
  @Bean
  public JedisPool createJedisPool() {

    /**
     * ! Jedis 4 버전대 이상에서 사용시 에러 발생함
     * ! Jedis 3.7.1 버전에서는 안정적으로 사용 가능
     * : 관련 이슈 - https://github.com/redis/jedis/issues/2781
    */
    return new JedisPool("127.0.0.1", 6379);
  }
}
