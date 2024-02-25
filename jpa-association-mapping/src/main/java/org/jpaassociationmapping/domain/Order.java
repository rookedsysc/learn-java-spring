package org.jpaassociationmapping.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "ORDERS")
public class Order {
    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public void setMember(Member member) {
        this.member = member;
        if (!member.getOrders().contains(this)) {
            member.getOrders().add(this);
        }
    }

    public void setProduct(Product product) {
        this.product = product;
        if (!product.getOrders().contains(this)) {
            product.getOrders().add(this);
        }
    }
}
