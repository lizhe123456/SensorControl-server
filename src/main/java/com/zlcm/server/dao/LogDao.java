package com.zlcm.server.dao;

import com.zlcm.server.model.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogDao {
    //新增
    Long insert(Log log);

    //更新
    void update(Log log);

    //通过对象进行查询
    public Log select(Log SysLog);

    //通过id进行查询
    public Log selectById(@Param("id") Long id);

    //查询全部
    public List<Log> selectAll();

    //查询数量
    public int selectCounts();

    List<Log> selectLog(@Param("sort") String sort, @Param("order") String order, @Param("method") String method, @Param("url") String url, @Param("param") String param, @Param("result") String result);

}
