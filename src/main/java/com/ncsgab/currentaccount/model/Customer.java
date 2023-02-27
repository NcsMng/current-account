package com.ncsgab.currentaccount.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@ToString(callSuper = true)
public class Customer extends BaseEntity<Long> {

    private String name;

    private String surname;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    Set<Account> accounts = new HashSet<>();
}
