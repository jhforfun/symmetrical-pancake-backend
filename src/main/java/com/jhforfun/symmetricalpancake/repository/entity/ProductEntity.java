package com.jhforfun.symmetricalpancake.repository.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "product",
        uniqueConstraints = @UniqueConstraint(name = "uk_product_serial_number", columnNames = "serial_number"))
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;
    @Column(name = "production_type", nullable = false)
    private String productionType;
    @Column(nullable = false)
    private String name;
    @Column(name = "minimum_order_quantity", columnDefinition = "NUMERIC(10,3)", nullable = false, precision = 13, scale = 3)
    private BigDecimal minimumOrderQuantity;
    @CreationTimestamp
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @UpdateTimestamp
    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "product")
    private List<BomEntryEntity> bomEntries;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductEntity entity = (ProductEntity) o;
        return id != null && Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
