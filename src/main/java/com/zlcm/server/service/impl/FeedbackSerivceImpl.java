package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.FeedbackMapper;
import com.zlcm.server.model.bean.Feedback;
import com.zlcm.server.service.FeedbackSerivce;
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
public class FeedbackSerivceImpl extends BaseServiceImpl<Feedback,FeedbackMapper> implements FeedbackSerivce{

}
