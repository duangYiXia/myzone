package com.xxkj.resume.resume.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @Author zjx
 * @create 2020/3/20 10:52
 */
@Entity
@Table(name = "resume_info", schema = "myZone")
public class ResumeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private Long id;

    @Column(name = "basic_info")
    private String basicInfo;

    @Column(name = "education_experience")
    private String educationExperience;

    @Column(name = "technical_direction")
    private String technicalDirection;

    @Column(name = "work_experience")
    private String workExperience;

    @Column(name = "item_experience")
    private String itemExperience;

    @Column(name = "person_advantage")
    private String personAdvantage;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_time")
    private String updateTime;
}
