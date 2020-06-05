package com.xxkj.resume.resume.dao;

import com.xxkj.resume.resume.domain.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author zjx
 * @create 2020/3/20 10:07
 */
@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {
     /**
      * @Author: zjx 
      * @Description:  按key获取
      * @param mykey
      * @return: com.xxkj.myzone.entity.Config
      * @Date: 2020/3/22 13:22 
      */
    Config findByMykey(String mykey);

    /**
     * @Author: zjx
     * @Description:  自定义查询 按key获取
     * @param key
     * @return: com.xxkj.myzone.entity.Config
     * @Date: 2020/3/22 13:22
     */
    @Query(value = "select * from config where key_o = ?1 limit 1", nativeQuery = true)
    Config findByKey(String key);
}
