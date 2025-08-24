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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Prestador;
import model.Servico;

/**
 *
 * @author Alunos
 */
public class PrestadorDAO {

    //Prestador(int idPrestador, String nome, String endereco, String telefone, Double pontuacao) {
    public boolean salvar(Prestador prestador) {
        String sql = "INSERT INTO prestador (Nome, Endereco, Telefone, Pontuacao) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.criarConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(2, prestador.getNome());
            stmt.setString(3, prestador.getEndereco());
            stmt.setString(4, prestador.getTelefone());
            stmt.setDouble(5, prestador.getPontuacao());

            System.out.println(stmt.toString());

            stmt.executeUpdate();
            System.out.println("Prestador cadastrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar Prestador: " + e.getMessage());
            return false;
        }
    }

    //Prestador(int idPrestador, String nome, String endereco, String telefone, Double pontuacao) {
    public boolean atualizar(Prestador prestador) {
        String sql = "UPDATE prestador SET Nome=?,Endereco=?,Telefone=? ,"
                + " Pontuacao=? WHERE idPrestador = ?";

        try (Connection conexao = Conexao.criarConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, prestador.getNome());
            ps.setString(2, prestador.getEndereco());
            ps.setString(3, prestador.getTelefone());
            ps.setDouble(4, prestador.getPontuacao());
            ps.setInt(5, prestador.getIdPrestador());

            System.out.println("Comando atualizar  " + ps.toString());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar dados do prestador. Metodo ATUALIZAR");
            return false;
        }
    }

    public String excluir(int id) {
        System.out.println("id " + id);
        String sql = "DELETE FROM prestador WHERE idPrestador= ?";
        try (Connection conexao = Conexao.criarConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            System.out.println("Comando de apagar  " + ps.toString());
            ps.executeUpdate();
            System.out.println("Registro excluido com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir dados. Metodo Excluir");
        }
        return ("Sucesso");
    }

    public ObservableList<Prestador> consultarTodos() {
        String sql = "SELECT * from prestador";
        ObservableList<Prestador> listaPrestador = FXCollections.observableArrayList();
        try (Connection conexao = Conexao.criarConexao();
                PreparedStatement ps = conexao.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                int idPrestador = rs.getInt("idPrestador");
                String nome = rs.getString("Nome");
                String endereco = rs.getString("Endereco");
                String telefone = rs.getString("Telefone");
                Double pontuacao = rs.getDouble("Pontuacao");

                //Prestador(int idPrestador, String nome, String endereco, String telefone, Double pontuacao) {
                Prestador p = new Prestador(idPrestador, nome, endereco, telefone, pontuacao);
                // System.out.println(v.toString());
                listaPrestador.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar banco de dados Prestador. Metodo ConsultarTodos");
            return null;
        }
        return listaPrestador;
    }
}
