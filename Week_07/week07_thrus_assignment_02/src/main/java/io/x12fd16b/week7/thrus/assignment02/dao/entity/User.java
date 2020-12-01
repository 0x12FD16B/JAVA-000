package io.x12fd16b.week7.thrus.assignment02.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author David Liu
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 7250607547582870329L;
    /**
     * ID
     */
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别, 1: 男, 2: 女
     */
    private int gender;
    /**
     * 简介
     */
    private String brief;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
}
