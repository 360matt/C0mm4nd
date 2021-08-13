package fr.i360matt.c0mm4nd.exceptions;

public class BadArgException extends Exception {

    private final int argNeeded;
    private final Class<?> type;

    public BadArgException (final int argNeeded, final Class<?> type) {
        this.argNeeded = argNeeded;
        this.type = type;

    }

    public int getArgNeeded () {
        return this.argNeeded;
    }

    public Class<?> getType () {
        return this.type;
    }
}