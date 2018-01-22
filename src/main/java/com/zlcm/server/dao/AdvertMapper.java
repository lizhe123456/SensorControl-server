package com.zlcm.server.dao;

import com.zlcm.server.base.BaseMapper;
import com.zlcm.server.model.admin.AdminAdvert;
import com.zlcm.server.model.apprep.AppAdvert;
import com.zlcm.server.model.bean.Advert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvertMapper extends BaseMapper<Advert>{

    List<AppAdvert> getAppHotList(@Param("page") Integer page, @Param("size") Integer size);

    List<AppAdvert> getAppRecommend(@Param("page") Integer page, @Param("size") Integer size);

    List<String> findAdvertFordid(@Param("did") Integer did);

    List<AppAdvert> findAdvertForDid(@Param("did") Integer did,@Param("page") Integer page, @Param("size") Integer size);

    List<Advert> findAdvertWhereState(@Param("state") Integer state,@Param("page") Integer page, @Param("size") Integer size);

    List<AdminAdvert> findAdminAdvert(@Param("page") Integer page, @Param("size") Integer size);

    Integer findReleaseNum(@Param("uid") Integer uid);

    Integer findAuditingNum(@Param("uid") Integer uid);

}