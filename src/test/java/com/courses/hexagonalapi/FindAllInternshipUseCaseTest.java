package com.courses.hexagonalapi;

import com.courses.hexagonalapi.core.BusinessException;
import com.courses.hexagonalapi.internship.domain.internships.ErrorInternshipCode;
import com.courses.hexagonalapi.internship.domain.internships.FindAllInternshipUseCase;
import com.courses.hexagonalapi.internship.domain.internships.Internship;
import com.courses.hexagonalapi.internship.domain.internships.InternshipRepositoryFetcher;
import com.courses.hexagonalapi.internship.domain.internships.InternshipStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindAllInternshipUseCaseTest {

    private final InternshipRepositoryFetcher repositoryFetcher = mock(InternshipRepositoryFetcher.class);
    private final FindAllInternshipUseCase useCase = new FindAllInternshipUseCase(repositoryFetcher);

    @Test
    void shouldReturnListOfInternships() {
        Internship internship1 = new Internship(
                UUID.randomUUID(),
                "Stage A",
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 12, 1),
                InternshipStatus.SUBMITTED,
                null
        );

        Internship internship2 = new Internship(
                UUID.randomUUID(),
                "Stage B",
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2026, 1, 15),
                InternshipStatus.SUBMITTED,
                null
        );


        List<Internship> mockList = List.of(internship1, internship2);
        when(repositoryFetcher.findAll()).thenReturn(mockList);

        List<Internship> result = useCase.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(mockList, result);
        verify(repositoryFetcher, times(1)).findAll();
    }

    @Test
    void shouldThrowWhenNoInternshipsFound() {
         when(repositoryFetcher.findAll()).thenReturn(List.of());

         BusinessException exception = assertThrows(BusinessException.class, () -> useCase.findAll());
        assertEquals(ErrorInternshipCode.INTERNSHIP_NOT_FOUND, exception.getErrorCode());
        assertEquals("Aucun stage trouvé", exception.getMessage());
        verify(repositoryFetcher, times(1)).findAll();
    }
}
