package com.zlcm.server.service;

import com.zlcm.server.base.BaseService;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.bean.User;

/**
 * author:lizhe
 * date: 2017-12-19
 * for tomorrow
 * 类介绍:
 */
public interface UserService extends BaseService<User> {

    User getUserForName(String username) throws SysException;
}
