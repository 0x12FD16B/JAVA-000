package io.x12fd16b.week7.thrus.assignment02.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户收货地址
 *
 * @author David Liu
 */
@Data
public class UserShippingAddress implements Serializable {
    private static final long serialVersionUID = -1647264168645916333L;
    /**
     * ID
     */
    private Long id;
    /**
     * 用户 ID
     */
    private Long userId;
    /**
     * 省编码
     */
    private String provinceCode;
    /**
     * 市编码
     */
    private String cityCode;
    /**
     * 区编码
     */
    private String districtCode;
    /**
     * 邮编
     */
    private String postCode;
    /**
     * 详细地址
     */
    private String detailAddress;
    /**
     * 收货人姓名
     */
    private String consigneeName;
    /**
     * 收货人手机号
     */
    private String consigneeMobile;
    /**
     * 是否默认, 0: 否, 1: 是
     */
    private int defaultFlag;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
}
