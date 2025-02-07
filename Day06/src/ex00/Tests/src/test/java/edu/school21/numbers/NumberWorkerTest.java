package edu.school21.numbers;

import edu.school21.exceptions.IllegalNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest  {

    @ParameterizedTest
    @ValueSource(ints = {197, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29})
    void isPrimeForPrimes(int numbers) {
        NumberWorker nw = new NumberWorker();
        assertTrue(nw.isPrime(numbers));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 9, 10, 12, 14, 15})
    void isPrimeForNotPrimes(int numbers) {
        NumberWorker nw = new NumberWorker();
        assertFalse(nw.isPrime(numbers));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 0, -1, -2, -10, -25})
    void isPrimeForIncorrectNumbers(int numbers) {
        NumberWorker nw = new NumberWorker();
        assertThrows(IllegalNumberException.class, () ->  nw.isPrime(numbers));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void digitsSum(int numbers, int expectedSum) {
        NumberWorker nw = new NumberWorker();
        assertEquals(expectedSum, nw.digitsSum(numbers));
    }
}
