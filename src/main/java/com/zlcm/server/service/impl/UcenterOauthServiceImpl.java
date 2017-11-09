package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.user.UcenterOauthDao;
import com.zlcm.server.model.user.UcenterOauth;
import com.zlcm.server.service.UcenterOauthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UcenterOauthServiceImpl extends BaseServiceImpl<UcenterOauth,UcenterOauthDao>
        implements UcenterOauthService{

    private static Logger _log = LoggerFactory.getLogger(UcenterOauthServiceImpl.class);


}
