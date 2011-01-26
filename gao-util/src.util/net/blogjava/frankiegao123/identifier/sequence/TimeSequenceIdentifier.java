package net.blogjava.frankiegao123.identifier.sequence;

import net.blogjava.frankiegao123.identifier.SequenceIdentifier;

public class TimeSequenceIdentifier extends HexSequenceIdentifier implements SequenceIdentifier {

    private final int multi;

    public TimeSequenceIdentifier() {
        this(1);
    }

    public TimeSequenceIdentifier(int multi) {
        this.multi = multi;
    }

    public long next() {
        return (System.currentTimeMillis() / multi);
    }
}