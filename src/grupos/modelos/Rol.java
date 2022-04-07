/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupos.modelos;
/**
 *
 * @author G07
 */
public enum Rol {
    ADMINISTRADOR("Administrador"),
    COLABORADOR("Colaborador");
    
    private String valor;

    
    private Rol(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
    
}
