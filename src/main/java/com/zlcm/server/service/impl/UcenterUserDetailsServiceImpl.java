package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.user.UcenterUserDetailsDao;
import com.zlcm.server.model.user.UcenterUserDetails;
import com.zlcm.server.service.UcenterUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UcenterUserDetailsServiceImpl extends BaseServiceImpl<UcenterUserDetails,UcenterUserDetailsDao>
        implements UcenterUserDetailsService {

    private static Logger _log = LoggerFactory.getLogger(UcenterUserDetailsServiceImpl.class);

}
