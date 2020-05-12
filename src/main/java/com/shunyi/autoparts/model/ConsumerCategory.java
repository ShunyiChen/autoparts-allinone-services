package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @description 客户分类
 * @author Shunyi
 * @date 2020/3/23
 */
@Entity
@Table(name = "consumer_categories")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ConsumerCategory {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 类目名称 */
    private String name;
    /** 父类目ID */
    private Long parentId;
    /** 是否父节点 */
    private Boolean parent;
}
