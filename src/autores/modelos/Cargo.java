/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autores.modelos;
/**
 *
 * @author G07
 */
public enum Cargo {
    TITULAR("Titular"),
    ASOCIADO("Asociado"),
    ADJUNTO("Adjunto"),
    JTP("Jefe de Trabajos Prácticos"),
    ADG("Aux. Docente Graduado");

    private String valor; 

    private Cargo(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }

}

