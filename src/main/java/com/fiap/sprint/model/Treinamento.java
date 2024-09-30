package com.fiap.sprint.model;

import com.fiap.sprint.database.ConexaoDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Treinamento {
    private int id;

    private int idAluno;

    private int idInstrutor;
    private String titulo;
    private String descricao;
    private List<SessaoTreinamento> sessoes;

    public Treinamento(int id, int idAluno,int idInstrutor,String titulo, String descricao) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título do treinamento não pode ser nulo ou vazio.");
        }
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do treinamento não pode ser nula ou vazia.");
        }

        this.id = id;
        this.idAluno = idAluno;
        this.idInstrutor = idInstrutor;
        this.titulo = titulo;
        this.descricao = descricao;
        this.sessoes = new ArrayList<>();
    }



    public static void criarTreinamento(Scanner scanner, ConexaoDB conexaoDB) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("ID do  aluno: ");
        int idAluno = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ID do instrutor: ");
        int idInstrutor = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Treinamento treinamento = new Treinamento(id,idAluno ,idInstrutor,titulo, descricao);
        conexaoDB.criarTreinamento(treinamento);
        System.out.println("Treinamento criado com sucesso!");
    }

    public boolean removerSessao(int idSessao) {
        return sessoes.removeIf(sessao -> sessao.getId() == idSessao);
    }

    // Método para listar todas as sessões
    public void listarSessoes() {
        if (sessoes.isEmpty()) {
            System.out.println("Nenhuma sessão disponível para o treinamento: " + titulo);
            return;
        }
        System.out.println("Sessões do treinamento: " + titulo);
        for (SessaoTreinamento sessao : sessoes) {
            System.out.println("ID: " + sessao.getId() + ", Data: " + sessao.getData() + ", Status: " + sessao.getStatus());
        }
    }

    @Override
    public String toString() {
        return "Treinamento{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", sessoes=" + sessoes.size() +
                '}';
    }
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }


    public List<SessaoTreinamento> getSessoes() {
        return sessoes;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public int getIdInstrutor() {
        return idInstrutor;
    }
}
