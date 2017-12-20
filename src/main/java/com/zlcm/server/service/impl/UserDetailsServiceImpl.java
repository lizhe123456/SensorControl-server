package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.UserDetailsMapper;
import com.zlcm.server.model.bean.UserDetails;
import com.zlcm.server.service.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * author:lizhe
 * date: 2017-12-19
 * for tomorrow
 * 类介绍:
 */
@Transactional
@Service
public class UserDetailsServiceImpl extends BaseServiceImpl<UserDetails,UserDetailsMapper>
        implements UserDetailsService {

}
