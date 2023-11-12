package org.example;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.resps.Tuple;

import java.util.HashMap;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    try (var jedisPool = new JedisPool("localhost", 6379)) {
      try (var jedis = jedisPool.getResource()) {
        String key = "game:scores";
        // 1. zadd key map
        var map = new HashMap<String, Double>();
        map.put("mario", 3000.0);
        map.put("luigi", 1500.0);
        map.put("yoshi", 4500.0);
        map.put("bowser", 6000.0);
        map.put("toad", 2000.0);
        jedis.zadd(key,map);

        // 2-1. zrange key start stop
        // 기본적으로 오름차순 정렬이 된다.
        System.out.println(jedis.zrange(key, 0, Long.MAX_VALUE));
        System.out.println(jedis.zrange(key, 0, -1));

        // 2-2. zrangeWithScores
        List<Tuple> tuples = jedis.zrangeWithScores(key, 0, -1);
        tuples.forEach(i -> System.out.println(i.getElement() + " : " + i.getScore()));

        // 3. card
        System.out.println("zcard : " + jedis.zcard(key));

        // 4. incryby key increment member
        jedis.zincrby(key, 2000, "luigi");
        jedis.zrangeWithScores(key, 0, -1).forEach(i -> System.out.println(i.getElement() + " : " + i.getScore()));

        // 4. zrem
        jedis.zrem(key, "toad");
        System.out.println("after remove zcard : " + jedis.zcard(key));
      }
    }
  }
}