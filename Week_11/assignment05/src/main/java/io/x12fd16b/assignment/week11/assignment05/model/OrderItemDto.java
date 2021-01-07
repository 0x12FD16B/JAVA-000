package io.x12fd16b.assignment.week11.assignment05.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Order item dto.
 *
 * @author David Liu
 */
@Data
public class OrderItemDto implements Serializable {
    private static final long serialVersionUID = 1891190003955971813L;

    private String skuCode;

    private Integer quantity;
}
