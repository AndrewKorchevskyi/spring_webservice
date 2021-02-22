package other.binarysearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BinarySearchImpl {

    @Autowired
    private SortAlgorithm sortAlgorithm;

    public int search(int[] numbers, int numberToSearch) {
        int[] sortedNumbers = sortAlgorithm.sort(numbers);
        int first = 0;
        int last = sortedNumbers.length - 1;
        int mid = (first + last) / 2;
        while (first <= last) {
            if (sortedNumbers[mid] < numberToSearch) {
                first = mid + 1;
            } else if ( sortedNumbers[mid] == numberToSearch ) {
                return mid;
            } else {
                last = mid - 1;
            }
            mid = (first + last) / 2;
        }
        return -1;
    }
}
