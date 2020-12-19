package io.x12fd16b.week09.thurs.assignment03.rpcfx.core.api;

import lombok.Data;

import java.io.Serializable;

/**
 * RPC Request
 *
 * @author David Liu
 */
@Data
public class RpcRequest implements Serializable {
    
    private static final long serialVersionUID = 3985017984894256347L;
    
    /**
     * invoke target service class
     */
    private String serviceClass;
    
    /**
     * invoke target method
     */
    private String method;
    
    /**
     * invoke method parameters
     */
    private Object[] args;
}
