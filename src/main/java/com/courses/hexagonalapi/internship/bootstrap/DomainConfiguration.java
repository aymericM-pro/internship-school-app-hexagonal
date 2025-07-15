package com.courses.hexagonalapi.internship.bootstrap;

import com.courses.hexagonalapi.internship.domain.defenses.DefenseRepositorySaver;
import com.courses.hexagonalapi.internship.domain.defenses.SubmitDefenseUseCase;
import com.courses.hexagonalapi.internship.domain.internships.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public FindInternshipByIdUseCase findInternshipByIdUseCase(
                        InternshipRepositoryFetcher internshipRepositoryFetcher) {
        return new FindInternshipByIdUseCase(internshipRepositoryFetcher);
    }

    @Bean
    public SubmitInternshipUseCase SubmitInternshipUseCase(InternshipRepositorySaver internshipRepositorySaver) {
        return new SubmitInternshipUseCase(internshipRepositorySaver);
    }

    @Bean
    public FindAllInternshipUseCase findAllInternshipUseCase(
            InternshipRepositoryFetcher internshipRepositoryFetcher) {
        return new FindAllInternshipUseCase(internshipRepositoryFetcher);
    }

    @Bean
    public SubmitDefenseUseCase submitDefenseUseCase(DefenseRepositorySaver defenseRepositorySaver) {
        return new SubmitDefenseUseCase(defenseRepositorySaver);
    }
}
