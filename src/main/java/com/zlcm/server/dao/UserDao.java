package com.zlcm.server.dao;

import com.zlcm.server.model.user.UserInfo;
import com.zlcm.server.model.user.UserUcenter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao {

    int selectUid(String id);

    int selectUser(@Param("userName") String username,@Param("passWord") String password);

    int selectUserFormName(String username);

    boolean insertUserInfo(UserInfo userInfo);

    boolean insertUserUcenter(UserUcenter userUcenter);


}
