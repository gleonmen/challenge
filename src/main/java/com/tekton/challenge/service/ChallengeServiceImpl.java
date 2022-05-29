package com.tekton.challenge.service;

import com.tekton.challenge.exception.NotValidHeightException;
import com.tekton.challenge.exception.NotValidSizeException;
import org.springframework.stereotype.Service;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Override
    public int maxAreaInAnArray(Integer[] data) {
        int size = data.length;

        if (size < 2 || size > 105) {
            throw new NotValidSizeException(size);
        }

        int maxArea = 0;
        for (int i = 0; i < size; i++) {
            int lateralWall = data[i];
            if (lateralWall < 0 || lateralWall > 104) {
                throw new NotValidHeightException(lateralWall);
            }
            maxArea = findMax(lateralWall, maxArea);
            maxArea = compareMaxArea(data, size, maxArea, i, lateralWall);
        }
        return maxArea;
    }

    private int compareMaxArea(Integer[] data, int size, int maxArea, int i, int lateralWall) {
        for (int j = i + 1; j < size; j++) {
            int high = data[j];
            if (high <= lateralWall) {
                int base = j - i;
                maxArea = findMax(maxArea, high * base);
            }
        }
        return maxArea;
    }

    private int findMax(int a, int b) {
        int max = (a > b) ? a : b;
        return max;
    }
}
