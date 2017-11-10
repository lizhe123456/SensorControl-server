package com.zlcm.server.constant;

public class Constant {

	/**
	 * 数据请求返回码
	 */
	public static final int RESCODE_SUCCESS = 1000;				//成功
	public static final int RESCODE_SUCCESS_MSG = 1001;			//成功(有返回信息)
	public static final int RESCODE_EXCEPTION = 1002;			//请求抛出异常
	public static final int RESCODE_NOLOGIN = 1003;				//未登陆状态
	public static final int RESCODE_NOEXIST = 1004;				//查询结果为空
	public static final int RESCODE_NOAUTH = 1005;				//无操作权限
	
	/**
	 * jwt
	 */
	public static final String JWT_ID = "jwt";
	public static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";
	public static final int JWT_TTL = 60*60*1000;  //millisecond
	public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
	public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond

	/**
	 * 设配
	 */
	public static final int DEVICE_INSERT_SUCCESS = 906;//设配绑定成功
	public static final int DEVICE_DELETE_SUCCESS = 907;
	public static final int DEVICE_BIND_SUCCESS = 900;//设配绑定成功
	public static final int DEVICE_BIND = 901;//设配以绑定
	public static final int DEVICE_BIND_ERROR = 904;//设配绑定错误
	public static final int DEVICE_UNBIND_SUCCESS = 905;//设配解绑成功
	public static final int DEVICE_UNBIND_ERROR = 903;//设配解绑错误

	/**
	 * 接收发送
	 */
	public static final int DEVICE_NOT_FOUND = 801;//设配不存在
	public static final int DEVICE_STATE_EXCEPTION = 805;//设配不存在
	public static final int DEVICE_NOT_BIND = 802;//设配不在线

	/**
	 * 收发记录
	 */
	public static final int RECORD_DELETE_SUCCESS = 705;
	public static final int RECORD_INSERT_SUCCESS = 700;


	public final static String UPMS_TYPE = "zlcm-server-type";
}
