package net.blogjava.frankiegao123.identifier.sequence;

import net.blogjava.frankiegao123.identifier.SequenceIdentifier;
import net.blogjava.frankiegao123.util.ByteUtil;
import net.blogjava.frankiegao123.util.NumberUtil;

public abstract class HexSequenceIdentifier implements SequenceIdentifier {

    public String nextHex() {
        return ByteUtil.bytes2Str(NumberUtil.long2BytesBE(next()));
    }
}
