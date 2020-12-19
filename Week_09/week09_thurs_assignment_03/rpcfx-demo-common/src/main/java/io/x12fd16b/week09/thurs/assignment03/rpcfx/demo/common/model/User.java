package io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * User
 *
 * @author David Liu
 */
@Data
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 7564869229407972557L;

    private Integer id;

    private String name;
}
