package com.rateye.shop_cms.common.util;

import org.springframework.security.core.GrantedAuthority;

import java.util.*;

public class PermissionGenerator {
    public static String[] getPermissions(Collection<? extends GrantedAuthority> roles) {
        String[] permissions = {"Customer", "Staff", "Store owner", "Super admin"};
        List<GrantedAuthority> roleList = new ArrayList<>(roles);
        String role = roleList.get(0).getAuthority().toLowerCase(Locale.ROOT);
        role = role.substring(0, 1).toUpperCase() + role.substring(1);
        System.out.println(role);
        String[] userPermissions = new String[Arrays.asList(permissions).indexOf(role.replace("_", " "))+1];
        System.arraycopy(permissions, 0, userPermissions, 0, userPermissions.length);
        return userPermissions;
    }
}
