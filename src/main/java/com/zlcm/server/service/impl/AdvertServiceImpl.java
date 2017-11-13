package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.upms.AdvertMapper;
import com.zlcm.server.model.upms.Advert;
import com.zlcm.server.service.AdvertService;
import org.springframework.stereotype.Service;

@Service
public class AdvertServiceImpl  extends BaseServiceImpl<Advert,AdvertMapper> implements AdvertService{
}
