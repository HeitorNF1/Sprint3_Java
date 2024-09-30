package com.fiap.sprint.model;

import com.fiap.sprint.database.ConexaoDB;
import com.fiap.sprint.exceptions.RegistroSessaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Estudante extends Usuario {
    private int matricula;
    private List<SessaoTreinamento> sessoesRegistradas = new ArrayList<>();

    public Estudante(int id, String nome, String email, int matricula) {
        super(id, nome, email);
        if (matricula <= 0) {
            throw new IllegalArgumentException("Matrícula deve ser um número positivo.");
        }
        this.matricula = matricula;
    }



    public static void criarEstudante(Scanner scanner, ConexaoDB conexaoDB) {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Matrícula: ");
            int matricula = scanner.nextInt();

            Estudante estudante = new Estudante(id, nome, email, matricula);
            conexaoDB.criarEstudante(estudante);
            System.out.println("Estudante criado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao criar estudante: " + e.getMessage());
        }
    }

    public static void mostrarTreinamentos(ConexaoDB conexaoDB) {
        // Método para mostrar todos os treinamentos
        for (Treinamento treinamento : conexaoDB.getAllTreinamentos()) {
            System.out.println(treinamento);
        }
    }

    public static void mostrarSessoes(ConexaoDB conexaoDB) {
        // Método para mostrar todas as sessões
        for (SessaoTreinamento sessao : conexaoDB.getAllSessoes()) {
            System.out.println(sessao);
        }
    }

    public int getMatricula() {
        return matricula;
    }

    @Override
    public String getTipoUsuario() {
        return "Estudante";
    }
}
