package org.example;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.stream.IntStream;

public class Main {
  public static void main(String[] args) {
    try (var jedisPool = new JedisPool("localhost", 6379)) {
      try (var jedis = jedisPool.getResource()) {
        jedis.setbit("request-somepage-20231115", 100, true);
        jedis.setbit("request-somepage-20231115", 200, true);
        jedis.setbit("request-somepage-20231115", 300, true);

        System.out.println(jedis.getbit("request-somepage-20231115", 100));
        // 설정된적 없는 bit는 false로 나옴
        System.out.println(jedis.getbit("request-somepage-20231115", 50));

        System.out.println(jedis.bitcount("request-somepage-20231115"));

        /**
         * bitmap vs set 성능 비교
         * 아래 코드 실행 후
         * MEMORY USAGE request-somepage-set-20231115
         * 명령어를 통해서 사용되는 메모리 용량을 알아볼 수 있음
         */
        // pipline은 여러개의 명령어를 동시에 실행할 때 사용
        Pipeline pipeline = jedis.pipelined();

        IntStream.rangeClosed(0, 100000).forEach(i -> {
          pipeline.sadd("request-somepage-set-20231115", String.valueOf(i), "1");
          pipeline.setbit("request-somepage-bit-20231115", i, true);

          if (i % 1000 == 0) {
            pipeline.sync();
          }
        });
        pipeline.sync();
      }
    }
  }
}