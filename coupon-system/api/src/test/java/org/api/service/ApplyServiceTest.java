package org.api.service;

import org.api.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplyServiceTest {
    @Autowired
    private ApplyService applyService;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    void 한번만응모() {
        applyService.apply(1L);
        assertEquals(1, couponRepository.count());
    }

    /**
     * race condition 발생하는 예시
     */
    @Test
    void Redis_활용해서_수정_후_RaceCondition발생X() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                applyService.apply(userId);
                latch.countDown();
            });
        }
        latch.await();

        long count = couponRepository.count();
        assertThat(count).isEqualTo(100);
    }
}