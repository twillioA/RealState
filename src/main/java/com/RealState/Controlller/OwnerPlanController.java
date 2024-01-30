package com.RealState.Controlller;

import com.RealState.Entity.OwnerPlan;
import com.RealState.Service.OwnerPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/owner-plans")
public class OwnerPlanController {
//http://localhost:8080/api/owner-plans/subscribe?userId=123&email=test@example.com&numberOfDays=30
    @Autowired
    private OwnerPlanService ownerPlanService;

    @PostMapping("/subscribe")
    public ResponseEntity<OwnerPlan> subscribe(@RequestParam long userId,
                                               @RequestParam String email,
                                               @RequestParam int numberOfDays) {
        OwnerPlan ownerPlan = ownerPlanService.createSubscription(userId, email, numberOfDays);
        return ResponseEntity.ok(ownerPlan);
    }
    //http://localhost:8080/api/owner-plans/1

    @GetMapping("/{ownerPlanId}")
    public ResponseEntity<OwnerPlan> getOwnerPlan(@PathVariable long ownerPlanId) {
        Optional<OwnerPlan> ownerPlan = ownerPlanService.getOwnerPlanById(ownerPlanId);
        return ownerPlan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Other endpoints for updating, canceling, or querying subscriptions
}
