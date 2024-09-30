package com.fiap.sprint.model;

public class SistemaVR {
    private int id;
    private String nome;
    private String versao;

    public SistemaVR(int id, String nome, String versao) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do sistema não pode ser nulo ou vazio.");
        }
        if (versao == null || versao.trim().isEmpty()) {
            throw new IllegalArgumentException("A versão do sistema não pode ser nula ou vazia.");
        }

        this.id = id;
        this.nome = nome;
        this.versao = versao;
    }



    // Método para atualizar a versão do sistema
    public void atualizarVersao(String novaVersao) {
        if (novaVersao == null || novaVersao.trim().isEmpty()) {
            throw new IllegalArgumentException("A nova versão não pode ser nula ou vazia.");
        }
        this.versao = novaVersao;
        System.out.println("Versão do sistema atualizada para: " + novaVersao);
    }
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getVersao() {
        return versao;
    }
    @Override
    public String toString() {
        return "SistemaVR{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", versao='" + versao + '\'' +
                '}';
    }
}
