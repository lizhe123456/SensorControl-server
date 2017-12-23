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
	public final static String ID_KEY = "41f1313aeabd170eb2299636d257f545";

	public final static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoVZf5lth4dmqNI57guLNsXVbRhEvI0fCPNB9v6k/euS7DRZwq6yN2/fgreT3eXxqqXJb8XMUMYIusTQiO/8Raf4Pr2ySSUNKdmrpdFviACH3xZkuoemodcp4ZDTCBVoBIcjRQ27mwNPBb/bQNY4iPEPWCJ4OMupVtQAU4IOgdPwIDAQAB";

	public final static String APP_PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANYHCSm1quT5oJiYyfYm256qU9tX3/oYvt7kUnhJ6ohwKX+6ShsjGwn8F85gZcgmw2ZavST8JBcHmRvgEVHAvNhEzSPiErPM0EDfomiSayv7Ha1QAzqMijQrgXzOE5H/43bihRPZqcVJvROwGHQjwHnwtZcSWgS9+CdK3u6Tgy8pAgMBAAECgYEAor0m0ng4QXMuEdnbdzeEtth8esQOonQ+bezeaGiL1MK0S1KzFn9TB/yPfXT+73nKCgHk4EJ4jqpQWUm+4ZLUA5l68OHuSxRhZgR7/dQ1MLcgASgegU4IYwUoHBXvyNn05OzDJ7ZyJ0TFPPA5X/X5ppKi/zMBOmCUrwZfi7wrzyECQQD4E0H6UyHKbERT62gtpGZ6dggAI+rF/eb/zn+pQY3iuPCDLru9g6S63TQfijeTorZrzTO75bPEv+kqGo4dRUPXAkEA3N1WhEELcMHNFp223j80Z3VCDzoMhArrVCoqu007/5ZKMkCUjpj3jDiYZVQ/CDzaBNaKwtPshT7nEQirxozE/wJBANIire63YJYxI6faboLLEauGM7y6r/JK4tOpPeZHi0KT2delTI555p4LJk3ZLL64Q8IJLltpdd+hmIZ7vAn7VD0CQQCCjoQZ4ak1mhn1iH0UmyALGBFYluPXGe1J7zMbTOhuiRSK6Ano0Rtj1AChxU8NO4tU2M1lpYhmq2xDcXC5CXVZAkAT733zln5mk0EYL3cEZVAmir0xyUz1mCC4hHazAf9hdzQWjJAtfsnOxj7uvtnqo7PXHXq5YXyfwmyEnEwWi/OJ";


}
