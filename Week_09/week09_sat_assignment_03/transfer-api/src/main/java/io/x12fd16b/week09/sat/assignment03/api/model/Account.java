package io.x12fd16b.week09.sat.assignment03.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Account
 *
 * @author David Liu
 */
@Data
public class Account implements Serializable {
    private static final long serialVersionUID = 7903332522677197624L;

    private int id;

    private int rmb;

    private int dollar;
}
