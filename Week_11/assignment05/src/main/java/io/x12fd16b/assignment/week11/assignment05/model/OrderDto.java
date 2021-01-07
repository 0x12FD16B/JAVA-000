package io.x12fd16b.assignment.week11.assignment05.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * order dto.
 *
 * @author David Liu
 */
@Data
public class OrderDto implements Serializable {
    private static final long serialVersionUID = 4256775335001728426L;

    private List<OrderItemDto> orderItems;
}
