package org.jpaassociationmapping.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "PRODUCT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter @Builder
public class Product {
    @Id
    @Column(name = "PRODUCT_ID")
    private String id;

    private String name;
}
