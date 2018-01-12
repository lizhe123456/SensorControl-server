package com.zlcm.server.base;

public class CustomContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static final String DATA_SOURCE_RDS = "rdsDataSource";//对应动态数据源配置中的key
    public static final String DATA_SOURCE_ESC= "escDataSource";
    public static final String DATA_SOURCE_TEST = "testDataSource";

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        String db = contextHolder.get();
        if (db == null){
            db = DATA_SOURCE_TEST;
        }
        return db;
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }

    

}
