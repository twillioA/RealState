package com.RealState.Service;

import com.RealState.Entity.OwnerPlan;
import com.RealState.Repository.OwnerPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OwnerPlanService {

    @Autowired
    private OwnerPlanRepository ownerPlanRepository;

    public OwnerPlan createSubscription(long userId, String email, int numberOfDays) {
        OwnerPlan ownerPlan = new OwnerPlan();
        ownerPlan.setUserId(userId);
        ownerPlan.setEmail(email);
        ownerPlan.setSubscriptionActive(true);
        ownerPlan.setSubscriptionActiveDate(LocalDate.now());
        ownerPlan.setNumberOfDays(numberOfDays);
        ownerPlan.setSubscriptionExpirationDate(LocalDate.now().plusDays(numberOfDays));

        return ownerPlanRepository.save(ownerPlan);
    }

    public Optional<OwnerPlan> getOwnerPlanById(long ownerPlanId) {
        return ownerPlanRepository.findById(ownerPlanId);
    }

    // Other methods for updating, canceling, or querying subscriptions
}
