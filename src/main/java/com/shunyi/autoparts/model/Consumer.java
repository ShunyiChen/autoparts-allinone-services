package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @description 客户
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "consumers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Consumer {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 客户编码 */
    private String code;
    /** 客户单位名称 */
    private String name;
    /** 联系人 */
    private String contact;
    /** 电话好 */
    private String phone;
    /** Email */
    private String email;
    /** 车牌号 */
    private String licensePlate;
    /** 创建时间 */
    private Date dateCreated;
    /** 创建者 */
    private String creator;
    /** 更新时间 */
    private Date dateUpdated;
    /** 更新者 */
    private String updater;
    /** 更新次数 */
    private Integer updatedCount;
    /** 删除时间 */
    private Date dateDeleted;
    /** 删除标记 */
    private Boolean deleteFlag;
    /** 删除者 */
    private String deleter;
}
