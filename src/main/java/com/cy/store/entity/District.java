package com.cy.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** 省/市/区数据的实体类 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class District implements Serializable {
    private Integer id;
    private String parent;
    private String code;
    private String name;
}
