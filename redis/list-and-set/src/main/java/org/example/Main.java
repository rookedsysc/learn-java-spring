package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    // var redisList = new RedisList();
    // redisList.run();

    var redisSet = new RedisSet();
    redisSet.run();
  }
}