package com.github.dockerjava.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @since RemoteApiVersion#VERSION_1_24
 */
public enum SwarmNodeState {

    @JsonProperty("unknown")
    UNKNOWN,

    @JsonProperty("down")
    DOWN,

    @JsonProperty("ready")
    READY,

    @JsonProperty("disconnected")
    DISCONNECTED
}
