package com.example.springforumapp.publications.models.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationInDTO {
    @NotNull
    @Size(min = 3,message = "Publication error (name of publication is too small)")
    private String name;

    @NotNull
    @Size(min = 3,message = "Publication error (text of publication is too small)")
    private String text;

    @JsonProperty("filesId")
    private List<Integer> upFilesId;
}
