package com.saspes.oauth2.security;

import com.saspes.oauth2.model.UserAccount;
import com.saspes.oauth2.repository.UserAccountRepository;
import java.util.ArrayList;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component("userDetailsService")
public class UserAccountDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserAccountDetailsService.class);
    
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {

        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase();

        UserAccount userFromDatabase = userAccountRepository.findByUsernameCaseInsensitive(lowercaseLogin);

        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database");
        }
        
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        userFromDatabase.getAuthorities().forEach((auth) -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
        });

        User loggedInUser = new User(userFromDatabase.getUsername(), userFromDatabase.getPassword(), grantedAuthorities);

        return loggedInUser;
    }

}
