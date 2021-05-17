package com.example.video.service;

import com.example.video.pojo.Admin;

import java.util.List;

public interface AdminService {

    Admin getAdminByNameAndPassword(String adminName, String password);

    List<Admin> listAdmin();

    void saveAdmin(Admin admin);


}
