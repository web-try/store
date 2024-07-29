package com.cy.store.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//购物车数据
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartVo implements Serializable {
    private Integer cid;

    private Integer uid;

    private Integer pid;

    private Long price;

    private Integer num;

    private String title;

    private String image;

    private Long realPrice;

}
