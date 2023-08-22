package com.example.demo.service;

//package com.baeldung.drools.service;

import com.example.demo.model.*;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantService {

    private final KieSession kieSession;

    @Autowired
    public ApplicantService(KieSession kieSession) {
        this.kieSession = kieSession;
    }

    public SuggestedRole suggestARoleForApplicant(Applicant applicant, SuggestedRole suggestedRole) {
        kieSession.insert(applicant);
        kieSession.setGlobal("suggestedRole", suggestedRole);
        kieSession.fireAllRules();
        return suggestedRole;
    }
}
