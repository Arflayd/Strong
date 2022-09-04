package com.kk.strong.service;

import com.kk.strong.exception.UserNotFoundException;
import com.kk.strong.model.Account;
import com.kk.strong.model.AccountRole;
import com.kk.strong.model.GymUser;
import com.kk.strong.model.dto.GymUserDto;
import com.kk.strong.repository.AccountRepository;
import com.kk.strong.repository.GymUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Account not found"));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        account.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
        return new User(account.getUsername(), account.getPassword(), authorities);
    }

    public GymUserDto registerAccount(String username, String password, GymUserDto gymUserDto) {
        GymUser gymUser = modelMapper.map(gymUserDto, GymUser.class);
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.getRoles().add(AccountRole.REGULAR_USER);

        account.setGymUser(gymUser);
        gymUser.setAccount(account);

        gymUserRepository.save(gymUser);
        accountRepository.save(account);
        return modelMapper.map(gymUser, GymUserDto.class);
    }

    public void addRoleToAccount(String username, AccountRole accountRole) {
        log.info("Adding role: {} to account with username: {}", accountRole.name(), username);
        Account account = accountRepository
                .findByUsername(username)
                        .orElseThrow(() -> new UserNotFoundException(username));
        account.getRoles().add(accountRole);
    }
}
