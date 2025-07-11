package com.courses.hexagonalapi;

import com.courses.hexagonalapi.core.BusinessException;
import com.courses.hexagonalapi.internship.application.SubmitInternshipRequest;
import com.courses.hexagonalapi.internship.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SubmitInternshipUseCaseTest {
    private final InternshipRepositorySaver repositorySaver = mock(InternshipRepositorySaver.class);
    private final SubmitInternshipUseCase useCase = new SubmitInternshipUseCase(repositorySaver);

    @Test
    void shouldSaveInternshipSuccessfully() {
        SubmitInternshipRequest request = new SubmitInternshipRequest(
                "Stage génial",
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 12, 31)
        );

        Internship result = useCase.save(request);

        verify(repositorySaver, times(1)).save(any(Internship.class));
        assertNotNull(result);
        assertNotNull(result.internshipId());
        assertEquals(request.title(), result.title());
        assertEquals(request.startDate(), result.startDate());
        assertEquals(request.endDate(), result.endDate());
        assertEquals(InternshipStatus.SUBMITTED, result.status());
    }

    @Test
    void shouldThrowExceptionWhenStartDateAfterEndDate() {
        SubmitInternshipRequest invalidRequest = new SubmitInternshipRequest(
                "Stage invalide",
                LocalDate.of(2025, 12, 31),
                LocalDate.of(2025, 9, 1)
        );

        BusinessException exception = assertThrows(BusinessException.class, () -> useCase.save(invalidRequest));

        assertEquals("La date de début ne peut pas être après la date de fin", exception.getMessage());
        assertEquals(ErrorInternshipCode.INVALID_INTERNSHIP_DATA, exception.getErrorCode());

        verify(repositorySaver, never()).save(any());
    }
}
