package com.zlcm.server.dao.upms;

import com.zlcm.server.base.BaseMapper;
import com.zlcm.server.model.upms.UpmsUserPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsUserPermissionMapper extends BaseMapper<UpmsUserPermission>{

    List<UpmsUserPermission> userToPermission(@Param("user_id") Integer uId);
}