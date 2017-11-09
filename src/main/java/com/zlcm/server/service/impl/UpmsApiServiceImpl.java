package com.zlcm.server.service.impl;

import com.zlcm.server.model.upms.*;
import com.zlcm.server.model.user.UcenterUser;
import com.zlcm.server.service.UpmsApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UpmsApiServiceImpl implements UpmsApiService{

    private static Logger _log = LoggerFactory.getLogger(UpmsApiServiceImpl.class);

    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer userId) {
        return null;
    }

    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer userId) {
        return null;
    }

    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer userId) {
        return null;
    }

    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Integer userId) {
        return null;
    }

    @Override
    public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId) {
        return null;
    }

    @Override
    public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId) {
        return null;
    }

    @Override
    public List<UpmsSystem> selectUpmsSystemByExample(UpmsSystem upmsSystem) {
        return null;
    }

    @Override
    public List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganization upmsOrganization) {
        return null;
    }

    @Override
    public UcenterUser selectUpmsUserByUsername(String username) {
        return null;
    }

    @Override
    public int insertUpmsLogSelective(UpmsLog record) {
        return 0;
    }
}
