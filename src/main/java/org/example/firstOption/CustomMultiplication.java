package org.example.firstOption;

import java.math.BigInteger;

public class CustomMultiplication implements Multiplication {
    @Override
    public BigInteger multiplication(String firstNumber, String secondNumber) {
        int firstLen = firstNumber.length();
        int secondLen = secondNumber.length();
        int[] resultLen = new int[firstLen + secondLen];

        // Prechádzame cez každú číslicu oboch čísel
        for (int i = firstLen - 1; i >= 0; i--) {
            for (int j = secondLen - 1; j >= 0; j--) {
                // Násobíme číslice a pridáme do správneho miesta v výsledku
                int mult = (firstNumber.charAt(i) - '0') * (secondNumber.charAt(j) - '0');
                int sum = mult + resultLen[i + j + 1];

                // Uložíme výsledok na správne miesto
                resultLen[i + j + 1] = sum % 10;
                resultLen[i + j] += sum / 10;
            }
        }

        // Zostavíme výsledný reťazec z výsledkového poľa
        StringBuilder sb = new StringBuilder();
        for (int num : resultLen) {
            if (!(sb.length() == 0 && num == 0)) {
                sb.append(num);
            }
        }

        // Vrátime výsledok ako BigInteger
        return new BigInteger(sb.length() == 0 ? "0" : sb.toString());
    }
}
