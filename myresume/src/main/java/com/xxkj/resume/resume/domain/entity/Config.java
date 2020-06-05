package com.xxkj.resume.resume.domain.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Setter;

/**
 * @Author zjx
 * @create 2020/3/20 10:15
 */
@Getter
@Setter
@Entity
@Table(name = "config", schema = "myZone")
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "mykey")
    private String mykey;

    @Column(name = "myvalue")
    private String myvalue;
}
