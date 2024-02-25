package org.jpaassociationmapping.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.jpaassociationmapping.domain.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 주문이 존재하지 않습니다.")
        );
    }

    public void deleteAll() {
        orderRepository.deleteAll();
    }
}
