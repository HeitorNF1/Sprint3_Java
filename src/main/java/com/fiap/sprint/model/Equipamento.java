package com.fiap.sprint.model;

import com.fiap.sprint.database.ConexaoDB;
import com.fiap.sprint.model.StatusEquipamento;

import java.util.Scanner;

public class Equipamento {
    private int id;
    private String tipo;
    private StatusEquipamento status; // Usando enum para garantir status válidos

    public Equipamento(int id, String tipo, StatusEquipamento status) {
        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("O tipo de equipamento não pode ser nulo ou vazio.");
        }
        this.id = id;
        this.tipo = tipo;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public StatusEquipamento getStatus() {
        return status;
    }

    // Método para alterar o status do equipamento
    public void setStatus(StatusEquipamento novoStatus) {
        this.status = novoStatus;
    }
    public static void criarEquipamento(Scanner scanner, ConexaoDB conexaoDB) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();
        System.out.print("Status (ex: ATIVO, INATIVO): "); // Altere conforme os valores do seu enum
        String statusInput = scanner.nextLine();

        // Converte a String para o enum StatusEquipamento
        StatusEquipamento status = StatusEquipamento.valueOf(statusInput.toUpperCase());

        Equipamento equipamento = new Equipamento(id, tipo, status);
        conexaoDB.criarEquipamento(equipamento);
        System.out.println("Equipamento criado com sucesso!");
    }

    @Override
    public String toString() {
        return "Equipamento{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", status=" + status +
                '}';
    }
}