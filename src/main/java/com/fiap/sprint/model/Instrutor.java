package com.fiap.sprint.model;

import com.fiap.sprint.database.ConexaoDB;

import java.util.Scanner;

public class Instrutor extends Usuario {
    private String especialidade;

    public Instrutor(int id,String nome, String email,String especialidade) {
        super(id,nome,email);
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new IllegalArgumentException("Especialidade não pode ser nula ou vazia.");
        }
        this.especialidade = especialidade;
    }



    public static void criarInstrutor(Scanner scanner, ConexaoDB conexaoDB) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        System.out.print("Especialidade: ");
        scanner.nextLine();
        String especialidade = scanner.nextLine();

        Instrutor instrutor = new Instrutor(id,"nome","email",especialidade);
        conexaoDB.criarInstrutor(instrutor);
        System.out.println("Instrutor criado com sucesso!");
    }




    public String getEspecialidade() {
        return especialidade;
    }

    @Override
    public String getTipoUsuario() {
        return "Instrutor";
    }
}
