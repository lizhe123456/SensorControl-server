package com.zlcm.server.base;

import com.zlcm.server.exception.SysException;

import java.util.List;

public interface BaseService<Entity> {

    /**
     * 增加
     * @param entity
     */
    void save(Entity entity) throws SysException;

    /**
     * 修改
     * @param entity
     */
    void update(Entity entity) throws SysException;

    /**
     * 通过主键删除
     * @param pk
     */

    void deleteByPK(final Integer pk) throws SysException;

    /**
     * 通过主键查询
     * @param pk
     * @return
     */
    Entity get(Integer pk) throws SysException;

    /**
     * 查询全部
     * @return
     */
    List<Entity> findAll() throws SysException;

    /**
     * 总数
     */
    Integer getCount() throws SysException;

    /**
     * 分页
     */
    List<Entity> getPageList(Integer page, Integer size) throws SysException;

}
