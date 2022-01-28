package com.product.propose.domain.wiki.entity.reference;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "tag")
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
