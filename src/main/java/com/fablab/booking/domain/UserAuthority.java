package com.fablab.booking.domain;

import com.fablab.booking.domain.common.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "authorities")
public class UserAuthority extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @Column(unique=true)
    private UserRole role;
}
