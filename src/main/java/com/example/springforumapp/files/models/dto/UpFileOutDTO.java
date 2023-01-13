package com.example.springforumapp.files.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpFileOutDTO {
    private int id;
    private String url;
    private String name;
    private String type;
}
