package com.RealState.Service;

import com.RealState.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationService {

    //wherever you want to use this emailotpmapping i can use this by doing static import**
    static final Map<String,String> emailOtpMapping = new HashMap<>();

    //creating hashmap for response(for status and message)
    Map<String, String> response = new HashMap<>();
    @Autowired
    private UserService userService;
    //this is once responsible to verify the otp
    public Map<String, String>verifyOtp(String email, String otp){
        //verification(this store otp sent as a email to you abd need to be verified with this String otp if both matches then it correct)
        String storedOtp = emailOtpMapping.get(email);
if(storedOtp != null && storedOtp.equals(otp)){
    //Fectch user by email amd mark email as verified(once true let the entire details in DB able to get through email)
    User user = userService.getUserByEmail(email);
    //if present in DB it not equal now thus verify user will set true before it will be false.in user service
    if(user!= null) {
        userService.verifyEmail(user);
        response.put("status", "success");
        response.put("message", "Email verified successfully");
    }else {
        response.put("status", "error");
        response.put("message", "User not Found");
    }
}else {
    response.put("status", "error");
    response.put("message", "Invaild Otp");
}
return response;

    }
}
