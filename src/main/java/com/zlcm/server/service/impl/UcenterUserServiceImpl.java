package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.user.UcenterUserDao;
import com.zlcm.server.model.user.UcenterUser;
import com.zlcm.server.service.UcenterUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UcenterUserServiceImpl extends BaseServiceImpl<UcenterUser,UcenterUserDao>
        implements UcenterUserService {

    private static Logger _log = LoggerFactory.getLogger(UcenterUserServiceImpl.class);


}
