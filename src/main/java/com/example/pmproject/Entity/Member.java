package com.example.pmproject.Entity;

import com.example.pmproject.Constant.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member")
@SequenceGenerator(sequenceName = "member_SEQ", name = "member_SEQ", allocationSize = 1)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_SEQ")
    private Long memberId; //멤버 번호

    @Column(nullable = false)
    private String email; //멤버 이메일(아이디)

    @Column(nullable = false)
    private String password; //멤버 비밀번호

    @Column(nullable = false)
    private String name; //멤버 닉네임

    @Column
    @Enumerated(EnumType.STRING)
    private Role role; //멤버 역할 (관리자 : ROLE_ADMIN / 유저 : ROLE_USER)

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PmUse> useList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ProductComment> productCommentList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ShopComment> shopCommentList;
}