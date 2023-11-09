package com.example.pmproject.DTO;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long productId;
    private String name;
    @NotNull(message = "상품 설명은 필수 입력 사항입니다.")
    private String content;
    private Integer price;
    private String img;
}
