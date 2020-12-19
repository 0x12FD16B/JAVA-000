package io.x12fd16b.week09.thurs.assignment03.rpcfx.core.api;

import lombok.Data;

import java.io.Serializable;

/**
 * RPC Response
 *
 * @author David Liu
 */
@Data
public class RpcResponse implements Serializable {
    private static final long serialVersionUID = 6720560083984272023L;
    
    /**
     * result
     */
    private Object result;
    
    /**
     * status
     */
    private Boolean status;
    
    /**
     * exception
     */
    private Exception exception;
}
