package com.example.login_page;

public class EncryptUtils {

    public static int charToAscii(char c) {
        return (int) c;
    }

    public static char asciiToChar(int ascii) {
        return (char) ascii;
    }

    public static void encryp(int[] asci, int key, int len, int[] en_asci) {
        int mod_key = key % 7;
        for (int i = 0; i < len; ++i) {
            switch (i % 8) {
                case 0:
                    en_asci[i] = (asci[i] + (mod_key - 5) - 32 + 95) % 95 + 32;
                    break;
                case 1:
                    en_asci[i] = (asci[i] + (mod_key * 5) - 32 + 95) % 95 + 32;
                    break;
                case 2:
                    en_asci[i] = (asci[i] + ((key / 2) - 5) - 32 + 95) % 95 + 32;
                    break;
                case 3:
                    en_asci[i] = (asci[i] + (mod_key + 7) - 32 + 95) % 95 + 32;
                    break;
                case 4:
                    en_asci[i] = (asci[i] + mod_key - 32 + 95) % 95 + 32;
                    break;
                case 5:
                    en_asci[i] = (asci[i] + (mod_key + 19) - 32 + 95) % 95 + 32;
                    break;
                case 6:
                    en_asci[i] = (asci[i] - 4 - 32 + 95) % 95 + 32;
                    break;
                case 7:
                    en_asci[i] = (asci[i] + 5 - 32 + 95) % 95 + 32;
                    break;
            }
        }
    }

    public static String encrypt(String password, int key) {
        int len = password.length();
        int[] asci = new int[len];
        int[] en_asci = new int[len];
        char[] encrypted = new char[len];

        for (int i = 0; i < len; i++) {
            asci[i] = charToAscii(password.charAt(i));
        }

        encryp(asci, key, len, en_asci);
        for (int i = 0; i < len; i++) {
            encrypted[i] = asciiToChar(en_asci[i]);
        }

        return new String(encrypted);
    }
}
