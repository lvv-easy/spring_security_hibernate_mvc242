package project.dao;

import org.springframework.stereotype.Repository;
import project.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> index() {
        return em.createQuery("FROM User person", User.class).getResultList();
    }

    @Override
    public User show(Long id) {
        return em.find(User.class, id);
    }


    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void update(User updatedUser, Long id) {
        User user = em.find(User.class, id);
        user.setName(updatedUser.getName());
        user.setSurname(updatedUser.getSurname());
        user.setSalary(updatedUser.getSalary());
        user.setDepartment(updatedUser.getDepartment());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setRoles(updatedUser.getRoles());
        em.merge(user);
    }

    @Override
    public void delete(Long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public User findPersonByEmail(String email) {
        return em.createQuery("FROM User user WHERE user.email =:email", User.class)
                .setParameter("email", email).getSingleResult();
    }

    @Override
    public User findPersonByName(String name) {
        return em.createQuery("FROM User user WHERE user.name =:name", User.class)
                .setParameter("name", name).getSingleResult();
    }
}
