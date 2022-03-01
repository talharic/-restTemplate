package com.talharic.resttemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PostDto {

    @NotNull
    private Long userId;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String body;

}
