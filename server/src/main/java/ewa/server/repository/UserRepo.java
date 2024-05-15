package ewa.server.repository;

import ewa.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    void deleteUserById(Integer id);

    User findByUsername(String username);

    @Query("SELECT e from User e where e.username =:username AND e.password =:password ")
    User findByUsernameAndPassword(String username, String password);
}
