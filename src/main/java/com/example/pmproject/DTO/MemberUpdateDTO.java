package com.example.pmproject.DTO;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberUpdateDTO {
    private String email;
    @NotEmpty(message = "기존 비밀번호는 필수 입력 사항입니다.")
    private String recentPassword;
    @NotEmpty(message = "새로운 비밀번호는 필수 입력 사항입니다.")
    private String newPassword;
    @NotEmpty(message = "닉네임은 필수 입력 사항입니다.")
    private String name;
}
