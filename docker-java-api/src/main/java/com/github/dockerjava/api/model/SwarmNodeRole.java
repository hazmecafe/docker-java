package com.github.dockerjava.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @since RemoteApiVersion#VERSION_1_24
 */
public enum SwarmNodeRole {

    @JsonProperty("worker")
    WORKER,

    @JsonProperty("manager")
    MANAGER
}
