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

	/**
	 * 增删改查错误码
	 */
	public static final int ADD_ERROR = 1;
	public static final int UPDATE_ERROR = 2;
	public static final int DELETE_ERROR = 3;
	public static final int SELECE_ERROR = 4;



	public final static String UPMS_TYPE = "zlcm-server-type";
	public final static String ID_AUTHEN = "http://apis.juhe.cn/idimage/verify";
	public final static String ADDRESS = "http://192.168.1.201:8080/";
//	public final static String ADDRESS = "http://39.106.34.25:8080/";
	public final static String ZS_ADDRESS = "D:/web/";
//	public final static String ZS_ADDRESS = "D:/web/";

	public final static String ID_KEY = "41f1313aeabd170eb2299636d257f545";

	public final static String ADVERT_IMG_URL = "D:/web/advert";
	public final static String AVATAR_IMG_URL = "D:/web/avatar";
	public final static String IMAGE_IMG_URL = "D:/web/image";
	public final static String PUSH_JSON_URL = "D:/web/appPush.txt";
	public final static String HEAD_JSON_URL = "D:/web/headPush.txt";
	public final static String NAVIGATION_JSON_URL = "D:/web/navigation.txt";
	public final static String VERSION_JSON_URL = "D:/web/version.txt";

	public final static String PRIVATE_KEY = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAnGbUUvdHAOBEFaQEifonOo1Dwyf/ycS6YcA5iZUyGyuMb6VySaZivhJW87/h/N5NR3ok7SQZWgVS3eWhAmryPwIDAQABAkBgHgHWiwkjzK+K7SWs9gMtxKskQGy+Pxyb/lpd50bH0uEhY4ls/EMIMWrbakvPjTgg+KTqioFsK5GsJGpjHC1BAiEA1YGZb0wHM8NUDONGUUbF/RQPlYkDLtwHux4dWP4MwhcCIQC7h67wmv06mHl4lgXWaeqaGJ0knPTHdyumf0rYiIiyGQIhAIj2BzBvTBt3jN5rmTsgNHgBu3GIoAMaYNc3HUpuRk7nAiEAjEDbVvokAEn/N2/Ep+sJWfyksrhs3i3DyL+VQ8NBsSkCIHChzdVah3gS53CgWIH7F8d1XmqxDMfchj+q2USXSGG6";


}
