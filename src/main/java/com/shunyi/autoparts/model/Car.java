package com.shunyi.autoparts.model;

import javax.persistence.*;

/** 车型表 */
@Entity
@Table(name = "cars")
public class Car {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 车型编码 */
    private String uniqueId;
    /** 车型 */
    private String model;

    public Car() {}

    public Car(String uniqueId, String model) {
        this.uniqueId = uniqueId;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", uniqueId='" + uniqueId + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
