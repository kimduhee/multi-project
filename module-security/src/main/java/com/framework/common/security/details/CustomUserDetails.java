package com.framework.common.security.details;

import com.framework.common.security.dto.UserInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private UserInfo userInfo;

    public CustomUserDetails(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        userInfo.getRoleList().forEach(r -> {
            authorities.add(() -> r);
        });
        return List.of();
    }

    @Override
    public String getPassword() {
        return userInfo.getUserPassword();
    }

    @Override
    public String getUsername() {
        return String.valueOf(userInfo.getUserId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
