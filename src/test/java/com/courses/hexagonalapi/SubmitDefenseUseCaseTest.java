package com.courses.hexagonalapi;

import com.courses.hexagonalapi.core.BusinessException;
import com.courses.hexagonalapi.internship.application.DefenseRequest;
import com.courses.hexagonalapi.internship.domain.defenses.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SubmitDefenseUseCaseTest {
    DefenseRepositorySaver mockSaver = mock(DefenseRepositorySaver.class);
    SubmitDefenseUseCase useCase = new SubmitDefenseUseCase(mockSaver);

    @Test
    void shouldSaveDefenseSuccessfully() {
        UUID internshipId = UUID.randomUUID();

        DefenseRequest defenseRequest = new DefenseRequest(
                internshipId,
                LocalDate.now(),
                LocalTime.of(10,0).toString(),
                "Presentation finale",
                "Commentaires");

        useCase.saveDefense(defenseRequest);

        verify(mockSaver, times(1)).save(any(Defense.class));
    }

    @Test
    void shouldThrowExceptionWhenNotFoundDefense() {
        UUID id = UUID.randomUUID();
        DefenseRepositoryFetcher mockFetcher = mock(DefenseRepositoryFetcher.class);
        when(mockFetcher.findById(id)).thenReturn(Optional.empty());

        FindByIdDefenseUseCase useCase = new FindByIdDefenseUseCase(mockFetcher);

        assertThrows(BusinessException.class, () -> useCase.findDefenseById(id));
    }
}
