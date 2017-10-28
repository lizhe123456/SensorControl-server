package com.zlcm.server.controller;

import com.zlcm.server.model.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
@RequestMapping("/send")
public class ReceiveController {


    @RequestMapping(value = "/img",method = RequestMethod.POST)
    public @ResponseBody Result receiveImg(@RequestParam("uploadFile") MultipartFile file)
            throws IOException{
        String name = file.getContentType();
        System.out.println(name);

        return new Result(200,"上传成功");
    }

    @RequestMapping()
    public @ResponseBody Result receiveAT(){
        Result result = null;

        return result;
    }


}
