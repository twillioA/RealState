package com.RealState.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OwnerPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ownerPlanId;

    @Column(name = "user_id", unique = true)
    private long userId;

    private String email;

    private boolean subscriptionActive;

    @Column(name = "subscription_active_date")
    private LocalDate subscriptionActiveDate;

    @Column(name = "subscription_expiration_date")
    private LocalDate subscriptionExpirationDate;

    private int numberOfDays;

    // Constructors, getters, setters, and other methods
}
