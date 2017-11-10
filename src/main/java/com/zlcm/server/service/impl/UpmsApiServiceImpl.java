package com.zlcm.server.service.impl;

import com.zlcm.server.dao.upms.*;
import com.zlcm.server.dao.user.UcenterUserDao;
import com.zlcm.server.model.upms.*;
import com.zlcm.server.model.user.UcenterUser;
import com.zlcm.server.service.UpmsApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class UpmsApiServiceImpl implements UpmsApiService{

    private static Logger _log = LoggerFactory.getLogger(UpmsApiServiceImpl.class);

    @Autowired
    UcenterUserDao ucenterUserDao;
    @Autowired
    UpmsApiMapper upmsApiMapper;
    @Autowired
    UpmsRolePermissionMapper upmsRolePermissionMapper;
    @Autowired
    UpmsUserPermissionMapper upmsUserPermissionMapper;
    @Autowired
    UpmsSystemMapper upmsSystemMapper;
    @Autowired
    UpmsOrganizationMapper upmsOrganizationMapper;
    @Autowired
    UpmsLogMapper upmsLogMapper;

    /**
     * 根据用户id获取所有的权限
     * @param userId
     * @return
     */
    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer userId) {
        UcenterUser ucenterUser = ucenterUserDao.get(userId);
        if (ucenterUser == null || ucenterUser.getLocked() == 1){
            _log.info("selectUpmsPermissionByUpmsUserId : upmsUserId={}" ,userId );
            return null;
        }
        List<UpmsPermission> upmsPermissions = upmsApiMapper.selectUpmsPermissionByUpmsUserId(userId);
        return upmsPermissions;
    }

    @Override
    @Cacheable(value = "zlcm-ehcache", key = "'selectUpmsPermissionByUpmsUserId_'+ #upmsUserId")
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer userId) {
        return selectUpmsPermissionByUpmsUserId(userId);
    }

    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer userId) {
        //用户不存在或锁定状态
        UcenterUser ucenterUser = ucenterUserDao.get(userId);
        if (null == ucenterUser || 1 == ucenterUser.getLocked()) {
            _log.info("selectUpmsRoleByUpmsUserId : upmsUserId={}", userId);
            return null;
        }
        List<UpmsRole> upmsRoles = upmsApiMapper.selectUpmsRoleByUpmsUserId(userId);
        return upmsRoles;
    }
    /**
     * 根据用户id获取所属的角色
     * @param userId
     * @return
     */
    @Cacheable(value = "zlcm-ehcache", key = "'selectUpmsRoleByUpmsUserId_' + #upmsUserId")
    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Integer userId) {
       return selectUpmsRoleByUpmsUserId(userId);
    }
    /**
     * 根据角色id获取所拥有的权限
     * @param upmsRoleId
     * @return
     */
    @Override
    public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId) {
        List <UpmsRolePermission> list = upmsRolePermissionMapper.roleToPermission(upmsRoleId);
        return list;
    }

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId) {
        List<UpmsUserPermission> upmsUserPermissions = upmsUserPermissionMapper.userToPermission(upmsUserId);
        return upmsUserPermissions;
    }

    /**
     * 根据条件取系统数据
     * @param upmsSystem
     * @return
     */
    @Override
    public List<UpmsSystem> selectUpmsSystemByExample(UpmsSystem upmsSystem) {
        return null;
    }

    @Override
    public List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganization upmsOrganization) {
        return null;
    }

    /**
     * 根据username获取UpmsUser
     * @param username
     * @return
     */
    @Override
    public UcenterUser selectUpmsUserByUsername(String username) {
        UcenterUser ucenterUsers = ucenterUserDao.userNameForSelect(username);
        return ucenterUsers;
    }

    /**
     * 写入操作日志
     * @param record
     * @return
     */
    @Override
    public int insertUpmsLogSelective(UpmsLog record) {
        return upmsLogMapper.save(record);
    }
}
