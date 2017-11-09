package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.upms.UpmsSystemMapper;
import com.zlcm.server.model.upms.UpmsSystem;
import com.zlcm.server.service.UpmsSystemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UpmsSystemServiceImpl extends BaseServiceImpl<UpmsSystem,UpmsSystemMapper>
        implements UpmsSystemService {
}
