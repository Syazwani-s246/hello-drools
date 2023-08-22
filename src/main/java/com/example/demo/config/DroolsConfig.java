package com.example.demo.config;


import com.example.demo.model.Applicant;
import com.example.demo.model.SuggestedRole;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class DroolsConfig {

    private static final String RULES_PATH = "rules/"; //path rule file

    @Bean
    public KieServices kieServices() {
        return KieServices.Factory.get();
    }

    @Bean
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices().newKieFileSystem();
        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(
                    ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
        }
        return kieFileSystem;
    }

    @Bean
    public KieContainer kieContainer() throws IOException {
        KieServices kieServices = kieServices();
        KieRepository kieRepository = kieServices.getRepository();

        kieRepository.addKieModule(new KieModule() {
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();

        return kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
    }



    @Bean
    public KieSession kieSession() throws IOException {
        return kieContainer().newKieSession();
    }

    private Resource[] getRuleFiles() throws IOException {
        return new Resource[]{
                new ClassPathResource("rules/role_suggestion.drl") //path ke drl file
                // Add more rule files if ada
        };
    }
}
