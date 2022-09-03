package com.kk.strong.service;

import com.kk.strong.model.Account;
import com.kk.strong.model.AccountRole;
import com.kk.strong.model.GymUser;
import com.kk.strong.repository.AccountRepository;
import com.kk.strong.repository.GymUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final GymUserRepository gymUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Account not found"));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        account.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
        return new User(account.getUsername(), account.getPassword(), authorities);
    }

    public Account saveAccount(Account account) {
        log.info("Saving account: {}", account.toString());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.getRoles().add(AccountRole.REGULAR_USER);
        GymUser gymUser = new GymUser();
        gymUser.setAccount(account);
        account.setGymUser(gymUser);

        gymUserRepository.save(gymUser);
        return accountRepository.save(account);
    }

    public void addRoleToAccount(Account account, AccountRole accountRole) {
        log.info("Adding role: {} to account with username: {}", accountRole.name(), account.getUsername());
        account.getRoles().add(accountRole);
    }


}
