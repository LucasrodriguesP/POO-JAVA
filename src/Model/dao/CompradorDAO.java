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
import Model.Comprador;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lucas
 */
public class CompradorDAO {
    
    public void salvar(Comprador c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            
            
            stmt = con.prepareStatement("INSERT INTO comprador (nome,cpf,telefone,regiao,valor) VALUES (?,?,?,?,?)");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getRegiao());
            stmt.setFloat(5, c.getValor());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "salvo com sucessso");
        } catch (SQLException ex) {
            Logger.getLogger(CompradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closedConnection(con, stmt);
        }

        
    }
    
    public List<Comprador> buscar(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Comprador> list = new ArrayList<>();
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM comprador");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Comprador c = new Comprador();
                
                c.setNome(rs.getString("nome"));                
                c.setCpf(rs.getString("cpf"));                
                c.setTelefone(rs.getString("telefone"));
                c.setRegiao(rs.getString("regiao"));
                c.setValor(rs.getFloat("valor"));
                
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closedConnection(con, stmt, rs);
        }
        
        return list;   
    }
    
    public void alterar(Comprador c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            
            
            stmt = con.prepareStatement("UPDATE comprador SET nome = ?,cpf = ?,telefone = ?,regiao = ?, valor = ? WHERE cpf = ?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getRegiao());
            stmt.setFloat(5, c.getValor());
            stmt.setString(6, c.getCpf());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucessso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao atualizar "+ ex);       
        } finally{
            ConnectionFactory.closedConnection(con, stmt);
        }

        
    }
    
    
    public void excluir(Comprador c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            
            
            stmt = con.prepareStatement("DELETE FROM comprador WHERE cpf = ?");
            stmt.setString(1, c.getCpf());
               
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucessso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao excluir "+ ex);       
        } finally{
            ConnectionFactory.closedConnection(con, stmt);
        }

        
    }
}
