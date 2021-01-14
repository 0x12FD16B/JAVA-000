package io.x12fd16b.assignment.week13.sat.message;

import lombok.Data;

import java.io.Serializable;

/**
 * order message.
 *
 * @author David Liu
 */
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = -4239582177953147017L;

    /**
     * order no.
     */
    private String orderNo;

    /**
     * constructor with orderNo.
     *
     * @param orderNo orderNo
     */
    public Order(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * non args constructor.
     */
    public Order() {
    }
}
