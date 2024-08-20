package org.example.firstOption;

import java.math.BigInteger;

public class BigIntMultiplication implements Multiplication {
    @Override
    public BigInteger multiplication(String firstNumber, String secondNumber) {
        BigInteger firstNum = new BigInteger(firstNumber);
        BigInteger secondNum = new BigInteger(secondNumber);
        return firstNum.multiply(secondNum);

    }
}
