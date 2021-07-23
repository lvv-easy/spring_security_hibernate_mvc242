package project.service;

import project.entity.User;

import java.util.List;

public interface UserService {
    public List<User> index();

    public User show(final Long id);

    public void save(User user);

    public void update(User updatedUser, Long id);

    public void delete(Long id);

    public User findPersonByEmail(String email);

    public User findPersonByName(String name);
}
