package com.zlcm.server.service;

import com.zlcm.server.dao.upms.UpmsOrganizationMapper;
import com.zlcm.server.dao.user.UcenterUserOauthDao;
import com.zlcm.server.model.upms.UpmsRole;
import com.zlcm.server.model.upms.UpmsUserRole;
import com.zlcm.server.model.user.UcenterUserOauth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml"})
public class DaoTest {

    @Autowired
    UcenterUserOauthDao ucenterUserOauthDao;

    @Autowired
    UpmsOrganizationMapper upmsOrganizationMapper;
    @Autowired
    UcenterUserOauthService ucenterUserOauthService;
    @Autowired
    UpmsUserRoleService upmsUserRoleService;
    @Autowired
    UpmsApiService upmsApiService;
    @Test
    public void saas(){
        ucenterUserOauthDao.findAll();
        upmsOrganizationMapper.findAll();
        List<UcenterUserOauth> list = ucenterUserOauthService.findAll();
        List<UpmsUserRole> list1 = upmsUserRoleService.findAll();
        System.out.println(list.size());
        System.out.println(list1.size());
        upmsApiService.selectUpmsRoleByUpmsUserId(1);
        List<UpmsRole> list2 = upmsApiService.selectUpmsRoleByUpmsUserIdByCache(1);


    }
}
