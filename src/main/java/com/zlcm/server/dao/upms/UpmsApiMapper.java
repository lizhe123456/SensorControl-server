package com.zlcm.server.dao.upms;

import com.zlcm.server.model.upms.UpmsPermission;
import com.zlcm.server.model.upms.UpmsRole;

import java.util.List;

public interface UpmsApiMapper {

    // 根据用户id获取所拥有的权限
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

    // 根据用户id获取所属的角色
    List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);
}
