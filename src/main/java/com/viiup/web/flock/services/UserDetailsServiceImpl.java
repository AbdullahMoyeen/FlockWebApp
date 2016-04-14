package com.viiup.web.flock.services;

import com.viiup.web.flock.businessLayer.interfaces.IUserBusinessLayer;
import com.viiup.web.flock.models.AuthenticatedUser;
import com.viiup.web.flock.models.UserModel;
import com.viiup.web.flock.models.UserRoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AbdullahMoyeen on 1/26/2016.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserBusinessLayer userBusinessLayer;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel user = userBusinessLayer.getUserByEmailAddress(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        
        List<UserRoleModel> userRoles = userBusinessLayer.getUserRolesByUserId(user.getUserId());
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserRoleModel userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
        }

        AuthenticatedUser authenticatedUser = new AuthenticatedUser();

        authenticatedUser.setUserId(user.getUserId());
        authenticatedUser.setUsername(user.getEmailAddress());
        authenticatedUser.setPassword(user.getPassword());
        authenticatedUser.setSalt(user.getSalt());
        authenticatedUser.setCredentialsNonExpired(!user.isPasswordExpired());
        authenticatedUser.setAccountNonLocked(user.getAccountStatus().equals("A"));
        authenticatedUser.setAccountNonExpired(user.getAccountStatus().equals("A"));
        authenticatedUser.setEnabled(user.getAccountStatus().equals("A"));
        authenticatedUser.setAuthorities(authorities);

        return authenticatedUser;
    }
}