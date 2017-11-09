package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.upms.UpmsUserPermissionMapper;
import com.zlcm.server.model.upms.UpmsUserPermission;
import com.zlcm.server.service.UpmsUserPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UpmsUserPermissionServiceImpl extends BaseServiceImpl<UpmsUserPermission,UpmsUserPermissionMapper>
        implements UpmsUserPermissionService{

}
