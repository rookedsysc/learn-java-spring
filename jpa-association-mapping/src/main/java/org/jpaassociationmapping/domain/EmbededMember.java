package org.jpaassociationmapping.domain;

import jakarta.persistence.*;
import lombok.*;
import org.jpaassociationmapping.domain.vo.Address;
import org.jpaassociationmapping.domain.vo.PhoneNumber;
import org.jpaassociationmapping.domain.vo.Zipcode;

@Entity(name="EMBEDED_MEMBER")
@Builder @AllArgsConstructor @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmbededMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Embedded
    Address address;

    @Embedded
    PhoneNumber phoneNumber;

    @Embedded
    Zipcode zipcode;
}
