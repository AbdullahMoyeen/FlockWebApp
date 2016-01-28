package com.viiup.web.flock.services;

import com.viiup.web.flock.providers.IUserProvider;
import com.viiup.web.flock.models.AuthenticatedUser;
import com.viiup.web.flock.models.Customer;
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

        Customer customer = userProvider.getCustomerByEmailAddress(username);
        if(customer == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("CUSTOMER"));

        AuthenticatedUser authenticatedUser = new AuthenticatedUser();

        authenticatedUser.setUserId(customer.getCustomerID());
        authenticatedUser.setUsername(customer.getEmailAddress());
        authenticatedUser.setPassword(customer.getPassword());
        authenticatedUser.setAccountNonExpired(true);
        authenticatedUser.setAccountNonLocked(true);
        authenticatedUser.setCredentialsNonExpired(true);
        authenticatedUser.setEnabled(true);
        authenticatedUser.setAuthorities(authorities);

        return authenticatedUser;
    }
}
