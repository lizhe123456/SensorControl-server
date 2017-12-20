package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.LogMapper;
import com.zlcm.server.model.bean.Log;
import com.zlcm.server.service.LogService;
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
public class LogServiceImpl extends BaseServiceImpl<Log,LogMapper> implements LogService{
}
