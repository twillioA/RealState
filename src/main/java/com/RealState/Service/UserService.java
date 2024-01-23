package com.RealState.Service;

import com.RealState.Entity.User;
import com.RealState.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // Save the user to the database
        return userRepository.save(user);
    }
//Help me to search the record based on email Id.
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }
// it set the email as true.It is getting the email value to there object(User user) only we are updating boolean value as email verified to true and save it.
    public void verifyEmail(User user) {
        user.setEmailVerified(true);
        userRepository.save(user);
    }
}
