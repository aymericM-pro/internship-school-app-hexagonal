package com.courses.hexagonalapi;

import com.courses.hexagonalapi.core.BusinessException;
import com.courses.hexagonalapi.internship.domain.defenses.Defense;
import com.courses.hexagonalapi.internship.domain.defenses.DefenseRepositoryFetcher;
import com.courses.hexagonalapi.internship.domain.defenses.DefenseStatus;
import com.courses.hexagonalapi.internship.domain.defenses.FindAllDefenseUseCase;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindAllDefenseUseCaseTest {

    @Test
    void shouldReturnAllDefenses() {
        Defense defense1 = new Defense(UUID.randomUUID(), UUID.randomUUID(), null, null, "", "", DefenseStatus.PENDING_REQUEST);
        Defense defense2 = new Defense(UUID.randomUUID(), UUID.randomUUID(), null, null, "", "", DefenseStatus.PENDING_REQUEST);

        DefenseRepositoryFetcher mockFetcher = mock(DefenseRepositoryFetcher.class);
        when(mockFetcher.findAll()).thenReturn(List.of(defense1, defense2));

        FindAllDefenseUseCase useCase = new FindAllDefenseUseCase(mockFetcher);

        List<Defense> result = useCase.findAllDefenses();

        assertEquals(2, result.size());
        assertTrue(result.contains(defense1));
        assertTrue(result.contains(defense2));
    }

    @Test
    void shouldThrowExceptionWhenNoDefenseFound() {
        DefenseRepositoryFetcher mockFetcher = mock(DefenseRepositoryFetcher.class);
        when(mockFetcher.findAll()).thenReturn(List.of());

        FindAllDefenseUseCase useCase = new FindAllDefenseUseCase(mockFetcher);

        BusinessException exception = assertThrows(
                BusinessException.class,
                useCase::findAllDefenses
        );

        assertEquals("Aucune soutenance trouvée", exception.getMessage());
        assertEquals(404, exception.getErrorCode().getHttpStatusCode());
    }
}