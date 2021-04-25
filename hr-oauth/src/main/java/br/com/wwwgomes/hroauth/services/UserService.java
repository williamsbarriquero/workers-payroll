package br.com.wwwgomes.hroauth.services;

import br.com.wwwgomes.hroauth.entities.User;
import br.com.wwwgomes.hroauth.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserFeignClient userFeignClient;

    @Autowired
    public UserService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    public User findByEmail(String email) {
        User user = userFeignClient.findByEmail(email).getBody();
        if (user == null) {
            LOGGER.error("Email not found: " + email);
            throw new IllegalArgumentException("Email not found");
        }
        LOGGER.info("Email found: " + email);

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userFeignClient.findByEmail(username).getBody();
        if (user == null) {
            LOGGER.error("Email not found: " + username);
            throw new UsernameNotFoundException("Email not found");
        }
        LOGGER.info("Email found: " + username);

        return user;
    }
}
