package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @description 配件进口实体类
 * @author Shunyi Chen
 * @date 2020/4/11
 */
@Entity
@Table(name = "imports")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Import {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 名称 */
    private String name;
}
