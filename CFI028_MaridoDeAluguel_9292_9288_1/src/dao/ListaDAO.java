/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Lista;

/**
 *
 * @author Alunos
 */
public class ListaDAO {
    //  int idLista, idPrestador, idServico;

    public String salvar(int idPrestador, int idServico) {
        String sql = "INSERT INTO lista (idPrestador, idServico) VALUES (?, ?)";
        try (Connection conn = Conexao.criarConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPrestador);
            stmt.setInt(2, idServico);
            System.out.println("Comando salvar: " + stmt.toString());
            stmt.executeUpdate();
            System.out.println("Registro de associação inserido com sucesso!");
            return "SUCESSO";

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar associação: " + e.getMessage());
            return "FRACASSO";
        }
    }

    public String atualizar(Lista l) {
        String sql = "UPDATE lista SET idPrestador = ?, idServico = ? WHERE idLista = ?";
        try (Connection conn = Conexao.criarConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, l.getIdPrestador());
            stmt.setInt(2, l.getIdServico());
            stmt.setInt(3, l.getIdLista());

            System.out.println("Comando atualizar: " + stmt.toString());
            stmt.executeUpdate();
            return "SUCESSO";

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar associação: " + e.getMessage());
            return "FRACASSO";
        }
    }

    public String excluir(int id) {
        String sql = "DELETE FROM lista WHERE idLista = ?";
        try (Connection conn = Conexao.criarConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            System.out.println("Comando excluir: " + stmt.toString());
            stmt.executeUpdate();
            return "SUCESSO";

        } catch (SQLException e) {
            System.err.println("Erro ao excluir associação: " + e.getMessage());
            return "FRACASSO";
        }
    }

    //devolve todos os valores, incluindo o de outras listas
    public ObservableList<Lista> consultarTodos() {
        ObservableList<Lista> lista = FXCollections.observableArrayList();

        String sql = "SELECT lista.idLista, lista.idPrestador, prestador.nome, prestador.telefone, prestador.pontuacao, lista.idServico, \n"
                + " servico.tipoServico, servico.valorHora  \n"
                + "FROM lista  "
                + "JOIN prestador ON lista.idPrestador = prestador.idPrestador "
                + "JOIN servico ON lista.idServico = servico.idServico;";

        try (Connection conn = Conexao.criarConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Lista l = new Lista();
                l.setIdLista(rs.getInt("idLista"));
                l.setIdPrestador(rs.getInt("idPrestador"));
                l.setNome(rs.getString("nome"));
                l.setTelefone(rs.getString("telefone"));
                l.setPontuacao(rs.getDouble("pontuacao"));
                l.setIdServico(rs.getInt("idServico"));
                l.setTipoServico(rs.getString("tipoServico"));
                l.setValorHora(rs.getString("valorHora"));

                lista.add(l);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar lista: " + e.getMessage());
        }

        return lista;
    }

}
