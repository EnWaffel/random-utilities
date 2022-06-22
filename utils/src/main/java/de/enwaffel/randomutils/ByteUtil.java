package de.enwaffel.randomutils;

public class ByteUtil {

    public static byte[] numberToBytes(int number) {
        char[] chars = String.valueOf(number).toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0;i < chars.length;i++) {
            bytes[i] = singleDigitToByte(Integer.parseInt(String.valueOf(chars[i])));
        }
        return bytes;
    }

    public static byte singleDigitToByte(int digit) {
        if (digit > 9 || digit < -9) throw new IllegalArgumentException("digit cannot be higher as 9 or lower as -9");
        return switch (digit) {
            case 1 -> 0x31;
            case 2 -> 0x32;
            case 3 -> 0x33;
            case 4 -> 0x34;
            case 5 -> 0x35;
            case 6 -> 0x36;
            case 7 -> 0x37;
            case 8 -> 0x38;
            case 9 -> 0x39;
            default -> 0x30;
        };
    }

    public static int byteToSingleDigit(int b) {
        return switch (b) {
            case 0x31 -> 1;
            case 0x32 -> 2;
            case 0x33 -> 3;
            case 0x34 -> 4;
            case 0x35 -> 5;
            case 0x36 -> 6;
            case 0x37 -> 7;
            case 0x38 -> 8;
            case 0x39 -> 9;
            default -> 0;
        };
    }

}
