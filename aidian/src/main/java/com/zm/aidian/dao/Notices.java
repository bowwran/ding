package com.zm.aidian.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notices {
    private Integer id;
    private String name;
    private String content;
    private String times;

    public Notices(String name, String content, String times) {
        this.name = name;
        this.content = content;
        this.times = times;
    }
}
