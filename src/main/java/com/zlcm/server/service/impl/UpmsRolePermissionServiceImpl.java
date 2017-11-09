package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.upms.UpmsRolePermissionMapper;
import com.zlcm.server.model.upms.UpmsRolePermission;
import com.zlcm.server.service.UpmsRolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UpmsRolePermissionServiceImpl extends BaseServiceImpl<UpmsRolePermission,UpmsRolePermissionMapper>
        implements UpmsRolePermissionService{
}
