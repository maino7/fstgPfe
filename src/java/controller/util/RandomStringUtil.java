/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.util;

import java.util.Random;

/**
 *
 * @author Abed
 */
public class RandomStringUtil {

    private static final char[] symbols;
    private static final char[] symbolsNum;

    static {
        StringBuilder tmp = new StringBuilder();
        StringBuilder tmpNum = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch) {
            tmp.append(ch);
            tmpNum.append(ch);
        }
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            tmp.append(ch);
        }
        symbols = tmp.toString().toCharArray();
        symbolsNum = tmpNum.toString().toCharArray();
    }

    private static final Random RANDOM = new Random();

    private static final char[] BUF= new char[10];
    private static final char[] BUFNUM= new char[6];

    public static String generate() {
        for (int idx = 0; idx < BUF.length; ++idx) {
            BUF[idx] = symbols[RANDOM.nextInt(symbols.length)];
        }
        return new String(BUF);
    }
    public static String generateNum() {
        for (int idx = 0; idx < BUFNUM.length; ++idx) {
            BUFNUM[idx] = symbolsNum[RANDOM.nextInt(symbolsNum.length)];
        }
        return new String(BUFNUM);
    }
}
