package org.example;

import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    try (var jedisPool = new JedisPool("localhost", 6379)) {
      try (
          var jedis = jedisPool.getResource();
      ) {
        String key = "user:2:info";
        String key2 = "user:3:info";

        // 1-1. hset : key, field, value
        jedis.hset(key2, "name", "greg2");
        // 1-2. hset : key, Map
        var map = new HashMap<String, String>();
        map.put("name", "greg3");
        map.put("email", "greg3@gmail.com");
        map.put("phone-number", "010-1234-5678");
        map.put("age", "2");
        jedis.hset(key, map);

        // 2. hdel : key, field
        jedis.hdel(key, "phone-number");

        // 3. hget : key, field
        System.out.println(jedis.hget(key, "name"));

        // 4. hgetall : key
        Map<String,String> userInfo = jedis.hgetAll(key);
        userInfo.forEach((k, v) -> System.out.println(k + " : " + v));

        // 5. hincrby : key, field, increment
        jedis.hincrBy(key, "age", 1);
        System.out.println("age value changed : " +  jedis.hget(key, "age"));
      }
    }
  }
}