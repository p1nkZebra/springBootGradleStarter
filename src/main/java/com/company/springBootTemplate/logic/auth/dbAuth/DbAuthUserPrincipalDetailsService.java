package com.company.springBootTemplate.logic.auth.dbAuth;

import com.company.springBootTemplate.domain.DbAuthUser;
import com.company.springBootTemplate.repository.DbAuthUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DbAuthUserPrincipalDetailsService implements UserDetailsService {
    private final static Logger LOG = LoggerFactory.getLogger(DbAuthUserPrincipalDetailsService.class);

    private DbAuthUserRepository dbAuthUserRepository;

    public DbAuthUserPrincipalDetailsService(DbAuthUserRepository dbAuthUserRepository) {
        this.dbAuthUserRepository = dbAuthUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DbAuthUser user = dbAuthUserRepository.findByUsername(username);
        return new DbAuthUserPrincipal(user);
    }
}
