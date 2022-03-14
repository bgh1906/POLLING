package com.polling.common.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Authenticate a user from the database.
 */
@Slf4j
@Component("userDetailsService")
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

//   private final UserRepository userRepository;

   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String email) {
//      if (new EmailValidator().isValid(email, null)) {
//         User findUser = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " was not found in the database"));
//         return createSecurityUser(email, findUser);
//      }
//
//      String lowercaseLogin = email.toLowerCase(Locale.ENGLISH);
//      User findUser = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
//      return createSecurityUser(email, findUser);
      return null;
   }

//   private org.springframework.security.core.userdetails.User createSecurityUser(String lowercaseLogin, User user) {
//      List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//      grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().name()));
//      return new org.springframework.security.core.userdetails.User(user.getEmail(),
//         user.getPassword(),
//         grantedAuthorities);
//   }
}
