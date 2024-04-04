package org.api.service;

import org.api.domain.entity.Coupon;
import org.api.repository.CouponCountRepository;
import org.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository) {
        this.couponCountRepository = couponCountRepository;
        this.couponRepository = couponRepository;
    }

    /**
     * 쿠폰이 100개가 넘게 발급되었는지 조회 후
     * 100개가 넘지 않으면 쿠폰을 발급한다.
     */
    public void apply(Long userId) {
        long count = couponCountRepository.increment();
        if (count > 100) {
            return;
        }
        couponRepository.save(new Coupon(userId));
    }
}
