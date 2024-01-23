package com.RealState.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import static com.RealState.Service.EmailVerificationService.emailOtpMapping;
@Service
public class EmailService {
@Autowired
private JavaMailSender javaMailSender;
private final UserService userService;

    public EmailService(JavaMailSender javaMailSender, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }

   private String genertateOtp(){
        return String.format("%06d", new java.util.Random().nextInt(1000000));
   }
    public Map<String, String> sendOtpEmail(String email){
        String otp = genertateOtp();

        //after generating an otp, before sending it.I want to store this in a hashmap as a key value pair(it generate a otp stores into this variable.To acess this variable we need to do static import
        //practical example where hashmap can be used?sir,I can use hashmap for otp verification where in temporarily we can store otp number into a hashmap(and in hashmap content will be stored in a key value pair).Before sending otp storing that in (below line)it contain value of otp and for this verification should happen in emailverificationService layer
        emailOtpMapping.put(email, otp);




        // Sent OTP to the user's email
        sendEmail(email, "OTP for Email verification", "Your OTP is:" + otp);
        //Return a success response
Map<String,String> response = new HashMap<>();
response.put("status","success");
response.put("message", "OTP sent successfully");
return response;
    }
private void sendEmail(String to,String subject, String text){

    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("your.gmail.com");
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    javaMailSender.send(message);
}




}

