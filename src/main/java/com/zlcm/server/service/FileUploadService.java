package com.zlcm.server.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface FileUploadService {

    Map<String,Object> upload(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;

}
