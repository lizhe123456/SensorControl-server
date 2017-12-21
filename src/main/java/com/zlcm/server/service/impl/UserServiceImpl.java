package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.constant.Constant;
import com.zlcm.server.dao.UserMapper;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.bean.User;
import com.zlcm.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
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

    private static Logger _log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User getUserForName(String username) throws SysException {
        try {
            return dao.getUserForName(username);
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }
}
