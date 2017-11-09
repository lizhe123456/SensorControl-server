package com.zlcm.server.model.upms;

import java.io.Serializable;

public class UpmsUserPermission implements Serializable {
    private Integer userPermissionId;

    private Integer userId;

    private Integer permissionId;

    private Byte type;

    private static final long serialVersionUID = 1L;

    public Integer getUserPermissionId() {
        return userPermissionId;
    }

    public void setUserPermissionId(Integer userPermissionId) {
        this.userPermissionId = userPermissionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}