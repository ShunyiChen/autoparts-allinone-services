package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @description 配件单位实体类
 * @author Shunyi Chen
 * @date 2020/4/2
 */
@Entity
@Table(name = "units")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Unit {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 单位名称 */
    private String name;
}
