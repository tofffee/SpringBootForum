package com.example.springforumapp.comments.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentDTO {
    @NotNull
    @Size(min = 1,message = "Comment error (text of publication is too small)")
    private String textOfComment;


    private int responed_to_comment_id;

    public  CommentDTO( ) { }

    public CommentDTO(String textOfComment, int responed_to_comment_id) {
        this.textOfComment = textOfComment;
        this.responed_to_comment_id = responed_to_comment_id;
    }

    public String getTextOfComment() {
        return textOfComment;
    }

    public void setTextOfComment(String textOfComment) {
        this.textOfComment = textOfComment;
    }

    public int getResponed_to_comment_id() {
        return responed_to_comment_id;
    }

    public void setResponed_to_comment_id(int responed_to_comment_id) {
        this.responed_to_comment_id = responed_to_comment_id;
    }
}

