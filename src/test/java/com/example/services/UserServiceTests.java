package com.example.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.security.entities.OurUser;
import com.example.security.entities.Role;
import com.example.security.repository.OurUserRepository;
import com.example.security.services.OurUserDetailsService;

import exceptions.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserServiceTests {

    @Mock
    private OurUserRepository ourUserRepository;

    @InjectMocks
    private OurUserDetailsService ourUserDetailsService;

    private OurUser ourUser;

    @BeforeEach
    void setUp() {
        ourUser = OurUser.builder()
                .email("vrmachado@gmail.com")
                .password("Temp2023$$")
                .role(Role.ADMIN)
                .build();
    }

    // Test para guardar un user y que se genere una exception
    // Verifica que nunca se sea posible agregar un empleado cuyo
    // email ya exista
    @Test
    @DisplayName("Test para guardar un user y genere una exception")
    public void testSaveUserWithThrowException() {

        // given
        given(ourUserRepository.findByEmail(ourUser.getEmail()))
                .willReturn(Optional.of(ourUser));

        // when
        assertThrows(ResourceNotFoundException.class, () -> {
            ourUserDetailsService.add(ourUser);
        });

        // Then
        verify(ourUserRepository, never()).save(any(OurUser.class));

    }

}
