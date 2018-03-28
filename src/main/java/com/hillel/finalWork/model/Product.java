package com.hillel.finalWork.model;

import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@ToString(callSuper = true)
@Entity
@Table
//@Audited
@EntityListeners({AuditingEntityListener.class})
public class Product extends AbstractIdentifiableObject {

    @Getter
    @Setter
    @Column
    private String name;

    @Setter
    @Getter
    @Column(precision = 10, scale = 2, columnDefinition="DECIMAL(10,2)")
    private BigDecimal price;

    @Getter
    @Setter
    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Orders order;
}
