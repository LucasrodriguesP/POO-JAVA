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
import Model.Proprietario;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lucas
 */
public class ProprietarioDAO {
    
    public void salvar(Proprietario p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            
            
            stmt = con.prepareStatement("INSERT INTO proprietario (nome,cpf,telefone) VALUES (?,?,?)");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getTelefone());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "salvo com sucessso");
        } catch (SQLException ex) {
            Logger.getLogger(ProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closedConnection(con, stmt);
        }

        
    }
    
    public List<Proprietario> buscar(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Proprietario> list = new ArrayList<>();
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM proprietario");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Proprietario p = new Proprietario();
                
                p.setNome(rs.getString("nome"));                
                p.setCpf(rs.getString("cpf"));                
                p.setTelefone(rs.getString("telefone"));
                
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closedConnection(con, stmt, rs);
        }
        
        return list;   
    }
    
    public void alterar(Proprietario p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            
            
            stmt = con.prepareStatement("UPDATE proprietario SET nome = ?,cpf = ?,telefone = ? WHERE cpf = ?");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getTelefone());
            stmt.setString(4, p.getCpf());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucessso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao atualizar "+ ex);       
        } finally{
            ConnectionFactory.closedConnection(con, stmt);
        }

        
    }
    
    
    public void excluir(Proprietario p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            
            
            stmt = con.prepareStatement("DELETE FROM proprietario WHERE cpf = ?");
            stmt.setString(1, p.getCpf());
               
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucessso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao excluir "+ ex);       
        } finally{
            ConnectionFactory.closedConnection(con, stmt);
        }

        
    }
}
