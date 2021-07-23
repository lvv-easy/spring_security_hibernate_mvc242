package project.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.dao.UserDAO;
import project.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDAO userDAO;

    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findPersonByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User 's%' was not found", email));
        }
        return user;
    }
}
