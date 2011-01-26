package net.blogjava.frankiegao123.identifier.sequence;

import net.blogjava.frankiegao123.identifier.SequenceIdentifier;

public class TimeAwareSequenceIdentifier extends HexSequenceIdentifier implements SequenceIdentifier {

    private final static int MULTI = 200;
    private final static long TIME_MASK = 0x7ffffffffL;
    private final static long CLUSTER_NODE_MASK = 0x1ffL;
    private final static long SEQUENCE_MASK = 0xfffffL;

    private final SequenceIdentifier time;
    private final SequenceIdentifier atomic;

    private final long clusterNode;

    public TimeAwareSequenceIdentifier() {
        this(0, 0);
    }

    public static TimeAwareSequenceIdentifier createStartIdentifier(long start) {
        return new TimeAwareSequenceIdentifier(0, start);
    }

    public static TimeAwareSequenceIdentifier createClusterIdentifier(int clusterNode) {
        return new TimeAwareSequenceIdentifier(clusterNode, 0);
    }

    public TimeAwareSequenceIdentifier(int clusterNode, long start) {
        check(clusterNode);
        this.clusterNode = (clusterNode & CLUSTER_NODE_MASK);
        this.time = new TimeSequenceIdentifier(MULTI);
        this.atomic = new AtomicSequenceIdentifier(start);
    }

    private void check(int clusterNode) {
        if(clusterNode < 0) {
            throw new IllegalArgumentException("cluster node number must be 0 or positive integer");
        }
        if(clusterNode > CLUSTER_NODE_MASK) {
            throw new IllegalArgumentException("cluster node number must be lesser than " + (CLUSTER_NODE_MASK + 1));
        }
    }

    public long next() {
        return ((time.next() & TIME_MASK) << 29) | (clusterNode << 20) | (atomic.next() & SEQUENCE_MASK);
    }

    public static class TimeAwareSequence {

        private String hexSequence;
        private long time;
        private int clusterNode;
        private long sequence;

        private TimeAwareSequence(String hexSequence) {
            this.hexSequence = hexSequence;
        }

        public static TimeAwareSequence parse(String hexSequence) {
            try {
                long ts = Long.parseLong(hexSequence, 16);
                TimeAwareSequence sequence = new TimeAwareSequence(hexSequence);
                sequence.parse(ts);
                return sequence;
            } catch (Exception e) {
                throw new IllegalArgumentException("hex sequence is invalid", e);
            }
        }

        private void parse(long ts) {
            time = ((ts >> 29) & TIME_MASK) * MULTI;
            clusterNode = (int)((ts >> 20) & CLUSTER_NODE_MASK);
            sequence = ts & SEQUENCE_MASK;
        }

        public long getTime() {
            return time;
        }

        public int getClusterNode() {
            return clusterNode;
        }

        public long getSequence() {
            return sequence;
        }

        public String toString() {
            return hexSequence + "@time: " + String.format("%tF %<tT,%<tL", time) + ", clusterNode: " + clusterNode + ", sequence: " + sequence;
        }
    }
}
