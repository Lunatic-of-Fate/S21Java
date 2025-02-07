package edu.school21.numbers;

import edu.school21.exceptions.IllegalNumberException;
import lombok.NoArgsConstructor;


@NoArgsConstructor

public class NumberWorker {

    public boolean isPrime(int number) {
        if(number < 2) throw new IllegalNumberException("Число некорректное");
        int del = (int)Math.sqrt(number);
        for(int i = 2; i <= del; i++) {
            if(i > 2 && i % 2 == 0) continue;
            if(number % i == 0) return false;
        }
        return true;
    }

    public int digitsSum(int number) {
        boolean flag = false;
        if (number < 0) {
            number = number * -1;
            flag = true;
        }
        int result = 0;
        while(number > 0) {
            result += number % 10;
            number /= 10;
        }
        if(flag) result = result * -1;
        return result;
    }


}
