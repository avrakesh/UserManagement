package com.cit.usermanagement.jwtService;

import com.cit.usermanagement.entity.UserProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserInfoDetails implements UserDetails {

    private final UserProfile userProfile;
    private final String name;
    private final String password;
    //private final List<GrantedAuthority> authorities;

//    public UserInfoDetails(UserProfile userInfo) {
//        name = userInfo.getUsername();
//        password = userInfo.getPassword();
//        authorities = Arrays.stream(userInfo.getRole().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }

    public UserInfoDetails(UserProfile userProfile) {
       this.userProfile = userProfile;
       this.name = userProfile.getUsername();
       this.password = userProfile.getPassword();

//       this.authorities = Arrays.stream(userProfile.getGroupRoles().get(0).getRoleId().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
