package fr.i360matt.c0mm4nd.exceptions;

public class MissingArgException extends Exception {

    private final int argNeeded;
    public MissingArgException (final int argNeeded) {
        this.argNeeded = argNeeded;
    }

    public int getArgNeeded () {
        return this.argNeeded;
    }
}
