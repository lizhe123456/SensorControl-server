package com.zlcm.server.model;

public class PicUploadResult extends Result {

    private Integer error=0;        //图片上传错误不能抛出，抛出就无法进行jsp页面回调，所以设置这个标识，0表示无异常，1代表异常
    private String url;                //浏览器能够解析的具体页面路径。相对路径： http//image.jt/img.jpg  本地路径：存放图片路径   D:/jt-image/....
    private String width;
    private String height;

    public PicUploadResult(int stateCode, String message) {
        super(stateCode, message);
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
