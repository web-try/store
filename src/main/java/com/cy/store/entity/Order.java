package com.cy.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity{
    private Integer oid;

    private Integer uid;

    private String recvName;

    private String recvPhone;

    private String recvProvince;

    private String recvCity;

    private String recvArea;

    private String recvAddress;

    private Long totalPrice;

    private Integer status;

    private Date orderTime;

    private Date payTime;
}
