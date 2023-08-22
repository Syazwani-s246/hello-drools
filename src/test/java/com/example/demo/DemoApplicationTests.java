package com.example.demo;

import com.example.demo.model.Applicant;
import com.example.demo.model.SuggestedRole;
import com.example.demo.service.ApplicantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private ApplicantService applicantService;

    @Test
    public void whenCriteriaMatching_ThenSuggestManagerRole() {
        Applicant applicant = new Applicant("Adrian", 37, 1600000.0);
        SuggestedRole suggestedRole = new SuggestedRole();

        applicantService.suggestARoleForApplicant(applicant, suggestedRole);

        // Assert that the suggestedRole's role matches the expected value
        assertEquals("Manager", suggestedRole.getRole());
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestSeniorDeveloperRole() {
        Applicant applicant = new Applicant("Alice", 7, 800000.0);
        SuggestedRole suggestedRole = new SuggestedRole();

        applicantService.suggestARoleForApplicant(applicant, suggestedRole);

        // Assert that the suggestedRole's role matches the expected value
        assertEquals("Senior Developer", suggestedRole.getRole());
    }

}
