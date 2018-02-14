package com.example.demo.model.factory;


import com.example.demo.domain.entity.User;
import com.example.demo.model.security.CerberusUser;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class CerberusUserFactory {

    public static CerberusUser create(User user) throws DisabledException{
        Collection<? extends GrantedAuthority> authorities;
        try {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
        } catch (Exception e) {
            authorities = null;
        }
        return new CerberusUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getLastPasswordReset(),
                authorities,
                user.isEnabled()
        );
    }

}
