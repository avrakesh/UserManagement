package com.cit.usermanagement.jwtService;

import com.cit.usermanagement.entity.UserProfile;
import com.cit.usermanagement.repository.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

//    @Autowired
//    private UserInfoRepository repository;
    @Autowired
    private UserProfileDao userProfileDao;

//    @Autowired
//    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserProfile> userDetail = userProfileDao.findByUsername(username);



        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

//    public String addUser(UserInfo userInfo) {
//        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
//        repository.save(userInfo);
//        return "User Added Successfully";
//    }


}

