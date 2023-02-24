package com.ncsgab.currentaccount.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity<Long> {

    private String name;

    private String surname;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    Set<Account> accounts = new HashSet<>();
}
