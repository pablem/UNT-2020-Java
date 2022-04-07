/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupos.modelos;

import autores.modelos.Autor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 *
 * @author G07
 */
public class Grupo {
    
    private String nombre;
    private String descripcion;
    private List<MiembroEnGrupo> miembros;


    public Grupo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.miembros = new ArrayList<>();
    }

    public String verDescripcion() {
        return descripcion;
    }

    public void asignarDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String verNombre() {
        return nombre;
    }

    public void asignarNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public List<MiembroEnGrupo> verMiembros() {
        return miembros;
    }    
    
    public void agregarMiembro(Autor autor, Rol rol) {
        MiembroEnGrupo nuevo = new MiembroEnGrupo(autor, this, rol);
        if (this.esSuperAdministradores())
            nuevo.asignarRol(Rol.ADMINISTRADOR);
        if (!this.miembros.contains(nuevo)){
            this.miembros.add(nuevo);
            autor.agregarGrupo(this, rol);
        }
    }
    
    public void quitarMiembro(Autor autor) {
        MiembroEnGrupo meg = new MiembroEnGrupo(autor, this, null);
        if (this.miembros.contains(meg)){
            this.miembros.remove(meg);
            autor.quitarGrupo(this);
        }
    }
    
    public boolean esSuperAdministradores() {
        if(this.equals(new Grupo("Super Administradores", null)))
            return true;
        return false;
    }
    
    public boolean tieneMiembros() {
        if(this.miembros.isEmpty())
            return false;
        return true;
    }
    
    public void mostrar() {
         System.out.println(this.nombre+", "+this.descripcion);
         if(this.tieneMiembros()) {
            System.out.println(" miembro/s : "); 
            for(MiembroEnGrupo m : this.miembros)
                 System.out.println("\t\t"+m.verAutor().verNombres()+" - "+m.verRol());
         }
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nombre);
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
        final Grupo other = (Grupo) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
}