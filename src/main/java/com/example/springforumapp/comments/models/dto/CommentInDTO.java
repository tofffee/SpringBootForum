package com.example.springforumapp.comments.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentInDTO {
    @NotNull
    @Size(min = 1,message = "Comment error (text of publication is too small)")
    private String text;

    private Long parentCommentId;
}

