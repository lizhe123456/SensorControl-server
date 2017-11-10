package com.zlcm.server.dao.user;


import com.zlcm.server.base.BaseMapper;
import com.zlcm.server.model.user.UcenterUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UcenterUserDao extends BaseMapper<UcenterUser> {

    UcenterUser userNameForSelect(@Param("username") String username);
}
