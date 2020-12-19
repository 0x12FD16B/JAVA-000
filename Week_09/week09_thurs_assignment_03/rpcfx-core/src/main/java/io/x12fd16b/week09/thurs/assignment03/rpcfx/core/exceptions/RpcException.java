package io.x12fd16b.week09.thurs.assignment03.rpcfx.core.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RPC Exception
 *
 * @author David Liu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RpcException extends RuntimeException {
    private static final long serialVersionUID = -2137534125206127165L;

    private String message;

    public RpcException() {
        super();
    }

    public RpcException(String message) {
        super(message);
        this.message = message;
    }
}
