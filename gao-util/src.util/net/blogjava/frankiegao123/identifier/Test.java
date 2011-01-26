package net.blogjava.frankiegao123.identifier;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import net.blogjava.frankiegao123.identifier.sequence.TimeAwareSequenceIdentifier;
import net.blogjava.frankiegao123.identifier.sequence.TimeAwareSequenceIdentifier.TimeAwareSequence;

public class Test {

    private AtomicInteger identity;

    private final static Test instance = new Test();

    public static Test getInstance() {
        return instance;
    }

    private Test() {
        this.identity = new AtomicInteger(0);
    }
    
    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            System.out.println(instance.next());
        }
    }

    public String next() {
        Calendar c = Calendar.getInstance();
        long x = c.get(Calendar.YEAR);
        x *= 100;
        x += c.get(Calendar.MONTH) + 1;
        x *= 100;
        x += c.get(Calendar.DAY_OF_MONTH);
        x *= 100;
        x += c.get(Calendar.HOUR_OF_DAY);
        x *= 100;
        x += c.get(Calendar.MINUTE);
        x *= 100;
        x += c.get(Calendar.SECOND);
        x *= 10000;
        x += (identity.getAndIncrement() & 0x7fffffff) % 10000L;
        return String.valueOf(x);
    }
    
    
    public static void mainx(String[] args) throws InterruptedException {
        SequenceIdentifier id = TimeAwareSequenceIdentifier.createStartIdentifier(12340);
        for(int i = 0; i < 10; i++) {
            String x = id.nextHex();
            System.out.println(x + " " + TimeAwareSequence.parse(x));
            Thread.sleep(100L);
        }
        System.out.println(0xfffffL);
        System.out.println(new Date(0x7ffffffffL * 200));
    }
}
