package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.constant.Constant;
import com.zlcm.server.dao.AdvertMapper;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.apprep.AppAdvert;
import com.zlcm.server.model.bean.Advert;
import com.zlcm.server.service.AdvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class AdvertServiceImpl  extends BaseServiceImpl<Advert,AdvertMapper> implements AdvertService {

    private final static Logger _log = LoggerFactory.getLogger(AdvertServiceImpl.class);

    /**
     * 分页获取人文
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<AppAdvert> getAppHotList(Integer page, Integer size) throws SysException {
        try {
            return dao.getAppHotList(page * size,size);
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }

    @Override
    public List<AppAdvert> getAppRecommend(Integer page, Integer size) throws SysException {
        try {
            return dao.getAppRecommend(page * size,size);
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }

    @Override
    public List<String> findAdvertFordid(Integer did) throws SysException{
        try{
            return dao.findAdvertFordid(did);
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }

    @Override
    public List<AppAdvert> findAdvertForDid(Integer did, Integer page, Integer size) throws SysException {
        try{
            return dao.findAdvertForDid(did,page * size,size);
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }

    /**
     * 查询待审广告
     */


    /**
     * 审核广告并发送到设配
     */

}
