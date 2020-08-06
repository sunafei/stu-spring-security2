package com.sun.security.service;

import com.sun.security.exception.BadRequestException;
import com.sun.security.service.dto.JwtUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public JwtUserDto loadUserByUsername(String username) {
        if ("002".equals(username)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if ("003".equals(username)) {
            throw new BadRequestException("账号未激活");
        }

        return new JwtUserDto(
                username,
                new BCryptPasswordEncoder().encode("123456"),
                new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority("1")))
        );
    }
}
