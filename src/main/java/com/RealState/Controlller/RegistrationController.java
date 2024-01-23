package com.RealState.Controlller;

import com.RealState.Entity.User;
import com.RealState.Service.EmailService;
import com.RealState.Service.EmailVerificationService;
import com.RealState.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {
   //http://localhost:8080/api/register
    private UserService userService;

    public RegistrationController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    private EmailService emailService;
    @Autowired
    private EmailVerificationService emailVerificationService;
    @PostMapping("/register")
    public Map<String,String> registrationUser (@RequestBody User user){
//Register the user without email Verification
         User registeredUser = userService.registerUser(user);
//Send OTP email for email verification
       return emailService.sendOtpEmail(user.getEmail());

    }
    //http://localhost:8080/api/verify-otp?email=&otp=
    //verifying OTP
    @PostMapping("/verify-otp")
    public Map<String, String> verifyOtp(@RequestParam String email, @RequestParam String otp){
        return emailVerificationService.verifyOtp(email, otp);
    }
}
