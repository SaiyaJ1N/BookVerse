package com.onlinebookshop.repository.role;

import com.onlinebookshop.model.Role;
import org.aspectj.apache.bcel.generic.LocalVariableGen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, LocalVariableGen> {
    Role getRoleByName(Role.RoleName roleName);
}
