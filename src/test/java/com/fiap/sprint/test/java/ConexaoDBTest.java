package com.fiap.sprint.test.java;

import com.fiap.sprint.database.ConexaoDB;
import com.fiap.sprint.model.*;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConexaoDBTest {
    private ConexaoDB conexaoDB;

    @BeforeEach
    public void setUp() throws SQLException {
        // Usando H2 como banco de dados para testes
        String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"; // Cria um banco de dados em memória
        String user = "sa";
        String password = "";
        Connection connection = DriverManager.getConnection(url, user, password);
        conexaoDB = new ConexaoDB(connection); // Supondo que você tenha um construtor que aceita uma conexão
        inicializarBancoDeDados();
    }

    private void inicializarBancoDeDados() throws SQLException {
        // Limpar tabelas existentes
        Statement stmt = conexaoDB.getConnection().createStatement();
        stmt.execute("DROP TABLE IF EXISTS sessao_treinamento");
        stmt.execute("DROP TABLE IF EXISTS treinamentos");
        stmt.execute("DROP TABLE IF EXISTS administradores");
        stmt.execute("DROP TABLE IF EXISTS instrutores");
        stmt.execute("DROP TABLE IF EXISTS estudantes");
        stmt.execute("DROP TABLE IF EXISTS usuarios");
        // Criação das tabelas necessárias para os testes

        stmt.execute("CREATE TABLE usuarios (id INT PRIMARY KEY, nome VARCHAR(100), email VARCHAR(100))");
        stmt.execute("CREATE TABLE estudantes (id INT PRIMARY KEY, matricula INT, FOREIGN KEY (id) REFERENCES usuarios(id))");
        stmt.execute("CREATE TABLE instrutores (id INT PRIMARY KEY, especialidade VARCHAR(100), FOREIGN KEY (id) REFERENCES usuarios(id))");
        stmt.execute("CREATE TABLE administradores (id INT PRIMARY KEY, cargo VARCHAR(100), FOREIGN KEY (id) REFERENCES usuarios(id))");
        stmt.execute("CREATE TABLE treinamentos (id INT PRIMARY KEY, titulo VARCHAR(100), descricao VARCHAR(255), id_aluno INT, id_instrutor INT, FOREIGN KEY (id_aluno) REFERENCES estudantes(id), FOREIGN KEY (id_instrutor) REFERENCES instrutores(id))");
        stmt.execute("CREATE TABLE sessao_treinamento (id INT PRIMARY KEY, data DATE, duracao INT, status VARCHAR(50), treinamento_id INT, feedback VARCHAR(350), nota INT, FOREIGN KEY (treinamento_id) REFERENCES treinamentos(id))");
        stmt.close();
    }

    @AfterEach
    public void tearDown() {
        conexaoDB.fecharConexao();
    }

    @Test
    public void testCriarUsuario() {
        Usuario usuario = new Usuario(1, "Teste", "teste@fiap.com") {
            @Override
            public String getTipoUsuario() {
                return "Usuário";
            }
        };
        conexaoDB.criarUsuario(usuario);

        List<Usuario> usuarios = conexaoDB.getAllUsuarios();
        assertEquals(1, usuarios.size());
        assertEquals("Teste", usuarios.get(0).getNome());
    }

    @Test
    public void testCriarEstudante() {
        Usuario usuario = new Usuario(1, "Teste", "teste@fiap.com") {
            @Override
            public String getTipoUsuario() {
                return "Usuário";
            }
        };
        conexaoDB.criarUsuario(usuario);

        Estudante estudante = new Estudante(1, "12345", "teste", 12345);
        conexaoDB.criarEstudante(estudante);

        // Verifique se o estudante foi criado corretamente
        // Isso requer um método para obter estudantes
        // Exemplo: List<Estudante> estudantes = conexaoDB.getAllEstudantes();
        // assertEquals(1, estudantes.size());
    }

    @Test
    public void testEditarUsuario() {
        Usuario usuario = new Usuario(1, "Teste", "teste@fiap.com") {
            @Override
            public String getTipoUsuario() {
                return "Usuário";
            }
        };
        conexaoDB.criarUsuario(usuario);

        // Edita o usuário
        conexaoDB.editarUsuario(1, "Teste Editado", "testeeditado@fiap.com");

        List<Usuario> usuarios = conexaoDB.getAllUsuarios();
        assertEquals(1, usuarios.size());
        assertEquals("Teste Editado", usuarios.get(0).getNome());
    }

    @Test
    public void testDeletarUsuario() {
        Usuario usuario = new Usuario(1, "Teste", "teste@fiap.com") {
            @Override
            public String getTipoUsuario() {
                return "Usuário";
            }
        };
        conexaoDB.criarUsuario(usuario);

        // Deletar o usuário
        conexaoDB.deletarUsuario(1);

        List<Usuario> usuarios = conexaoDB.getAllUsuarios();
        assertEquals(0, usuarios.size());
    }

    @Test
    public void testCriarTreinamento() {
        // Criação de um usuário
        Usuario usuario = new Usuario(1, "Teste", "teste@fiap.com") {
            @Override
            public String getTipoUsuario() {
                return "Usuário";
            }
        };
        conexaoDB.criarUsuario(usuario);

        // Criação de um estudante
        Estudante estudante = new Estudante(1, "12345", "teste", 12345);
        conexaoDB.criarEstudante(estudante);

        // Criação de um instrutor
        Instrutor instrutor = new Instrutor(1, "joao", "teste", "Especialidade"); // Certifique-se de que o ID do instrutor é único
        conexaoDB.criarInstrutor(instrutor); // Método que cria um instrutor, deve ser implementado

        // Criar um treinamento
        Treinamento treinamento = new Treinamento(5, 1, 1, "Treinamento 1", "Descrição do Treinamento");
        conexaoDB.criarTreinamento(treinamento);

        // Verificar se o treinamento foi criado corretamente
        List<Treinamento> treinamentos = conexaoDB.getAllTreinamentos();
        assertEquals(1, treinamentos.size());
        assertEquals("Treinamento 1", treinamentos.get(0).getTitulo());
    }

    @Test
    public void testCriarSessaoTreinamento() {
        // Criação de um usuário
        Usuario usuario = new Usuario(1, "Teste", "teste@fiap.com") {
            @Override
            public String getTipoUsuario() {
                return "Usuário";
            }
        };
        conexaoDB.criarUsuario(usuario);

        // Criação de um estudante
        Estudante estudante = new Estudante(1, "12345", "teste", 12345);
        conexaoDB.criarEstudante(estudante);

        // Criação de um instrutor
        Instrutor instrutor = new Instrutor(1, "marcos", "1", "especialidade");
        conexaoDB.criarInstrutor(instrutor); // Certifique-se de que esse método existe

        // Criar um treinamento
        Treinamento treinamento = new Treinamento(1, 1, 1, "Treinamento 1", "Descrição do Treinamento");
        conexaoDB.criarTreinamento(treinamento);

        // Criar uma sessão de treinamento
        SessaoTreinamento sessao = new SessaoTreinamento(1, Date.valueOf("2024-01-01"), 60, "Agendada", 1, "Bom", 5);
        conexaoDB.criarSessaoTreinamento(sessao);

        List<SessaoTreinamento> sessoes = conexaoDB.getAllSessoes();
        assertEquals(1, sessoes.size());
        assertEquals("Agendada", sessoes.get(0).getStatus());
    }

}

