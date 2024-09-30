package com.fiap.sprint.database;

import com.fiap.sprint.model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConexaoDB {
    private Connection connection;

    public ConexaoDB() {
        try {
            String url = "jdbc:mysql://localhost:3306/sprint3";
            String user = "root";
            String password = "admin";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    // Construtor que aceita uma conexão
    public ConexaoDB(Connection connection) {
        this.connection = connection;
    }

    public void criarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (id, nome, email) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, usuario.getId());
            pstmt.setString(2, usuario.getNome());
            pstmt.setString(3, usuario.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar usuário: " + e.getMessage());
        }
    }

    public void criarEstudante(Estudante estudante) {
        String sql = "INSERT INTO estudantes (id, matricula) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, estudante.getId());
            pstmt.setInt(2, estudante.getMatricula());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar estudante: " + e.getMessage());
        }
    }

    public void criarInstrutor(Instrutor instrutor) {
        String sql = "INSERT INTO instrutores (id,especialidade) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, instrutor.getId());
            pstmt.setString(2, instrutor.getEspecialidade());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar instrutor: " + e.getMessage());
        }
    }

    public void criarAdministrador(Administrador administrador) {
        String sql = "INSERT INTO administradores (id,cargo) VALUES (?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, administrador.getId());
            pstmt.setString(2, administrador.getCargo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar administrador: " + e.getMessage());
        }
    }

    public void criarTreinamento(Treinamento treinamento) {
        String sql = "INSERT INTO treinamentos (id, titulo, descricao, id_aluno, id_instrutor) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, treinamento.getId());
            pstmt.setString(2, treinamento.getTitulo());
            pstmt.setString(3, treinamento.getDescricao());
            pstmt.setInt(4, treinamento.getIdAluno());
            pstmt.setInt(5, treinamento.getIdInstrutor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar treinamento: " + e.getMessage());
        }
    }

    public void criarSessaoTreinamento(SessaoTreinamento sessao) {
        String sql = "INSERT INTO sessao_treinamento (id, data, duracao, status,treinamento_id,feedback,nota) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, sessao.getId());
            pstmt.setDate(2, new java.sql.Date(sessao.getData().getTime()));
            pstmt.setInt(3, sessao.getDuracao());
            pstmt.setString(4, sessao.getStatus());
            pstmt.setInt(5, sessao.getIdTreinamento());
            pstmt.setString(6, sessao.getFeedback());
            pstmt.setInt(7, sessao.getNota());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar sessão de treinamento: " + e.getMessage());
        }
    }

    public void criarEquipamento(Equipamento equipamento) {
        String sql = "INSERT INTO equipamentos (id, tipo, status) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, equipamento.getId());
            pstmt.setString(2, equipamento.getTipo());
            pstmt.setString(3, equipamento.getStatus().name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar equipamento: " + e.getMessage());
        }
    }



    public void editarUsuario(int id, String nome, String email) {
        String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao editar usuário: " + e.getMessage());
        }
    }

    public void deletarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                usuarios.add(new Usuario(id, nome, email) {
                    @Override
                    public String getTipoUsuario() {
                        return "Usuário";
                    }
                });
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter usuários: " + e.getMessage());
        }
        return usuarios;
    }
    // Feche a conexão ao final do uso
    public void fecharConexao() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão fechada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
    // Método para listar todos os treinamentos
    public List<Treinamento> getAllTreinamentos() {
        List<Treinamento> treinamentos = new ArrayList<>();
        String sql = "SELECT * FROM treinamentos";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                int idAluno = rs.getInt("id_aluno");
                int idInstrutor = rs.getInt("id_instrutor");
                treinamentos.add(new Treinamento(id, idAluno,idInstrutor,titulo, descricao));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter treinamentos: " + e.getMessage());
        }
        return treinamentos;
    }
    // Método para listar todas as sessões de treinamento
    public List<SessaoTreinamento> getAllSessoes() {
        List<SessaoTreinamento> sessoes = new ArrayList<>();
        String sql = "SELECT * FROM sessao_treinamento";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Date data = rs.getDate("data");
                int duracao = rs.getInt("duracao");
                String status = rs.getString("status");
                int idTreinamento = rs.getInt("treinamento_id");
                String feedback = rs.getString("feedback");
                int nota = rs.getInt("nota");
                sessoes.add(new SessaoTreinamento(id, data, duracao, status, idTreinamento, feedback, nota));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter sessões de treinamento: " + e.getMessage());
        }
        return sessoes;
    }
    public Connection getConnection() {
        return connection;
    }
}


