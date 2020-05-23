package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description: 报表实体类
 * @Author: Shunyi
 * @CreateDate: 2020/5/23
 */
@Entity
@Table(name = "reports")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Report {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 单号 */
    private String orderNo;
    /** 模板名称 */
    private String templateName;
    /** 报表文件类型 */
    private String reportFileType;
    /** 创建时间 */
    private Date dateCreated;
    /** 创建者 */
    private String creator;
}
