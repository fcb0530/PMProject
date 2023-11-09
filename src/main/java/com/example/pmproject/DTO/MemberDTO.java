package com.example.pmproject.DTO;

import com.example.pmproject.Constant.Role;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
    private Long memberId; //멤버 아이디
    @NotNull(message = "이메일은 필수 입력 사항입니다.")
    private String email;
    @NotNull(message = "비밀번호는 필수 입력 사항입니다.")
    private String password;
    @NotNull(message = "닉네임은 필수 입력 사항입니다.")
    private String name;
    private Role role;
}
