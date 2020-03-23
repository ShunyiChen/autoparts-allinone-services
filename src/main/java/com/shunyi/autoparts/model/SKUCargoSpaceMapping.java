package com.shunyi.autoparts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @description SKU与货位映射关系
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "sku_cargospace_mappings")
public class SKUCargoSpaceMapping {

    @Embeddable
    public static class Id implements Serializable {
        /** SKU ID */
        protected Long skuId;
        /** 货位ID */
        protected Long cargoSpaceId;

        public Id() {}

        public Id(Long skuId, Long cargoSpaceId) {
            this.skuId = skuId;
            this.cargoSpaceId = cargoSpaceId;
        }

        public Long getSkuId() {
            return skuId;
        }

        public void setSkuId(Long skuId) {
            this.skuId = skuId;
        }

        public Long getCargoSpaceId() {
            return cargoSpaceId;
        }

        public void setCargoSpaceId(Long cargoSpaceId) {
            this.cargoSpaceId = cargoSpaceId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return Objects.equals(skuId, id.skuId) &&
                    Objects.equals(cargoSpaceId, id.cargoSpaceId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(skuId, cargoSpaceId);
        }
    }
    /** 内嵌ID */
    @EmbeddedId
    protected Id id = new Id();
    /** SKU */
    @ManyToOne
    @JoinColumn(
            name = "skuId",
            insertable = false, updatable = false
    )
    @JsonIgnore
    protected SKU sku;
    /** 货位 */
    @ManyToOne
    @JoinColumn(
            name = "cargoSpaceId",
            insertable = false, updatable = false
    )
    @JsonIgnore
    protected Slot cargoSpace;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public SKU getSku() {
        return sku;
    }

    public void setSku(SKU sku) {
        this.sku = sku;
    }

    public Slot getCargoSpace() {
        return cargoSpace;
    }

    public void setCargoSpace(Slot cargoSpace) {
        this.cargoSpace = cargoSpace;
    }
}
