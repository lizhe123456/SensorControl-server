package com.zlcm.server.model.upms;

import java.io.Serializable;

public class UpmsRolePermission implements Serializable {
    private Integer rolePermissionId;

    private Integer roleId;

    private Integer permissionId;

    private static final long serialVersionUID = 1L;

    public Integer getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}