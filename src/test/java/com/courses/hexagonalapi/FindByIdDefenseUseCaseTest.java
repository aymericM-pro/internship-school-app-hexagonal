package com.courses.hexagonalapi;

import com.courses.hexagonalapi.core.BusinessException;
import com.courses.hexagonalapi.internship.domain.defenses.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindByIdDefenseUseCaseTest {

    @Test
    void shouldReturnDefenseWhenFound() {
        UUID defenseId = UUID.randomUUID();
        Defense expectedDefense = new Defense(defenseId, UUID.randomUUID(), null, null, "", "", DefenseStatus.PENDING_REQUEST);

        DefenseRepositoryFetcher mockFetcher = mock(DefenseRepositoryFetcher.class);
        when(mockFetcher.findById(defenseId)).thenReturn(Optional.of(expectedDefense));

        FindByIdDefenseUseCase useCase = new FindByIdDefenseUseCase(mockFetcher);

        Defense result = useCase.findDefenseById(defenseId);

        assertEquals(expectedDefense, result);
        verify(mockFetcher, times(1)).findById(defenseId);
    }

    @Test
    void shouldThrowBusinessExceptionWhenNotFound() {
        UUID defenseId = UUID.randomUUID();
        DefenseRepositoryFetcher mockFetcher = mock(DefenseRepositoryFetcher.class);
        when(mockFetcher.findById(defenseId)).thenReturn(Optional.empty());

        FindByIdDefenseUseCase useCase = new FindByIdDefenseUseCase(mockFetcher);

        BusinessException thrown = Assertions.assertThrows(BusinessException.class, () -> {
            useCase.findDefenseById(defenseId);
        });

        assertEquals(ErrorDefenseCode.DEFENSE_NOT_FOUND, thrown.getErrorCode());
        assertTrue(thrown.getMessage().contains(defenseId.toString()));
        verify(mockFetcher, times(1)).findById(defenseId);
    }
}
