package com.simplewebservice.etc.binarysearch;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class QuickSortAlgorithm implements SortAlgorithm {
    public int[] sort(int[] numbers) {
        Arrays.sort(numbers);
        return numbers;
    }
}
