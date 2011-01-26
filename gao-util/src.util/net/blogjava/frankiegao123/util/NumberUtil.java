package net.blogjava.frankiegao123.util;

public class NumberUtil {

    private NumberUtil(){}
    
    public static byte[] int2BytesBE(int num) {
        byte[] bys = new byte[4];
        bys[0] = (byte)((num >>> 24) & 0xff);
        bys[1] = (byte)((num >>> 16) & 0xff);
        bys[2] = (byte)((num >>>  8) & 0xff);
        bys[3] = (byte)(num & 0xff);
        return bys;
    }

    public static byte[] int2BytesLE(int num) {
        byte[] bys = new byte[8];
        bys[0] = (byte)(num & 0xff);
        bys[1] = (byte)((num >>>  8) & 0xff);
        bys[2] = (byte)((num >>> 16) & 0xff);
        bys[3] = (byte)((num >>> 24) & 0xff);
        return bys;
    }

    public static int byte2IntBE(byte[] bys) {
        if(bys == null || bys.length != 4) {
            throw new IllegalArgumentException("int must be 4 bytes");
        }
        int num = 0;
        num |= (bys[0] & 0xff) << 24;
        num |= (bys[1] & 0xff) << 16;
        num |= (bys[2] & 0xff) <<  8;
        num |= (bys[3] & 0xff);
        return num;
    }

    public static int byte2IntLE(byte[] bys) {
        if(bys == null || bys.length != 4) {
            throw new IllegalArgumentException("int must be 4 bytes");
        }
        int num = 0;
        num |= (bys[0] & 0xff);
        num |= (bys[1] & 0xff) <<  8;
        num |= (bys[2] & 0xff) << 16;
        num |= (bys[3] & 0xff) << 24;
        return num;
    }

    public static byte[] long2BytesBE(long num) {
        byte[] bys = new byte[8];
        bys[0] = (byte)((num >>> 56) & 0xff);
        bys[1] = (byte)((num >>> 48) & 0xff);
        bys[2] = (byte)((num >>> 40) & 0xff);
        bys[3] = (byte)((num >>> 32) & 0xff);
        bys[4] = (byte)((num >>> 24) & 0xff);
        bys[5] = (byte)((num >>> 16) & 0xff);
        bys[6] = (byte)((num >>>  8) & 0xff);
        bys[7] = (byte)(num & 0xff);
        return bys;
    }

    public static byte[] long2BytesLE(long num) {
        byte[] bys = new byte[8];
        bys[0] = (byte)(num & 0xff);
        bys[1] = (byte)((num >>>  8) & 0xff);
        bys[2] = (byte)((num >>> 16) & 0xff);
        bys[3] = (byte)((num >>> 24) & 0xff);
        bys[4] = (byte)((num >>> 32) & 0xff);
        bys[5] = (byte)((num >>> 40) & 0xff);
        bys[6] = (byte)((num >>> 48) & 0xff);
        bys[7] = (byte)((num >>> 56) & 0xff);
        return bys;
    }

    public static long byte2LongBE(byte[] bys) {
        if(bys == null || bys.length != 8) {
            throw new IllegalArgumentException("long must be 8 bytes");
        }
        long num = 0L;
        num |= (bys[0] & 0xffL) << 56;        
        num |= (bys[1] & 0xffL) << 48;
        num |= (bys[2] & 0xffL) << 40;
        num |= (bys[3] & 0xffL) << 32;
        num |= (bys[4] & 0xffL) << 24;
        num |= (bys[5] & 0xffL) << 16;
        num |= (bys[6] & 0xffL) <<  8;
        num |= (bys[7] & 0xffL);
        return num;
    }

    public static long byte2LongLE(byte[] bys) {
        if(bys == null || bys.length != 8) {
            throw new IllegalArgumentException("long must be 8 bytes");
        }
        long num = 0L;
        num |= (bys[0] & 0xffL);
        num |= (bys[1] & 0xffL) <<  8;
        num |= (bys[2] & 0xffL) << 16;
        num |= (bys[3] & 0xffL) << 24;
        num |= (bys[4] & 0xffL) << 32;
        num |= (bys[5] & 0xffL) << 40;
        num |= (bys[6] & 0xffL) << 48;
        num |= (bys[7] & 0xffL) << 56;
        return num;
    }
}
