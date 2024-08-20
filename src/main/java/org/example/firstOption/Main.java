package org.example.firstOption;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        System.out.println("Program started");


        if (args.length < 3) {
            System.out.println("Usage: --alg1|--alg2 <num1> <num2>");
            return;
        }


        System.out.println("Arguments provided:");
        for (int i = 0; i < args.length; i++) {
            System.out.println("Arg[" + i + "]: " + args[i]);
        }

        String algorithm = args[0];
        String num1 = args[1];
        String num2 = args[2];

        System.out.println("Algorithm: " + algorithm);
        System.out.println("First Number: " + num1);
        System.out.println("Second Number: " + num2);

        Multiplication multiplicationAlgorithm;
        switch (algorithm) {
            case "--alg1":
                multiplicationAlgorithm = new BigIntMultiplication();
                break;
            case "--alg2":
                multiplicationAlgorithm = new CustomMultiplication();
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algorithm);

        }


        BigInteger result = multiplicationAlgorithm.multiplication(num1, num2);
        System.out.println("Multiplication result: " + result);
    }
}
