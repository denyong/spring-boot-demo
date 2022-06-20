package com.duqi.repository;

import com.duqi.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dengyong
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    /**
     *
     * @param roleName
     * @return
     */
    Optional<Role> findByName(String roleName);
}
