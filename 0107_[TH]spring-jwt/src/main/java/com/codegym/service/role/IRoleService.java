package com.codegym.service.role;

import com.codegym.model.Role;
import com.codegym.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

//----- interface UserDetailsService chứa phương thức load tất cả user từ trong database bằng username
public interface IRoleService extends IGeneralService<Role>, UserDetailsService {
    Role findByName(String name);
}
