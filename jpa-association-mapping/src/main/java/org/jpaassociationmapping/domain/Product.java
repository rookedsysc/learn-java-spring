package org.jpaassociationmapping.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "PRODUCT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter @Builder
public class Product {
    @Id
    @Column(name = "PRODUCT_ID")
    private String id;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<Order>();
}
