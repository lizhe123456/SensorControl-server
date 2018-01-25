package com.zlcm.server.base;

import com.zlcm.server.constant.Constant;
import com.zlcm.server.exception.SysException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

public abstract class BaseServiceImpl<Entity,DAO extends BaseMapper<Entity>> implements BaseService<Entity> {

    @Autowired
    protected DAO dao;

    private  Logger _log = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Override
    public void save(Entity entity) throws SysException {
        try {
            dao.save(entity);
        } catch (DataAccessException e) {
            _log.error(e.getMessage());
            throw new SysException(Constant.ADD_ERROR);
        }
    }

    @Override
    public void update(Entity entity) throws SysException {
        try {
            dao.update(entity);
        } catch (DataAccessException e) {
            _log.error(e.getMessage());
            throw new SysException(Constant.UPDATE_ERROR);
        }
    }

    @Override
    public void deleteByPK(Integer pk) throws SysException {
        try {
            dao.deleteByPK(pk);
        } catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.DELETE_ERROR);
        }
    }

    @Override
    public Entity get(Integer pk) throws SysException {
        try {
            return dao.get(pk);
        } catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }


    @Override
    public List<Entity> findAll() throws SysException {
        try {
            return dao.findAll();
        } catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }

    @Override
    public Integer getCount() throws SysException {
        try {
            return dao.getCount();
        } catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }

    @Override
    public List<Entity> getPageList(Integer page, Integer size) throws SysException {
        try {
            return dao.getPageList(page * size,size);
        } catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }

}
