package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.upms.UpmsRoleMapper;
import com.zlcm.server.model.upms.UpmsRole;
import com.zlcm.server.service.UpmsRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UpmsRoleServiceImpl extends BaseServiceImpl<UpmsRole,UpmsRoleMapper>
        implements UpmsRoleService{
}
