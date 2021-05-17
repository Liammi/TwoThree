package com.example.video.service.serviceImpl;

import com.example.video.dao.AdminDao;
import com.example.video.pojo.Admin;
import com.example.video.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin getAdminByNameAndPassword(String adminName, String password) {
        return adminDao.getAdminByNameAndPassword(adminName,password);
    }

    @Override
    public List<Admin> listAdmin() {
        return adminDao.listAdmin();
    }

    @Override
    public void saveAdmin(Admin admin) {
        adminDao.saveAdmin(admin);
    }
}
