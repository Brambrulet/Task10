package end.of.study.springboot.service;

import end.of.study.springboot.entity.Role;
import end.of.study.springboot.entity.User;
import end.of.study.springboot.repository.UserRepository;
import java.util.Objects;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("userDetailsService")
public class UserService implements UserDetailsService {
    @Setter(onMethod_=@Autowired)
    private UserRepository repository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService() {
        boolean x = true;
    }

    @Transactional
    public User findByName(String name) {
        assert !StringUtils.isEmpty(name);

        return repository.getByName(name).orElseThrow(() -> new UsernameNotFoundException(name));
    }

    @Transactional
    public User getById(long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public boolean isAdmin(User user) {
        assert !Objects.isNull(user);

        return RoleService.isAdmin(user.getRole());
    }

    @Transactional
    public User createUser(String name, String passw) {
        return createUser(name, passw, false);
    }

    @Transactional
    public User createAdmin(String name, String passw) {
        return createUser(name, passw, true);
    }

    public User createUser(String name, String passw, boolean admin) {
        assert !StringUtils.isEmpty(name) && !StringUtils.isEmpty(passw);

        RoleService roleService = new RoleService();
        Role role = admin ? roleService.getAdminRole() : roleService.getUserRole();

        return repository.save(new User().setName(name).setPassword(passwordEncoder.encode(passw)).setRole(role));
    }

    public boolean matchPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getByName(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(user.getPassword())
                .disabled(false)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .roles(user.getRole().getName())
                .authorities(RoleService.ADMIN_ROLE_NAME.equals(user.getRole().getName()) ? "isAdmin" : "isUser")
                .build();
    }
}
