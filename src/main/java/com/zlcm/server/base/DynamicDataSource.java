package com.zlcm.server.base;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源配置
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String sourceType = CustomContextHolder.getCustomerType();
        return sourceType;
    }
}
