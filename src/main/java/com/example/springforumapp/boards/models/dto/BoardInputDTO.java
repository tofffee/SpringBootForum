package com.example.springforumapp.boards.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardInputDTO {
    @Size(min = 3, message = "Board error (name of board is too small)")
    @NotNull
    private String name;
}
