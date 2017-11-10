package com.zlcm.server.dao.upms;

import com.zlcm.server.base.BaseMapper;
import com.zlcm.server.model.upms.UpmsRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsRolePermissionMapper extends BaseMapper<UpmsRolePermission>{

    List<UpmsRolePermission> roleToPermission(@Param("role_id") Integer rId);

}