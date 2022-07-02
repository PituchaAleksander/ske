package com.ske.library.securityJwt.domain.authenticationFacade;

import com.ske.library.securityJwt.domain.services.UserDetailsImpl;
import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();

    UserDetailsImpl getUserDetailsImpl();
}
