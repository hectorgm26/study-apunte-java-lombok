package com.proyectolombok.app;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

@Slf4j
public class Main {

    @SneakyThrows(value = {IOException.class, FileNotFoundException.class})
    public static void main(String[] args) {
        Persona persona = new Persona(1, "Hector", "Gonzalez", "hector@uwu.cl", 27, 123456789, LocalDate.now());

        Persona persona2 = Persona.builder()
                .id(2l)
                .nombre("Yanira")
                .build();

        log.info(persona.toString());

        System.out.println(persona);
        System.out.println(persona2);

        @Cleanup
        InputStream input = new FileInputStream("archivo.txt");

        input.close();

    }
}
