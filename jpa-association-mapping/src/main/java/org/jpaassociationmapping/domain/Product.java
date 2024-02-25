package org.jpaassociationmapping.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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

    // 역방향 추가
    // 연관관계의 주인은 Member이고 Member의 products 필드에 의해 매핑된다.
    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private List<Member> members;
}
