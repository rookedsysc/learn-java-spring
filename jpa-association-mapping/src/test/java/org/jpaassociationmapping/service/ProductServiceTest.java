package org.jpaassociationmapping.service;

import org.jpaassociationmapping.domain.Member;
import org.jpaassociationmapping.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("검색된 Product에서 Member를 찾을 수 있다.")
    void PRODUCT_MEMBER_양방향_연관테스트() {
        // given
        Product productA = Product.builder().id("productA").name("productA").build();
        productService.save(productA);
        Member member = Member.builder().id("memberA").username("memberA").build();
        member.getProducts().add(productA);
        Member savedMember = memberService.save(member);

        // when
        Product searchedProduct = productService.findById(productA.getId());

        // then
        assertEquals(searchedProduct.getMembers().get(0).getId(), savedMember.getId());
    }
}