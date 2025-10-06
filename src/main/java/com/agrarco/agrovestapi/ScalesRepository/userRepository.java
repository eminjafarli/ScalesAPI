package com.agrarco.agrovestapi.ScalesRepository;
import com.agrarco.agrovestapi.ScalesEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
