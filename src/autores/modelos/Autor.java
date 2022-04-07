/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autores.modelos;
import java.util.ArrayList;
import grupos.modelos.MiembroEnGrupo;
import grupos.modelos.Grupo;
import grupos.modelos.Rol;
import java.util.List;
/**
 *
 * @author G07
 */
public abstract class Autor {
    
    private int dni;
    private String apellidos;
    private String nombres;
    private String clave;
    private List<MiembroEnGrupo> miembros; 

    public Autor(int dni, String apellidos, String nombres, String clave) {
        this.dni = dni;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.clave = clave;
        this.miembros = new ArrayList<>();
    }

    public int verDni() {
        return dni;
    }
    public void asignarDni(int dni) {
        this.dni = dni;
    }
    public String verApellidos() {
        return apellidos;
    }
    public void asignarApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String verNombres() {
        return nombres;
    }
    public void asignarNombres(String nombres) {
        this.nombres = nombres;
    }
    public String verClave() {
        return clave;
    }
    public void asignarClave(String clave) {
        this.clave = clave;
    }
    public List<MiembroEnGrupo> verGrupos() {
        return miembros;
    }
    public void agregarGrupo(Grupo grupo, Rol rol) {
        MiembroEnGrupo nuevo = new MiembroEnGrupo(this, grupo, rol);
        if(!this.miembros.contains(nuevo)) {
            this.miembros.add(nuevo);
            grupo.agregarMiembro(this, rol);
        } 
    }
    public void quitarGrupo(Grupo grupo) {
        MiembroEnGrupo meg = new MiembroEnGrupo(this, grupo, null);
        if (this.miembros.contains(meg)){
            this.miembros.remove(meg);      
            grupo.quitarMiembro(this);
        }
    }
    public boolean esSuperAdministrador() {
        for(MiembroEnGrupo m : this.miembros) {
            if(m.verGrupo().esSuperAdministradores())
                return true;
        }
        return false;   
    }
    public void mostrar() {
        System.out.println("[" + this.dni + "] " + this.apellidos + ", " + this.nombres);
            if(!this.miembros.isEmpty()){
                System.out.println(" es miembro de: ");
                    for(MiembroEnGrupo m : this.miembros)
                        System.out.println("\t\t"+m.verGrupo().verNombre()+" - Rol: "+m.verRol());
            }
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.dni;
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
        if (getClass().getSuperclass() != obj.getClass().getSuperclass()) {
            return false;
        }
        final Autor other = (Autor) obj;
        return this.dni == other.dni;
       
    }
    
}