package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.AdvertMapper;
import com.zlcm.server.model.bean.Advert;
import com.zlcm.server.service.AdvertService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AdvertServiceImpl  extends BaseServiceImpl<Advert,AdvertMapper> implements AdvertService {
}
