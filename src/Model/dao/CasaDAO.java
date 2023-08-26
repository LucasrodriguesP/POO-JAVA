package Model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Model.Casa;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lucas
 */
public class CasaDAO {

    public void salvar(Casa c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement("INSERT INTO casa (preco,m2,quartos,banheiros,garagem,elevador,mobilia,suites,andares,condominio,regiao,endereco,iptu,proprietario,vagas,quintal,piscina) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setFloat(1, c.getPreco());
            stmt.setInt(2, c.getM2());
            stmt.setInt(3, c.getQuartos());
            stmt.setInt(4, c.getBanheiros());
            stmt.setBoolean(5, c.getGaragem());
            stmt.setBoolean(6, c.getElevador());
            stmt.setBoolean(7, c.getMobilia());
            stmt.setInt(8, c.getSuites());
            stmt.setInt(9, c.getAndares());
            stmt.setString(10, c.getCondominio());
            stmt.setString(11, c.getRegiao());
            stmt.setString(12, c.getEndereço());
            stmt.setFloat(13, c.getIptu());
            stmt.setString(14, c.getPropietario());
            stmt.setInt(15, c.getVagas());
            stmt.setBoolean(16, c.getQuintal());
            stmt.setBoolean(17, c.getPiscina());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "salvo com sucessso");
        } catch (SQLException ex) {
            Logger.getLogger(CasaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closedConnection(con, stmt);
        }

    }

    public List<Casa> buscar() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Casa> list = new ArrayList<>();

        try {

            stmt = con.prepareStatement("SELECT * FROM casa");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Casa casa = new Casa();

                casa.setPreco(rs.getFloat("preco"));
                casa.setM2(rs.getInt("m2"));
                casa.setIptu(rs.getFloat("iptu"));
                casa.setQuartos(rs.getInt("quartos"));
                casa.setBanheiros(rs.getInt("banheiros"));
                casa.setSuites(rs.getInt("suites"));
                casa.setAndares(rs.getInt("andares"));
                casa.setCondominio(rs.getString("condominio"));
                casa.setRegiao(rs.getString("regiao"));
                casa.setEndereço(rs.getString("endereco"));
                casa.setVagas(rs.getInt("vagas"));
                casa.setPiscina(rs.getBoolean("piscina"));
                casa.setPropietario(rs.getString("proprietario"));
                casa.setGaragem(rs.getBoolean("garagem"));
                casa.setMobilia(rs.getBoolean("mobilia"));
                casa.setQuintal(rs.getBoolean("quintal"));
                casa.setElevador(rs.getBoolean("elevador"));
                casa.setId(rs.getInt("id"));

                list.add(casa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CasaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closedConnection(con, stmt, rs);
        }

        return list;
    }

    public void alterar(Casa c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement("UPDATE casa SET preco = ?, m2 =? , quartos = ?,banheiros=?,garagem=?,elevador=?,mobilia=?,suites=?,andares=?,"
                    + "condominio=?,regiao=?,endereco=?,iptu=?,proprietario=?,vagas=?,quintal=?, piscina = ? WHERE ID=?");
            
            stmt.setFloat(1, c.getPreco());
            stmt.setInt(2, c.getM2());
            stmt.setInt(3, c.getQuartos());
            stmt.setInt(4, c.getBanheiros());
            stmt.setBoolean(5, c.getGaragem());
            stmt.setBoolean(6, c.getElevador());
            stmt.setBoolean(7, c.getMobilia());
            stmt.setInt(8, c.getSuites());
            stmt.setInt(9, c.getAndares());
            stmt.setString(10, c.getCondominio());
            stmt.setString(11, c.getRegiao());
            stmt.setString(12, c.getEndereço());
            stmt.setFloat(13, c.getIptu());
            stmt.setString(14, c.getPropietario());
            stmt.setInt(15, c.getVagas());
            stmt.setBoolean(16, c.getQuintal());
            stmt.setBoolean(17, c.getPiscina());
            stmt.setInt(18, c.getId());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucessso");
        } catch (SQLException ex) {
            Logger.getLogger(CasaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closedConnection(con, stmt);
        }

    }

    public void excluir(Casa c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement("DELETE FROM casa WHERE id = ?");
            stmt.setInt(1, c.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucessso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao excluir " + ex);
        } finally {
            ConnectionFactory.closedConnection(con, stmt);
        }

    }
}
