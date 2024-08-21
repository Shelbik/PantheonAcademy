package firstOption;

import org.example.firstOption.BigIntMultiplication;
import org.example.firstOption.CustomMultiplication;
import org.example.firstOption.Multiplication;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplicationTest {

    @Test
    public void testMultiplicationEquality() {
        String num1 = "12345678901234567890";
        String num2 = "11111111111111111111";

        Multiplication alg1 = new BigIntMultiplication();
        Multiplication alg2 = new CustomMultiplication();

        BigInteger result1 = alg1.multiplication(num1, num2);
        BigInteger result2 = alg2.multiplication(num1, num2);

        assertEquals(result1, result2);
    }
}
