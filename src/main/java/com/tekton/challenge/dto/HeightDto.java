package com.tekton.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeightDto {
    @NotNull
    @NotEmpty
    @JsonProperty(value = "height", required = true)
    private Integer[] height = new Integer[1];
}
