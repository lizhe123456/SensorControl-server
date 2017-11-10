package com.zlcm.server.controller.device;

import com.zlcm.server.controller.app.AppUserController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/device/send")
@Api(value = "设配接口",description = "用户")
public class DeviceToServerController {

    private static Logger _log = LoggerFactory.getLogger(AppUserController.class);

    /**
     * 上报信息
     */


}
