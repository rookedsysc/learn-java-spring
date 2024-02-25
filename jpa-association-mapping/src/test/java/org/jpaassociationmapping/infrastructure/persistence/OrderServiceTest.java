package org.jpaassociationmapping.infrastructure.persistence;

import jakarta.transaction.Transactional;
import org.hibernate.LazyInitializationException;
import org.jpaassociationmapping.domain.Member;
import org.jpaassociationmapping.domain.Order;
import org.jpaassociationmapping.domain.Product;
import org.jpaassociationmapping.service.MemberService;
import org.jpaassociationmapping.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("Order를 통한 다대다 맵핑 테스트")
    void ORDER_양방향_연관테스트() {
        // given
        Member member = Member.builder().id("홍길동").username("홍길동").build();
        memberService.save(member);
        Product product = Product.builder().id("초코비").name("초코비").build();
        productService.save(product);
        Order order = new Order();
        order.setMember(member);
        order.setProduct(product);
        orderService.save(order);


        // when
        Order searchedOrder = orderService.findById(order.getOrderId());
        Product searchedProduct = productService.findById(product.getId());
        Member searchedMember = memberService.findByUserName(member.getUsername());

        // then
        assertEquals(searchedOrder.getMember().getId(), member.getId());
        assertEquals(searchedOrder.getProduct().getId(), product.getId());
        // Lazy 로딩이라서 에러 발생
        assertThrows(LazyInitializationException.class, () -> {
            searchedProduct.getOrders().get(0).getMember().getUsername();
        });
        // EAGER 로딩이라서 Member를 통해서 정상적으로 Product까지 조회가 됨
        assertEquals(searchedMember.getOrders().get(0).getProduct().getName(), searchedProduct.getName());

        orderService.deleteAll();
        productService.deleteAll();
        memberService.deleteAll();
    }
}