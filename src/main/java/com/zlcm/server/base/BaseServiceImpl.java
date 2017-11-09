package com.zlcm.server.base;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseServiceImpl<Entity,DAO extends BaseMapper<Entity>> implements BaseService<Entity> {

    @Autowired
    protected DAO dao;

    @Override
    public void save(Entity entity) {
        dao.save(entity);
    }

    @Override
    public void update(Entity entity) {
        dao.update(entity);
    }

    @Override
    public void deleteByPK(Integer pk) {
        dao.deleteByPK(pk);
    }

    @Override
    public Entity get(Integer pk) {
        return dao.get(pk);
    }


    @Override
    public List<Entity> findAll() {
        return dao.findAll();
    }

    @Override
    public Integer getCount() {
        return dao.getCount();
    }

    @Override
    public List<Entity> getPageList(Integer page, Integer size) {
        return dao.getPageList(page,size);
    }

}
