package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

public class RedisSet {
  public void run() {
    try (var jedisPool = new JedisPool("localhost", 6379);
    ) {
      try (Jedis jedis = jedisPool.getResource()) {
        String key = "user:500:follow";
        String key2 = "user:200:follow";
        // 1. sadd : Set에 멤버 추가
        jedis.sadd(key, "100","200","300", "400","500");
        jedis.sadd(key2, "200","500","600");
        // 2. srem : Set에 멤버 삭제
        jedis.srem(key, "200");
        // 3. smembers : Set에 멤버 조회
        jedis.smembers(key).forEach(System.out::println);
        // 4. sismember : Set에 멤버가 있는지 확인
        boolean isMember = jedis.sismember(key, "400");
        if (isMember) {
          System.out.println("100 is member");
        } else {
          System.out.println("100 is not member");
        }
        // 5. sinter : Set 교집합 조회
        Set<String> sinter = jedis.sinter(key, key2);
        Integer cnt = 0;
        for (String s : sinter) {
          cnt ++;
          System.out.printf("siner value %s : %s\n", cnt, s);
        }
        // 6. sunion : Set 합집합 조회
        Set<String> sunion = jedis.sunion(key, key2);
        cnt = 0;
        for (String s: sunion) {
          cnt ++;
          System.out.printf("sunion value %s : %s\n", cnt, s);
        }
      }
    }
  }
}
