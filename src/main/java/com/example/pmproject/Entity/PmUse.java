package com.example.pmproject.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pmUse")
@SequenceGenerator(sequenceName = "pmUse_SEQ", name = "pmUse_SEQ", allocationSize = 1)
public class PmUse extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pmUse_SEQ")
    private Long pmUseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pId")
    private Pm pm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_name", referencedColumnName = "name")
    private Member member;

    @Column(nullable = false)
    private String content;
}
