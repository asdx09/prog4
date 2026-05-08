package hu.pte.mik.prog4.jaas;

import java.security.Principal;

public class UserPrincipal implements Principal {

    private final String name;

    public UserPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
