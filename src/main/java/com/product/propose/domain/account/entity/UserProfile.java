package com.product.propose.domain.account.entity;

import com.product.propose.domain.account.web.dto.data.UserProfileCreateForm;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user_profile")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "post_code")
    private String postCode;
    @Column(name = "address")
    private String address;

    @Column(name = "email_recv")
    private boolean emailRecv;
    @Column(name = "phone_recv")
    private boolean phoneRecv;

    public static UserProfile createUserProfile(UserProfileCreateForm createForm) {
        return UserProfile.builder()
                .userName(createForm.getUserName())
                .phoneNumber(createForm.getPhoneNumber())
                .postCode(createForm.getPostCode())
                .address(createForm.getAddress())
                .emailRecv(false)
                .phoneRecv(false)
                .build();
    }
}
