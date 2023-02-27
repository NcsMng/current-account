package com.ncsgab.currentaccount.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"account"}, callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@ToString(callSuper = true)
public class Transaction extends BaseEntity<Long> {

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
