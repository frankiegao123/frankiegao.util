package net.blogjava.frankiegao123.util;

import java.math.BigInteger;

public class ByteUtil {

    private final static char[] HEX = "0123456789abcdef".toCharArray();
    private final static char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private final static BigInteger SIXTY_TWO = new BigInteger("62");
    private final static double LOG_62 = Math.log(62);
    private ByteUtil() { }

    public static void main(String[] args) {
        String n = "zzzzzzzzzzzzzzzz";
        long t0, t1;
        t0 = System.nanoTime();
        byte[] bys = base62ToBytes(n);
        t1 = System.nanoTime();
        System.out.println(t1 - t0);
        bys = new byte[]{ (byte)0x7f, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, 
                (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, 
                (byte)0xff, (byte)0xff};
        System.out.println(new BigInteger(1, bys) + " --> ");
        t0 = System.nanoTime();
        String str = bytesToBase62(bys);
        t1 = System.nanoTime();
        System.out.println(t1 - t0);
        System.out.println(str);
    }

    public static char toHex(int n) {
        return HEX[n & 0xf];
    }

    public static String bytes2StrSpace(byte[] bys) {
        char[] chs = new char[bys.length * 3 - 1];
        for(int i = 0, k = 0; i < bys.length; i++) {
            if(k > 0) {
                chs[k++] = ' ';
            }
            chs[k++] = HEX[(bys[i] >> 4) & 0xf];
            chs[k++] = HEX[bys[i] & 0xf];
        }
        return new String(chs);
    }

    public static String bytes2Str(byte[] bys) {
        char[] chs = new char[bys.length * 2];
        for(int i = 0, k = 0; i < bys.length; i++) {
            chs[k++] = HEX[(bys[i] >> 4) & 0xf];
            chs[k++] = HEX[bys[i] & 0xf];
        }
        return new String(chs);
    }

    public static String byte2Str(byte b) {
        char[] chs = new char[2];
        chs[0] = HEX[(b >> 4) & 0xf];
        chs[1] = HEX[b & 0xf];
        return new String(chs);
    }

    public static byte[] base62ToBytes(String base62) {
        BigInteger bi = BigInteger.ZERO;
        char[] chs = base62.toCharArray();
        for(int i = 0; i < chs.length; i++) {
            bi = bi.multiply(SIXTY_TWO).add(new BigInteger(String.valueOf(base62ToInt(chs[i]))));
        }
        return bi.toByteArray();
    }

    public static String bytesToBase62(byte[] bys) {
        BigInteger bi = new BigInteger(1, bys);
        char[] chs = new char[(int)(Math.ceil(Math.log(bi.doubleValue()) / LOG_62))];
        int n = chs.length;
        while(bi.compareTo(BigInteger.ZERO) != 0) {
            chs[--n] = BASE62[bi.mod(SIXTY_TWO).intValue()];
            bi = bi.divide(SIXTY_TWO);
        }
        return new String(chs);
    }

    private static int base62ToInt(char c) {
        if(c >= '0' && c <= '9') {
            return c - '0';
        }
        if(c >= 'A' && c <= 'Z') {
            return c - 'A' + 10;
        }
        if(c >= 'a' && c <= 'z') {
            return c - 'a' + 36;
        }
        throw new NumberFormatException("char " + c + " isnot 62-radix number");
    }
}
