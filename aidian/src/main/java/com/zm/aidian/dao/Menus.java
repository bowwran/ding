package com.zm.aidian.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Menus {
    private Integer id;
    private String name;
    private int typeid;
    private String burden;
    private String brief;
    private float price;
    private Integer sums;
    private float price1;
    private Integer sums1;
    private String imgpath;


}
