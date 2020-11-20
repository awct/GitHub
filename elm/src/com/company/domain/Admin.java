package com.company.domain;

public class Admin {
    private int adminId;
    private String adminName;
    private String password;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(int adminId, String adminName, String password) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.password = password;
    }

    public Admin() {
    }
}
