package com.fiap.sprint.model;

import com.fiap.sprint.database.ConexaoDB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SessaoTreinamento {
    private int id;
    private Date data;
    private int duracao;
    private String status;

    private int idTreinamento;
    private String feedback;

    private int nota;

    public SessaoTreinamento(int id, Date data, int duracao, String status, int idTreinamento, String feedback, int nota) {
        if (duracao <= 0) {
            throw new IllegalArgumentException("A duração deve ser positiva.");
        }

        if (!isStatusValido(status)) {
            throw new IllegalArgumentException("Status inválido. Deve ser 'Agendada', 'Em Andamento' ou 'Finalizada'.");
        }

        this.id = id;
        this.data = data;
        this.duracao = duracao;
        this.status = status;
        this.idTreinamento = idTreinamento;
        this.feedback = feedback;
        this.nota = nota;

    }

    // Validação de status
    private boolean isStatusValido(String status) {
        return status.equals("Agendada") || status.equals("Em Andamento") || status.equals("Finalizada");
    }



    // Novo método para remover feedback
    public static void criarSessaoTreinamento(Scanner scanner, ConexaoDB conexaoDB) {
        System.out.print("ID da Sessão: ");
        int id = scanner.nextInt();
        System.out.print("Data (YYYY-MM-DD): ");
        String data = scanner.next();
        System.out.print("Duração (em minutos): ");
        int duracao = scanner.nextInt();
        System.out.print("Status: ");
        String status = scanner.next();
        System.out.print("ID do treinamento: ");
        int idTreinamento = scanner.nextInt();
        System.out.print("Feedback: ");
        String feedback = scanner.next();
        System.out.print("Nota: ");
        int nota = scanner.nextInt();
        SessaoTreinamento sessao = new SessaoTreinamento(id, java.sql.Date.valueOf(data), duracao, status, idTreinamento, feedback,nota);
        conexaoDB.criarSessaoTreinamento(sessao);
        System.out.println("Sessão de treinamento criada com sucesso!");
    }

    public int getIdTreinamento() {
        return idTreinamento;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getNota() {
        return nota;
    }

    public int getId() {
        return id;
    }

    public Date getData() {return data;}

    public int getDuracao() {return duracao;}

    public String getStatus() {return status;}
    @Override
    public String toString() {
        return "SessaoTreinamento{" +
                "id=" + id +
                ", data=" + data +
                ", duracao=" + duracao +
                ", status='" + status + '\'' +
                ", idTreinamento=" + idTreinamento +
                ", feedback='" + feedback + '\'' +
                ", nota=" + nota +
                '}';
    }
}
