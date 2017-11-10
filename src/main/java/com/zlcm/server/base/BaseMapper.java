package com.zlcm.server.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<Entity> {

    /**
     * 增加
     * @param entity
     */
    int save(Entity entity);

    /**
     * 修改
     * @param entity
     */
    int update(Entity entity);

    /**
     * 通过主键删除
     * @param pk
     */

    int deleteByPK(@Param("pk") final Integer pk);

    /**
     * 通过主键查询
     * @param pk
     * @return
     */
    Entity get(@Param("pk") Integer pk);

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
    List<Entity> getPageList(@Param("page") Integer page, @Param("size") Integer size);

}
