package com.courses.hexagonalapi;


import com.courses.hexagonalapi.core.BusinessException;
import com.courses.hexagonalapi.internship.domain.internships.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindInternshipByIdUseCaseTest {

    private final InternshipRepositoryFetcher repositoryFetcher = mock(InternshipRepositoryFetcher.class);
    private final FindInternshipByIdUseCase useCase = new FindInternshipByIdUseCase(repositoryFetcher);

    @Test
    void shouldReturnInternshipWhenFound() {
        // given
        UUID internshipId = UUID.randomUUID();
        Internship expected = new Internship(
                internshipId,
                "Stage Java",
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 12, 31),
                InternshipStatus.SUBMITTED
        );

        when(repositoryFetcher.findById(internshipId)).thenReturn(Optional.of(expected));

        // when
        Internship result = useCase.findById(internshipId);

        // then
        assertNotNull(result);
        assertEquals(expected, result);
        verify(repositoryFetcher, times(1)).findById(internshipId);
    }

    @Test
    void shouldThrowBusinessExceptionWhenNotFound() {
        // given
        UUID internshipId = UUID.randomUUID();
        when(repositoryFetcher.findById(internshipId)).thenReturn(Optional.empty());

        // when + then
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            useCase.findById(internshipId);
        });

        assertEquals(ErrorInternshipCode.INTERNSHIP_NOT_FOUND, exception.getErrorCode());
        assertTrue(exception.getMessage().contains("Stage introuvable avec l’ID"));
        verify(repositoryFetcher, times(1)).findById(internshipId);
    }
}
