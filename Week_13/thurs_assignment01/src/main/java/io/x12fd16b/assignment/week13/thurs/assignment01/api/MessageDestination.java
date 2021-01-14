package io.x12fd16b.assignment.week13.thurs.assignment01.api;

import lombok.Data;

import java.io.Serializable;

/**
 * message destination.
 *
 * @author David Liu
 */
@Data
public class MessageDestination implements Serializable {
    private static final long serialVersionUID = -8865385993055465783L;

    private String destination;

    private String message;
}
