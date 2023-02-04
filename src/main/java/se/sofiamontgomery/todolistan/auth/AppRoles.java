package se.sofiamontgomery.todolistan.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static se.sofiamontgomery.todolistan.auth.AppPermissions.*;

@AllArgsConstructor
@Getter
public enum AppRoles {

    ADMIN(Set.of(ADMIN_CAN_READ,ADMIN_CAN_DELETE)),
    USER(Set.of(USER_CAN_CREATE,USER_CAN_READ,USER_CAN_UPDATE,USER_CAN_DELETE));

    private final Set<AppPermissions> appPermissionsList;

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissionsSet = getAppPermissionsList().stream().map(
                index -> new SimpleGrantedAuthority(index.getAppPermission())
        ).collect(Collectors.toSet());

        permissionsSet.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissionsSet;
    }


}
