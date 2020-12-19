package io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Order
 *
 * @author David Liu
 */
@Data
@AllArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = 2460826718561503963L;

    private Integer id;

    private String name;

    private Integer userId;
}
