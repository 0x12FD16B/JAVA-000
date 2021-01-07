package io.x12fd16b.assignment.week11.assignment04.lock;

import lombok.Data;

/**
 * DsLockCallbackResult.
 *
 * @author David Liu
 */
@Data
public class DsLockCallbackResult {

    private boolean success;

    private String errorInfo;
}
