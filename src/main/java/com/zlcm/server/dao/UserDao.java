package com.zlcm.server.dao;

import com.zlcm.server.model.user.UserInfo;
import com.zlcm.server.model.user.UserUcenter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.xml.registry.infomodel.User;
import java.util.Map;


@Repository
public interface UserDao {

    UserInfo selectUid(String id);

    UserUcenter selectUser(@Param(value = "userName") String username,@Param(value = "passWord") String pass);

    int selectUserFormName(String username);

    boolean insertUserInfo(UserInfo userInfo);

    boolean insertUserUcenter(UserUcenter userUcenter);


}
