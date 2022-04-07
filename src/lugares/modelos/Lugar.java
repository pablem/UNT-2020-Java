/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lugares.modelos;

import java.util.Objects;

/**
 *
 * @author G07
 */
public class Lugar {
    
    private String nombre;

    public Lugar(String nombre) {
        this.nombre = nombre;
    }

    public void asignarNombre(String nombre) {
        this.nombre = nombre;
    }
        public String verNombre() {
        return nombre;
    }

    public void mostrar() {     //ver que ocurre si es necesario o no
       System.out.println(this.nombre);} 
    
    
    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lugar other = (Lugar) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    
}
