package fr.akbarkhan.mediatheque.security;

import static fr.akbarkhan.mediatheque.security.ApplicationUserPermission.*;

import com.google.common.collect.Sets;
import java.util.Set;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(BOOK_READ)),
    ADMIN(Sets.newHashSet(BOOK_READ, BOOK_WRITE, USER_READ, USER_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(BOOK_READ, USER_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
