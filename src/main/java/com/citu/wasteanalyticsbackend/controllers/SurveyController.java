package com.citu.wasteanalyticsbackend.controllers;


import com.citu.wasteanalyticsbackend.entities.Survey;
import com.citu.wasteanalyticsbackend.entities.WasteEntry;
import com.citu.wasteanalyticsbackend.helper.Helper;
import com.citu.wasteanalyticsbackend.services.EntryService;
import com.citu.wasteanalyticsbackend.services.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v3")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping("survey/add")
    public ResponseEntity<String> addSurvey(@RequestBody Survey surveyData) throws Exception {
        String id = surveyService.addSurvey(surveyData);

        return ResponseEntity.status(HttpStatus.CREATED).body("Survey Logged with ID: " + id);
    }

    @GetMapping("survey/totalResult")
    public ResponseEntity<Map<String, Object>> getallSurvey() throws Exception{
        Map<String, Object> survey = surveyService.getResponses();
        return ResponseEntity.status(HttpStatus.OK).body(survey);
    }

}
