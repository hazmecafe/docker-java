package com.github.dockerjava.api.model;

import static com.github.dockerjava.api.model.InternetProtocol.TCP;
import static com.github.dockerjava.api.model.InternetProtocol.UDP;
import static com.github.dockerjava.api.model.InternetProtocol.SCTP;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.github.dockerjava.api.model.Ports.Binding;
import lombok.EqualsAndHashCode;

/**
 * Represents a container port that Docker exposes to external clients. The port is defined by its #getPort() port number and an
 * InternetProtocol. It can be published by Docker by Ports#bind(ExposedPort, Binding) binding it to a host port,
 * represented by a Binding.
 */
@EqualsAndHashCode
public class ExposedPort implements Serializable {
    private static final long serialVersionUID = 1L;

    private final InternetProtocol protocol;

    private final int port;

    /**
     * Creates an ExposedPort for the given parameters.
     *
     * @param port
     *            the #getPort() port number
     * @param protocol
     *            the InternetProtocol
     */
    public ExposedPort(int port, InternetProtocol protocol) {
        this.port = port;
        this.protocol = protocol;
    }

    /**
     * Creates an ExposedPort for the given #getPort() port number and InternetProtocol#DEFAULT.
     *
     * @param port
     *            the #getPort() port number
     */
    public ExposedPort(int port) {
        this(port, InternetProtocol.DEFAULT);
    }

    /**
     * Creates an ExposedPort for the given parameters.
     *
     * @param scheme
     *            the #getScheme() scheme, <code>tcp</code>, <code>udp</code> or <code>sctp</code>
     * @param port
     *            the #getPort() port number
     * @deprecated use #ExposedPort(int, InternetProtocol)
     */
    @Deprecated
    public ExposedPort(String scheme, int port) {
        this(port, InternetProtocol.valueOf(scheme));
    }

    /**
     * @return the InternetProtocol of the #getPort() port that the container exposes
     */
    public InternetProtocol getProtocol() {
        return protocol;
    }

    /**
     * @return the scheme (internet protocol), <code>tcp</code>, <code>udp</code> or <code>sctp</code>
     * @deprecated use #getProtocol()
     */
    @Deprecated
    public String getScheme() {
        return protocol.toString();
    }

    /** @return the port number that the container exposes */
    public int getPort() {
        return port;
    }

    /**
     * Creates an ExposedPort for InternetProtocol#TCP. This is a shortcut for
     * <code>new ExposedPort(port, InternetProtocol#TCP)</code>
     */
    public static ExposedPort tcp(int port) {
        return new ExposedPort(port, TCP);
    }

    /**
     * Creates an ExposedPort for InternetProtocol#UDP. This is a shortcut for
     * <code>new ExposedPort(port, InternetProtocol#UDP)</code>
     */
    public static ExposedPort udp(int port) {
        return new ExposedPort(port, UDP);
    }

    /**
     * Creates an ExposedPort for InternetProtocol#SCTP. This is a shortcut for
     * <code>new ExposedPort(port, InternetProtocol#SCTP)</code>
     */
    public static ExposedPort sctp(int port) {
        return new ExposedPort(port, SCTP);
    }

    /**
     * Parses a textual port specification (as used by the Docker CLI) to an ExposedPort.
     *
     * @param serialized
     *            the specification, e.g. <code>80/tcp</code>
     * @return an ExposedPort matching the specification
     * @throws IllegalArgumentException
     *             if the specification cannot be parsed
     */
    @JsonCreator
    public static ExposedPort parse(String serialized) throws IllegalArgumentException {
        try {
            String[] parts = serialized.split("/");
            switch (parts.length) {
                case 1:
                    return new ExposedPort(Integer.parseInt(parts[0]));
                case 2:
                    return new ExposedPort(Integer.parseInt(parts[0]), InternetProtocol.parse(parts[1]));
                default:
                    throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing ExposedPort '" + serialized + "'");
        }
    }

    /**
     * Returns a string representation of this ExposedPort suitable for inclusion in a JSON message. The format is
     * <code>port/protocol</code>, like the argument in #parse(String).
     *
     * @return a string representation of this ExposedPort
     */
    @Override
    @JsonValue
    public String toString() {
        return port + "/" + protocol.toString();
    }
}
