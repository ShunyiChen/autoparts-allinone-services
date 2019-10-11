package com.shunyi.autoparts.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/** 货位 */
@Entity
@Table(name = "cargo_spaces")
public class CargoSpace {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 仓库ID */
    private Long wareHouseId;
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
    /** SKU与货位映射集合 */
    @OneToMany(mappedBy = "cargoSpace")
    private Set<SKUCargoSpaceMapping> SKUCargoSpaceMappings = new HashSet<>();

    public CargoSpace() {}

    public CargoSpace(Long id, Long wareHouseId, String name, String level_1, String level_2, String level_3, String level_4) {
        this.id = id;
        this.wareHouseId = wareHouseId;
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

    public Long getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(Long wareHouseId) {
        this.wareHouseId = wareHouseId;
    }

    public Set<SKUCargoSpaceMapping> getSKUCargoSpaceMappings() {
        return SKUCargoSpaceMappings;
    }

    public void setSKUCargoSpaceMappings(Set<SKUCargoSpaceMapping> SKUCargoSpaceMappings) {
        this.SKUCargoSpaceMappings = SKUCargoSpaceMappings;
    }

    @Override
    public String toString() {
        return "CargoSpace{" +
                "id=" + id +
                ", wareHouseId=" + wareHouseId +
                ", name='" + name + '\'' +
                ", level_1='" + level_1 + '\'' +
                ", level_2='" + level_2 + '\'' +
                ", level_3='" + level_3 + '\'' +
                ", level_4='" + level_4 + '\'' +
                '}';
    }
}