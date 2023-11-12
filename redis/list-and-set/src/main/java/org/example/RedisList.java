package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class RedisList {
  public void run() {
    try (var jedisPool = new JedisPool("localhost", 6379);
    ) {
      try (Jedis jedis = jedisPool.getResource()) {
        // list
        // 1. stack
        jedis.rpush("stack1", "aaaa");
        jedis.rpush("stack1", "bbbb");
        jedis.rpush("stack1", "cccc");
        jedis.lrange("stack1", 0, -1)
            .forEach(System.out::println);
        System.out.println("stack1 pop : " + jedis.rpop("stack1")); // cccc
        System.out.println("stack1 pop : " + jedis.rpop("stack1")); // bbbb
        System.out.println("stack1 pop : " + jedis.rpop("stack1")); // aaaa
        System.out.println("stack1 pop : " + jedis.rpop("stack1")); // null

        // 2. queue
        jedis.rpush("queue1", "aaaa");
        jedis.rpush("queue1", "bbbb");
        jedis.rpush("queue1", "cccc");

        System.out.println("queue1 lpop : " + jedis.lpop("queue1")); // aaaa
        System.out.println("queue1 lpop : " + jedis.lpop("queue1")); // bbbb
        System.out.println("queue1 lpop : " + jedis.lpop("queue1")); // cccc
        System.out.println("queue1 lpop : " + jedis.lpop("queue1")); // null

        /**
         * b가 앞에 붙으면 blocking이라는 뜻으로
         * 뒤에 오는 값에 따라서 값이 들어올 때까지 몇 초간 기다린다는 뜻이다.
         */
        // 3. brpop, blpop
        // 이렇게 사용할 경우 리슨 상태로 queue에 값이 들어올 때마다 확인할 수 있음
        while (true) {
          List<String> blpop = jedis.blpop(10, "blocking-queue");
          if (blpop != null) {
            blpop.forEach(System.out::println);
          }
        }
      }
    }
  }
}
