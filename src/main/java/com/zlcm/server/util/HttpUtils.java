package com.zlcm.server.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

public class HttpUtils {

    public static String post(String url, String str)throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        // 处理请求地址
        URI uri = new URI(url);
        HttpPost post = new HttpPost(uri);
        post.setEntity(new StringEntity(str,"utf-8"));
        // 执行请求
        CloseableHttpResponse response = client.execute(post);

        if (response.getStatusLine().getStatusCode() == 200) {
            // 处理请求结果
            StringBuffer buffer = new StringBuffer();
            InputStream in = null;
            try {
                in = response.getEntity().getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

            } finally {
                // 关闭流
                if (in != null)
                    in.close();
            }

            return buffer.toString();
        } else {
            return null;
        }

    }

}
