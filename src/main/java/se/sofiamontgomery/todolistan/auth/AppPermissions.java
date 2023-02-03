package se.sofiamontgomery.todolistan.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AppPermissions {
    ADMIN_CAN_DELETE("admin:delete"),
    ADMIN_CAN_READ("admin:read"),
    USER_CAN_READ("user:read"),
    USER_CAN_UPDATE("admin:update"),
    USER_CAN_CREATE("user:post"),
    USER_CAN_DELETE("user:delete");

    private final String appPermission;
}
