package org.example;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.args.GeoUnit;
import redis.clients.jedis.params.GeoSearchParam;

public class Main {
  public static void main(String[] args) {
    try (var jedisPool = new JedisPool("localhost", 6379)) {
      try (var jedis = jedisPool.getResource()) {
        String key = "stores:geo";
        String twoSome = "Two Some Place";
        String sinchonSubwayStation = "SinchonSubwayStation";
        String sinchonSubwayFoodRestaurant = "SinchonSubwayFoodRestaurant";

        // 1. geoadd
        jedis.geoadd(key, 126.9367104466894, 37.555666092985945, twoSome);
        jedis.geoadd(key, 126.93701921825557, 37.55522025074202, sinchonSubwayStation);
        jedis.geoadd(key, 126.93615692082591, 37.55778088615871, sinchonSubwayFoodRestaurant);

        // 2. geo dist
        // 두 지점 사이의 거리를 구할 수 있다.
        Double geoDist = jedis.geodist(key, sinchonSubwayFoodRestaurant, sinchonSubwayStation);
        System.out.println("신촌역 ~ 신촌 서브웨이 : " + geoDist);

        // 3. geo search key 좌표 반경 단위
        GeoCoordinate yonseiUnivCoordinate = new GeoCoordinate(126.93889056062496, 37.564197428904414);
        /// 연세대학교로부터 반경 2000M 이내 데이터 검색
        jedis.geosearch(key, yonseiUnivCoordinate, 1000, GeoUnit.M)
            .forEach(geoResponse -> {
              System.out.println(geoResponse.getMemberByString() + " : " + geoResponse.getDistance());
            });

        // 위의 방법으로 호출할 경우 Coordinate와 Distance를 모두 가져올 수 없다.
        // 그래서 GeoSearchParam을 사용해서 호출하게되면 Coordinate와 Distance를 모두 가져올 수 있다.
        jedis.geosearch(key, new GeoSearchParam().fromLonLat(yonseiUnivCoordinate)
                .byRadius(1000, GeoUnit.M)
                .withCoord().withDist())
            .forEach(geoResponse -> {
              System.out.println(geoResponse.getMemberByString() + " : " + "coordinate(" + geoResponse.getCoordinate() + ") distance(" + geoResponse.getDistance() + ")");
            });
      }
    }
  }
}