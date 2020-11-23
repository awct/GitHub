package com.company.dao;

import com.company.domain.Admin;

public interface AdminDao {
    Admin getAdminByNameAndPassword(String username,String password);
}
