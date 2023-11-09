package com.example.pmproject.DTO;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCommentDTO {
    private Long productCommentId;
    private Long productId;
    private String member_name;
    @NotNull(message = "내용은 필수 입력 사항입니다.")
    private String content;
}