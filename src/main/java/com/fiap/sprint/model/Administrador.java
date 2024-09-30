package com.fiap.sprint.model;

import com.fiap.sprint.database.ConexaoDB;

import java.util.Scanner;

public class Administrador extends Usuario {
    private String cargo;

    public Administrador(int id, String nome, String email, String cargo) {
        super(id, nome, email);
        if (cargo == null || cargo.isEmpty()) {
            throw new IllegalArgumentException("O cargo não pode ser nulo ou vazio.");
        }
        this.cargo = cargo;
    }



    public static void criarAdministrador(Scanner scanner, ConexaoDB conexaoDB) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        System.out.print("cargo: ");
        scanner.nextLine();
        String cargo = scanner.nextLine();

        Administrador administrador = new Administrador(id, "-", "-", cargo);
        conexaoDB.criarAdministrador(administrador);
        System.out.println("Administrador criado com sucesso!");
    }


    public static void editarUsuario(Scanner scanner, ConexaoDB conexaoDB) {
        System.out.print("Informe o ID do usuário a ser editado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo Email: ");
        String email = scanner.nextLine();

        conexaoDB.editarUsuario(id, nome, email);
        System.out.println("Usuário editado com sucesso!");
    }

    public static void deletarUsuario(Scanner scanner, ConexaoDB conexaoDB) {
        System.out.print("Informe o ID do usuário a ser deletado: ");
        int id = scanner.nextInt();
        conexaoDB.deletarUsuario(id);
        System.out.println("Usuário deletado com sucesso!");
    }

    public static void mostrarUsuarios(ConexaoDB conexaoDB) {
        // Método para mostrar todos os usuários
        for (Usuario usuario : conexaoDB.getAllUsuarios()) {
            System.out.println(usuario);
        }
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public String getTipoUsuario() {
        return "Administrador";
    }
}
