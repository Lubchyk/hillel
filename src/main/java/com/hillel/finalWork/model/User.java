package com.hillel.finalWork.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@ToString(callSuper = true, exclude = "orders")
@Entity
@Table
//@Audited
@EntityListeners({AuditingEntityListener.class})
public class User extends AbstractIdentifiableObject {

    @Getter
    @Setter
    @Column
    private String name;

    @Getter
    @Setter
    @Column
    private String password;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    private Role role;

    @Setter
    @Getter
    @JsonBackReference
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Orders> orders;

}
