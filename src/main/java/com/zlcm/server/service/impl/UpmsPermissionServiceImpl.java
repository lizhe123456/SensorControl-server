package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.upms.UpmsPermissionMapper;
import com.zlcm.server.model.upms.UpmsPermission;
import com.zlcm.server.service.UpmsPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UpmsPermissionServiceImpl extends BaseServiceImpl<UpmsPermission,UpmsPermissionMapper>
        implements UpmsPermissionService {

}
