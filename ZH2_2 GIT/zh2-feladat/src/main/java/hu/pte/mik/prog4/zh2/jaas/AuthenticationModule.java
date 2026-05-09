package hu.pte.mik.prog4.zh2.jaas;

import at.favre.lib.crypto.bcrypt.BCrypt;
import hu.pte.mik.prog4.zh2.entity.RoleEntity;
import hu.pte.mik.prog4.zh2.entity.UserEntity;
import hu.pte.mik.prog4.zh2.repository.RoleRepository;
import hu.pte.mik.prog4.zh2.repository.UserRepository;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthenticationModule implements LoginModule {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private Subject subject;
    private CallbackHandler callbackHandler;
    private String login;
    private List<String> userGroups;

    public AuthenticationModule() {
        this.userRepository = new UserRepository();
        this.roleRepository = new RoleRepository();
    }

    @Override
    public void initialize(Subject subject,
                           CallbackHandler callbackHandler,
                           Map<String, ?> sharedState,
                           Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        try{
            Callback[] callbacks = new Callback[2];
            callbacks[0] = new NameCallback("login");
            callbacks[1] = new PasswordCallback("password", true);

            this.callbackHandler.handle(callbacks);
            String name = ((NameCallback) callbacks[0]).getName();
            char[] password = ((PasswordCallback) callbacks[1]).getPassword();

            if(name != null)
            {
                UserEntity userEntity = this.userRepository.findByUsername(name);
                if(userEntity != null)
                {
                    BCrypt.Result verify = BCrypt.verifyer().verify(password, userEntity.getPassword());
                    if(verify.verified)
                    {
                        this.login = name;
                        this.userGroups = this.roleRepository.findRolesByUser(userEntity)
                                .stream()
                                .map(RoleEntity::getCode)
                                .collect(Collectors.toList());
                        return true;
                    }
                }
            }
            throw new LoginException("Hiba a bejelentkezéskor!");
        } catch (IOException | UnsupportedCallbackException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        this.subject.getPrincipals().add(new UserPrincipal(this.login));
        this.userGroups.stream().map(RolePrincipal::new).forEach(this.subject.getPrincipals()::add);
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().clear();
        return true;
    }

}
