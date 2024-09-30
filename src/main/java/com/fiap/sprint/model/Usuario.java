package com.fiap.sprint.model;

import com.fiap.sprint.database.ConexaoDB;

import java.util.Scanner;

public abstract class Usuario implements UsuarioInterface {
    private int id;
    private String nome;
    private String email;

    public Usuario(int id, String nome, String email) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("O email não pode ser nulo ou vazio.");
        }

        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Usuario(int id) {
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public abstract String getTipoUsuario();

    public static void criarUsuario(Scanner scanner, ConexaoDB conexaoDB) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(id, nome, email) {
            @Override
            public String getTipoUsuario() {
                return "Usuário";
            }
        };

        conexaoDB.criarUsuario(usuario);
        System.out.println("Usuário criado com sucesso!");
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", tipoUsuario='" + getTipoUsuario() + '\'' +
                '}';
    }
}
