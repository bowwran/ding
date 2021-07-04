package com.zm.aidian.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private Integer id;
    private Integer userid;
    private Integer menuid;
    private Integer menusum;

    private String times;
    private Integer delivery;

    public Orders(Integer userid, Integer menuid, Integer menusum, String times, Integer delivery) {
        this.userid = userid;
        this.menuid = menuid;
        this.menusum = menusum;
        this.times = times;
        this.delivery = delivery;
    }
}
