package com.example.springforumapp.boards.models.dto;

import com.example.springforumapp.publications.models.domain.Publication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardDTO {

    private int id;

    private String name;

}
