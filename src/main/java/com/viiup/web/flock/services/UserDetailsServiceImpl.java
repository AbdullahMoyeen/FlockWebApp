package com.viiup.web.flock.services;

import com.viiup.web.flock.models.AuthenticatedUser;
import com.viiup.web.flock.models.UserModel;
import com.viiup.web.flock.models.UserRoleModel;
import com.viiup.web.flock.providers.IUserProvider;
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
 * Created by amoyeen on 2/23/2015.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserProvider userProvider;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel user = userProvider.getUserByEmailAddress(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        
        List<UserRoleModel> userRoles = userProvider.getUserRolesByUserId(user.getUserId());
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
        authenticatedUser.setAccountNonLocked(true);
        authenticatedUser.setAccountNonExpired(true);
        authenticatedUser.setEnabled(true);
        authenticatedUser.setAuthorities(authorities);

        return authenticatedUser;
    }
}