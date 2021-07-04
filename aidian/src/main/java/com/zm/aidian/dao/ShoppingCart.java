package com.zm.aidian.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    private Integer id;
    private String name;
    private float price;
    private Integer sums;

    public ShoppingCart( float price, Integer sums) {
        this.price = price;
        this.sums = sums;
    }

}
