package com.example.springforumapp.files.models.dto;

import com.example.springforumapp.files.models.UpFileType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpFileOutDTO {
    private long id;
    private String url;
    private String name;
    private UpFileType type;
}
