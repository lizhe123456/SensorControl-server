package com.zlcm.server.base;

import java.util.List;

public interface BaseService<Entity> {

    /**
     * 增加
     * @param entity
     */
    void save(Entity entity);

    /**
     * 修改
     * @param entity
     */
    void update(Entity entity);

    /**
     * 通过主键删除
     * @param pk
     */

    void deleteByPK(final Integer pk);

    /**
     * 通过主键查询
     * @param pk
     * @return
     */
    Entity get(Integer pk);

    /**
     * 查询全部
     * @return
     */
    List<Entity> findAll();

    /**
     * 总数
     */
    Integer getCount();

    /**
     * 分页
     */
    List<Entity> getPageList(Integer page, Integer size);

}
