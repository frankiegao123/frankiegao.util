package net.blogjava.frankiegao123.identifier.sequence;

import java.util.concurrent.atomic.AtomicLong;

import net.blogjava.frankiegao123.identifier.SequenceIdentifier;

public class AtomicSequenceIdentifier extends HexSequenceIdentifier implements SequenceIdentifier {

    private final AtomicLong sequence;

    public AtomicSequenceIdentifier() {
        this(0L);
    }

    public AtomicSequenceIdentifier(long start) {
        this.sequence = new AtomicLong(start);
    }

    public long next() {
        return sequence.getAndIncrement();
    }
}