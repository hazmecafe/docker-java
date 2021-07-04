package com.github.dockerjava.api.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dockerjava.api.model.DockerObject;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The response of a CreateSecretCmd
 */
@EqualsAndHashCode
@ToString
public class CreateSecretResponse extends DockerObject {
    @JsonProperty("ID")
    private String id;

    public String getId() {
        return id;
    }
}
