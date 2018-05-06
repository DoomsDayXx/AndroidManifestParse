package com.shabi.androidmanifest;

import java.io.*;

public class Utils {
    public static byte[] getBytye(File file) {
        try {
            if (file == null || !file.exists() || file.isDirectory()) return null;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buff = new byte[1024];
            while ((len = bis.read(buff, 0, buff.length)) > 0) {
                baos.write(buff, 0, len);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] copy(byte[] data, int offset, int len) {
        byte[] result = new byte[len];
        System.arraycopy(data, offset, result, 0, len);
        return result;
    }


    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int index = bytes.length - 1; index >= 0; index--) {
            String hex = Integer.toHexString(bytes[index] & 0xff);
            if (hex.length() < 2) {
                sb.append("0").append(hex);
            } else sb.append(hex);
        }
        return "0x" + sb.toString();
    }

    public static String conver2HexStr(byte[] b) {
        StringBuilder result = new StringBuilder();
        for (byte aB : b)
            result.append(Long.toString(aB & 0xff, 2)).append(",");
        return result.toString().substring(0, result.length() - 1);
    }

    public static int bytes2Int(byte[] bytes) {
        if (bytes.length == 2) {
            return (bytes[0] & 0xff)
                    | ((bytes[1] << 8) & 0xff00);
        }
        return (bytes[0] & 0xff)
                | ((bytes[1] << 8) & 0xff00)
                | ((bytes[2] << 24) >>> 8)
                | (bytes[3] << 24);
    }

    public static short byte2Short(byte[] b) {
        short s = 0;
        short s0 = (short) (b[0] & 0xff);// 最低位
        short s1 = (short) (b[1] & 0xff);
        s1 <<= 8;
        s = (short) (s0 | s1);
        return s;
    }

    public static String filtterString(byte[] bytes) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (byte aByte : bytes) {
            if (aByte != 0) {
                baos.write(aByte);
            }
        }
        return new String(baos.toByteArray());
    }

    public static int getChunkType(byte[] data) {
        return Utils.bytes2Int(Utils.copy(data, 0, 4));
    }

}
