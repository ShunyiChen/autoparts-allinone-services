package com.shunyi.autoparts.model;

import javax.persistence.*;

/** 货位 */
@Entity
@Table(name = "cargo_spaces")
public class CargoSpace {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 名称 */
    private String name;
    /** 第几区货架 */
    private String level_1;
    /** 第几个货架 */
    private String level_2;
    /** 第几层货架 */
    private String level_3;
    /** 第几个存放盒位 */
    private String level_4;

    public CargoSpace() {}

    public CargoSpace(String name, String level_1, String level_2, String level_3, String level_4) {
        this.name = name;
        this.level_1 = level_1;
        this.level_2 = level_2;
        this.level_3 = level_3;
        this.level_4 = level_4;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel_1() {
        return level_1;
    }

    public void setLevel_1(String level_1) {
        this.level_1 = level_1;
    }

    public String getLevel_2() {
        return level_2;
    }

    public void setLevel_2(String level_2) {
        this.level_2 = level_2;
    }

    public String getLevel_3() {
        return level_3;
    }

    public void setLevel_3(String level_3) {
        this.level_3 = level_3;
    }

    public String getLevel_4() {
        return level_4;
    }

    public void setLevel_4(String level_4) {
        this.level_4 = level_4;
    }

    @Override
    public String toString() {
        return "CargoSpace{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level_1='" + level_1 + '\'' +
                ", level_2='" + level_2 + '\'' +
                ", level_3='" + level_3 + '\'' +
                ", level_4='" + level_4 + '\'' +
                '}';
    }
}
