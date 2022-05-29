package com.tekton.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekton.challenge.dto.HeightDto;
import com.tekton.challenge.exception.ControllerAdvisor;
import com.tekton.challenge.exception.NotValidHeightException;
import com.tekton.challenge.exception.NotValidSizeException;
import com.tekton.challenge.response.ResponseHandler;
import com.tekton.challenge.service.ChallengeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringRunner.class)
public class ChallengeControllerTest {
    private final static String NOTIFICATION_URL = "/challenge-portal/v1/calculate-area";
    private ChallengeController challengeController;

    @Mock
    private ChallengeService service;
    private MockMvc mockMvc;

    @Mock
    private ControllerAdvisor controllerAdvisor;

    @Before
    public void setUp() {
        challengeController = new ChallengeController(service);
        this.mockMvc = MockMvcBuilders.standaloneSetup(challengeController)
                .setControllerAdvice(new ControllerAdvisor())
                .build();
    }

    @Test
    public void controllerReturnsOkOnValidRequest() throws Exception {
        final HeightDto heightDto = new HeightDto();
        heightDto.setHeight(new Integer[]{1, 8, 6, 2, 5, 4, 8, 3, 7});

        mockMvc.perform(post(NOTIFICATION_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)

                .content(new ObjectMapper().writeValueAsString(heightDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void controllerReturns4xxOnDtoValidationError() throws Exception {
        final HeightDto heightDto = new HeightDto();
        heightDto.setHeight(new Integer[]{1});
        Mockito.when(service.maxAreaInAnArray(new Integer[]{1})).thenThrow(NotValidSizeException.class);
        mockMvc.perform(post(NOTIFICATION_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(heightDto)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void controllerReturns4xxOnHeightValidationError() throws Exception {
        final HeightDto heightDto = new HeightDto();
        heightDto.setHeight(new Integer[]{1,106});
        Mockito.when(service.maxAreaInAnArray(new Integer[]{1,106})).thenThrow(NotValidHeightException.class);
        mockMvc.perform(post(NOTIFICATION_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(heightDto)))
                .andExpect(status().is4xxClientError());
    }



}
