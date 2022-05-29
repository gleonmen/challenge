package com.tekton.challenge.controller;

import com.tekton.challenge.dto.HeightDto;
import com.tekton.challenge.response.ResponseHandler;
import com.tekton.challenge.service.ChallengeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.tekton.challenge.controller.ChallengeController.PORTAL_BASE_URL;


@Slf4j
@Validated
@RestController
@RequestMapping(PORTAL_BASE_URL)
public class ChallengeController {
    public static final String PORTAL_BASE_URL = "/challenge-portal/v1";

    private final ChallengeService challengeService;
    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @PostMapping(value = "/calculate-area",produces = MediaType.APPLICATION_JSON_VALUE ,consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Object> calculateArea( @RequestBody @Valid HeightDto dataDto) {
        int result = challengeService.maxAreaInAnArray(dataDto.getHeight());
        return ResponseHandler.generateResponse("Max Area Challenge", HttpStatus.OK, result);
    }
}
