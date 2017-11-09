package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.upms.UpmsOrganizationMapper;
import com.zlcm.server.model.upms.UpmsOrganization;
import com.zlcm.server.service.UpmsOrganizationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UpmsOrganizationServiceImpl extends BaseServiceImpl<UpmsOrganization,UpmsOrganizationMapper>
        implements UpmsOrganizationService{

}
