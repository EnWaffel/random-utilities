package de.enwaffel.randomutils;

public class ByteUtil {

    public static byte[] numberToBytes(int number) {
        char[] chars = String.valueOf(number).toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0;i < chars.length;i++) {
            bytes[i] = digitToByte(Integer.parseInt(String.valueOf(chars[i])));
        }
        return bytes;
    }

    public static int bytesToNumber(byte[] bytes) {
        StringBuilder num = new StringBuilder();
        for (byte b : bytes) {
            num.append(byteToDigit(b));
        }
        return Integer.parseInt(num.toString());
    }

    public static byte digitToByte(int digit) {
        if (digit > 9 || digit < -9) throw new IllegalArgumentException("digit cannot be higher as 9 or lower as -9");
        int result;
        switch (digit) {
            case 1: {
                result = 0x31;
                break;
            }
            case 2: {
                result = 0x32;
                break;
            }
            case 3: {
                result = 0x33;
                break;
            }
            case 4: {
                result = 0x34;
                break;
            }
            case 5: {
                result = 0x35;
                break;
            }
            case 6: {
                result = 0x36;
                break;
            }
            case 7: {
                result = 0x37;
                break;
            }
            case 8: {
                result = 0x38;
                break;
            }
            case 9: {
                result = 0x39;
                break;
            }
            default: {
                result = 0x30;
                break;
            }
        }
        return (byte) result;
    }

    public static int byteToDigit(int b) {
        int result;
        switch (b) {
            case 0x31: {
                result = 1;
                break;
            }
            case 0x32: {
                result = 2;
                break;
            }
            case 0x33: {
                result = 3;
                break;
            }
            case 0x34: {
                result = 4;
                break;
            }
            case 0x35: {
                result = 5;
                break;
            }
            case 0x36: {
                result = 6;
                break;
            }
            case 0x37: {
                result = 7;
                break;
            }
            case 0x38: {
                result = 8;
                break;
            }
            case 0x39: {
                result = 9;
                break;
            }
            default: {
                result = 0;
            }
        }
        return result;
    }

}
