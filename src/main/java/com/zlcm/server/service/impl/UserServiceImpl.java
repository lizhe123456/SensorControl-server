package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.UserMapper;
import com.zlcm.server.model.bean.User;
import com.zlcm.server.service.UserService;
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
public class UserServiceImpl extends BaseServiceImpl<User,UserMapper> implements UserService{

}
