package com.talharic.resttemplate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

    private Long id;

    @NotNull
    private Long userID;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String body;

}
