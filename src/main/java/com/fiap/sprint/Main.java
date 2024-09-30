package com.fiap.sprint;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import com.fiap.sprint.database.ConexaoDB;
import com.fiap.sprint.model.*;

import java.sql.*;
import java.util.Scanner;

import static com.fiap.sprint.model.Administrador.*;
import static com.fiap.sprint.model.Equipamento.criarEquipamento;
import static com.fiap.sprint.model.Estudante.*;
import static com.fiap.sprint.model.Instrutor.criarInstrutor;
import static com.fiap.sprint.model.SessaoTreinamento.criarSessaoTreinamento;
import static com.fiap.sprint.model.Treinamento.criarTreinamento;
import static com.fiap.sprint.model.Usuario.criarUsuario;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConexaoDB conexaoDB = new ConexaoDB();

        // Pergunta pelo tipo de usuário
        System.out.println("Selecione seu tipo de usuário:");
        System.out.println("1. Administrador");
        System.out.println("2. Estudante");
        System.out.println("3. Instrutor");

        int tipoUsuario = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        while (true) {
            // Exibir opções baseadas no tipo de usuário
            switch (tipoUsuario) {
                case 1: // Administrador
                    System.out.println("Selecione uma opção:");
                    System.out.println("1. Criar Usuário");
                    System.out.println("2. Criar Estudante");
                    System.out.println("3. Criar Instrutor");
                    System.out.println("4. Criar Administrador");
                    System.out.println("5. Editar Usuário");
                    System.out.println("6. Deletar Usuário");
                    System.out.println("7. Mostrar Todos os Usuários");
                    System.out.println("8. Sair");

                    break;

                case 2: // Estudante
                    System.out.println("Selecione uma opção:");
                    System.out.println("1. Mostrar Todos os Treinamentos");
                    System.out.println("2. Mostrar Todas as Sessões");
                    System.out.println("3. Sair");

                    break;

                case 3: // Instrutor
                    System.out.println("Selecione uma opção:");
                    System.out.println("1. Criar Treinamento");
                    System.out.println("2. Criar Sessão de Treinamento");
                    System.out.println("3. Criar Equipamento");
                    System.out.println("4. Sair");

                    break;

                default:
                    System.out.println("Tipo de usuário inválido.");
                    return; // Encerra o programa se o tipo for inválido
            }

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            // Executar a ação com base nas opções disponíveis
            switch (tipoUsuario) {
                case 1: // Administrador
                    switch (opcao) {
                        case 1:
                            criarUsuario(scanner, conexaoDB);
                            break;
                        case 2:
                            criarEstudante(scanner, conexaoDB);
                            break;
                        case 3:
                            criarInstrutor(scanner, conexaoDB);
                            break;
                        case 4:
                            criarAdministrador(scanner, conexaoDB);
                            break;
                        case 5:
                            editarUsuario(scanner, conexaoDB);
                            break;
                        case 6:
                            deletarUsuario(scanner, conexaoDB);
                            break;
                        case 7:
                            mostrarUsuarios(conexaoDB);
                            break;
                        case 8:
                            System.out.println("Saindo...");
                            scanner.close();
                            return;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;

                case 2: // Estudante
                    switch (opcao) {
                        case 1:
                            mostrarTreinamentos(conexaoDB);
                            break;
                        case 2:
                            mostrarSessoes(conexaoDB);
                            break;
                        case 3:
                            System.out.println("Saindo...");
                            scanner.close();
                            return;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;

                case 3: // Instrutor
                    switch (opcao) {
                        case 1:
                            criarTreinamento(scanner, conexaoDB);
                            break;
                        case 2:
                            criarSessaoTreinamento(scanner, conexaoDB);
                            break;
                        case 3:
                            criarEquipamento(scanner, conexaoDB);
                            break;
                        case 4:
                            System.out.println("Saindo...");
                            scanner.close();
                            return;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;
            }
        }
    }

}
