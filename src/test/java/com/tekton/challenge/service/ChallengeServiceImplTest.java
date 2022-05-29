package com.tekton.challenge.service;

import com.tekton.challenge.exception.NotValidHeightException;
import com.tekton.challenge.exception.NotValidSizeException;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ChallengeServiceImplTest {
    private ChallengeServiceImpl challengeService;

    @Before
    public void setUp() {
        challengeService = new ChallengeServiceImpl();
    }

    @Test(expected = NotValidSizeException.class)
    public void whenArrayHasOneELement_thenNotValidSizeException() {
        Integer[] data = {1};
        challengeService.maxAreaInAnArray(data);
    }

    @Test(expected = NotValidSizeException.class)
    public void whenArrayHasMoreThan105Elements_thenNotValidSizeException() {
        Integer[] data= new Integer[106];
        initArray(data, 0, 104, 106);
        challengeService.maxAreaInAnArray(data);
    }

    @Test(expected = NotValidHeightException.class)
    public void whenHeightIsMoreThan104_thenNotValidHeightException() {
        Integer[] data= {1,105};
        challengeService.maxAreaInAnArray(data);
    }

    @Test(expected = NotValidHeightException.class)
    public void whenHeightIsLessThanZero_thenNotValidHeightException() {
        Integer[] data= {-1,104};
        challengeService.maxAreaInAnArray(data);
    }

    @Test()
    public void whenArrayIsCorrect_then_OutputCorrect() {
        Integer[] data= {1,1};
        int response = challengeService.maxAreaInAnArray(data);
        assertTrue("Success",response==1);
    }

    @Test()
    public void whenArrayIsCorrectExample2_then_OutputCorrect() {
        Integer[] data= {1,8,6,2,5,4,8,3,7};
        int response = challengeService.maxAreaInAnArray(data);
        assertTrue("Success",response==49);
    }




    @Test
    public void whenExceptionThrown_thenAssertionSucceeds() {
        Integer[] data = {1};
        Exception exception = assertThrows(NotValidSizeException.class, () -> {
            challengeService.maxAreaInAnArray(data);
        });
        String expectedMessage = "height must be greater than 2 and less than 105 it is 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    private void initArray(Integer[] data, int min, int max, int size) {
        for (int i = 0; i < size; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            data[i] = randomNum;
        }
    }

}
