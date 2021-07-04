package com.zm.aidian.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private Integer id;
    private String name;
    private String pwd;

    public Admin(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }
}
