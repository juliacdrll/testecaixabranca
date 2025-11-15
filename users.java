package login;
// Define a alocação da classe no pacote 'login'.

import java.sql.Connection;
// Importa a interface Connection (JDBC), para gerenciar a sessão com o banco de dados.
import java.sql.DriverManager;
// Importa a classe DriverManager, utilizada para carregar o driver JDBC e obter instâncias de Connection.
import java.sql.ResultSet;
// Importa a classe ResultSet, que armazena o conjunto de resultados de uma consulta SQL.
import java.sql.Statement;
// Importa a interface Statement, utilizada para a execução de comandos SQL estáticos.

public class User {
// Declaração da classe pública 'User', responsável por operações de autenticação e conexão com o BD.
    
    public boolean result = false;
    // Variável de instância booleana, inicializada como 'false'. Utilizada para registrar o resultado (sucesso/falha) da verificação de usuário.
// MÉTODO: conectarBD()
// Objetivo: Estabelecer a comunicação com o SGBD.
    public Connection conectarBD() {
        // Assinatura do método: retorna um objeto 'Connection'.
        Connection conn = null;
        // Inicialização do objeto 'conn' como null. Será a variável de retorno da conexão.
        try {
            // Início do bloco 'try', abrangendo as operações sujeitas a exceções (JDBC, I/O).
            Class.forName("com.mysql.jdbc.DriverManager").newInstance();
            // Carrega dinamicamente o driver JDBC do MySQL na memória, utilizando reflexão.
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "root");
            // Tentativa de estabelecimento de conexão com o SGBD, utilizando URL, usuário e senha fornecidos.
        } catch (Exception e) {
            // Bloco 'catch' genérico para tratar qualquer exceção ocorrida durante a conexão.
            System.out.println("Erro: " + e);
            // Saída de erro: registra a mensagem da exceção no console.
        }
        return conn;
        // Retorna o objeto Connection. Se houver falha no 'try', o retorno será 'null'.
    }

// MÉTODO: verificarUsuario()
// Objetivo: Autenticar o usuário contra o banco de dados.
    
    public boolean verificarUsuario(String login, String senha) {
        // Assinatura do método: recebe credenciais e retorna um booleano de autenticação.
        Connection conn = conectarBD();
        // Chamada ao método auxiliar. 'conn' recebe a conexão estabelecida (ou 'null' em caso de falha).
        try {
            // Início do bloco 'try' para as operações SQL.
            String sql = "";
            // Inicialização da variável local 'sql' (string da consulta).
            // INSTRUÇÃO SQL (Comentário do código original)
            sql += "select * from usuarios ";
            // Constrói a primeira parte da query (seleção de todas as colunas).
            sql += "where login = '" + login + "' ";
            // Adiciona a condição de filtro por login.
            // FRAGILIDADE: SQL INJECTION. O código concatena strings, permitindo a inserção de código malicioso.
            sql += "and senha = '" + senha + "'";
            // Adiciona a condição de filtro por senha.
            
            Statement st = conn.createStatement();
            // Cria um objeto Statement. 
            // FRAGILIDADE: PONTO DE FALHA (NullPointerException). Se 'conn' é null, o sistema falha aqui.
            ResultSet rs = st.executeQuery(sql);
            // Executa a consulta e armazena o resultado em 'rs'.
            
            if (rs.next()) {
                // Nó Predicado (Decisão): Se 'rs.next()' for verdadeiro, há um registro correspondente (usuário autenticado).
                String nome = rs.getString("nome");
                // Recupera o valor da coluna 'nome' do ResultSet (dado não utilizado posteriormente no método).
                result = true;
                // Atualiza o estado da variável de instância, indicando sucesso na autenticação.
            }
            // FRAGILIDADE: VAZAMENTO DE RECURSOS. Os recursos 'rs', 'st' e 'conn' não são fechados.
        } catch (Exception e) {
            // Bloco 'catch' genérico.
            // O bloco está vazio: Exceções são 'engolidas', impedindo a detecção e o registro do erro (Silent Failure).
        }
        return result;
        // Retorna o resultado final da verificação.
    }
}
// Fim da classe.