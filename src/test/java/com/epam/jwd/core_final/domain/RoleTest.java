package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoleTest {

    List<Integer> invalidIds;

    @BeforeEach
    void setUp() {
        List<Integer> generatedInvalidIds = Stream.of(
                new Random()
                        .ints(100_000, 5, 100_000_000)
                        .boxed()
                        .collect(Collectors.toList()),
                new Random()
                        .ints(100_000, -100_000_000, 1)
                        .boxed()
                        .collect(Collectors.toList())
        )
                .flatMap(List::stream)
                .collect(Collectors.toList());
        invalidIds = generatedInvalidIds;
    }

    @Test
    void resolveRoleById() {
        //assertEquals(1, 1);
        Role role = Role.COMMANDER;
        assertEquals(Role.resolveRoleById(4), role);
    }

    @Test
    void resolveRoleByInvalidId() {
        for (Integer id : invalidIds) {
            assertThrows(UnknownEntityException.class, () -> {
                Role.resolveRoleById(id);
            });
        }
    }
}