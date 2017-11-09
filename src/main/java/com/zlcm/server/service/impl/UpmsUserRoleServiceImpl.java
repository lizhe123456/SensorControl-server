package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.upms.UpmsUserRoleMapper;
import com.zlcm.server.model.upms.UpmsUserRole;
import com.zlcm.server.service.UpmsUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UpmsUserRoleServiceImpl extends BaseServiceImpl<UpmsUserRole,UpmsUserRoleMapper>
        implements UpmsUserRoleService{
}
