package com.simplewebservice.misc.binarysearch;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class BubbleSortAlgorithm implements SortAlgorithm {
    public int[] sort(int[] numbers) {
        for (int x = 0; x < numbers.length; x++) {
            for (int i = 1; i <= numbers.length - 1; i++) {
                if (numbers[i - 1] > numbers[i]) {
                    int temp = numbers[i - 1];
                    numbers[i -1] = numbers[i];
                    numbers[i] = temp;
                }
            }
        }
        return numbers;
    }
}
