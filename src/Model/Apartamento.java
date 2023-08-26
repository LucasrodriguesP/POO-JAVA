/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lucas
 */
public class Apartamento extends Imovel {
    Boolean varanda;
    Integer andar;

    public Boolean getVaranda() {
        return varanda;
    }

    public void setVaranda(Boolean varanda) {
        this.varanda = varanda;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getAndar() {
        return andar;
    }

    public void setAndar(Integer andar) {
        this.andar = andar;
    }
    
    
}
