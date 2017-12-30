package com.zlcm.server.service;

import com.zlcm.server.base.BaseService;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.apprep.AppAdvert;
import com.zlcm.server.model.bean.Advert;
import io.swagger.models.auth.In;

import java.util.List;

public interface AdvertService extends BaseService<Advert>{

    List<AppAdvert> getAppHotList(Integer page, Integer size) throws SysException;


    List<AppAdvert> getAppRecommend(Integer page, Integer size) throws SysException;

    List<String> findAdvertFordid(Integer did) throws SysException;

    List<AppAdvert> findAdvertForDid(Integer did, Integer page, Integer size) throws SysException;
}
