package com.cloudheaven.booking.service;

import com.cloudheaven.booking.model.user.User;
import com.cloudheaven.booking.model.user.UserPrincipal;
import com.cloudheaven.booking.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    private  UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if(user == null)
            throw new UsernameNotFoundException("Username Not Found");
        return new UserPrincipal(user);
    }
}
