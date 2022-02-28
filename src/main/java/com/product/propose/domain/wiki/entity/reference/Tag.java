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

    public static Tag create(String name) {
        return Tag.builder()
                .name(name)
                .build();
    }

    @Override
    public String toString() {
        return this.id + " / " + this.name;
    }
}
