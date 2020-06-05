package com.xxkj.resume.resume.service.impl;

import com.xxkj.resume.resume.dao.ConfigRepository;
import com.xxkj.resume.resume.domain.entity.Config;
import com.xxkj.resume.resume.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zjx
 * @create 2020/3/22 13:12
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    ConfigRepository configRepository;


    @Override
    public String getLeaveInfo(String key) {
        Config config = configRepository.findByMykey(key);
        return config.getMyvalue();
    }
}
