package tomato_th.project_tomato.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tomato_th.project_tomato.model.User;
import tomato_th.project_tomato.model.User_roles;
import tomato_th.project_tomato.repository.UserRepository;


import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user== null)throw new UsernameNotFoundException(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (User_roles role:user.getListUserRole())
        {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleId().getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),
                grantedAuthorities);
    }
}
