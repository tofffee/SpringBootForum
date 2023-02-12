package com.example.springforumapp.boards.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardInDTO {
    @Size(min = 3, message = "Board error (name of board is too small)")
    @Size(min = 20, message = "Board error (name of board is too big)")
    @NotNull
    private String name;
}
