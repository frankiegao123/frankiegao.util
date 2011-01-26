package net.blogjava.frankiegao123.util;

public class ByteUtil {

    private final static char[] HEX = "0123456789abcdef".toCharArray();

    private ByteUtil() { }

    public static void main(String[] args) {
        String str="我JSp查看tomcat内JS存使用情况";
        str=str.replaceAll("(?<=\\W)(?i:JS)(?=\\w)", "");
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
}
