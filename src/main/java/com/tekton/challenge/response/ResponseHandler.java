package com.tekton.challenge.response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("timestamp", LocalDateTime.now());
        map.put("message", message);
        map.put("status", status.value());
        map.put("response", responseObj);
        return new ResponseEntity<Object>(map,status);
    }
}
