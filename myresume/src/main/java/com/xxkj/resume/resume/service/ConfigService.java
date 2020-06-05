package com.xxkj.resume.resume.service;

/**
 * @Author zjx
 * @create 2020/3/22 13:13
 */
public interface ConfigService {
    /**
     * 离职原因
     * @Author: zjx
     * @Description:  获取离职原因
     * @param key
     * @return: String
     * @Date: 2020/3/22 13:23 
     */
    String getLeaveInfo(String key);
}
