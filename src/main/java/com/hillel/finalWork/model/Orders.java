package com.hillel.finalWork.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@ToString(callSuper = true, exclude = {"products", "user"})
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
//@Audited
@EntityListeners({AuditingEntityListener.class})
public class Orders extends AbstractIdentifiableObject {

    @Setter
    @Getter
    @Column(columnDefinition = "DATE")
    private Date created;

    @Getter
    @Setter
    @Column
    @Enumerated(EnumType.STRING)
//    @LastModifiedDate
    private Status status;

    @Setter
    @Getter
    @JsonBackReference
    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> products;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
//    @LastModifiedBy
    private User user;

    @Getter
    @Setter
//    @CreatedBy
//    @Column(columnDefinition = "varchar(255) default 'system'")
    private String createdBy;

}
