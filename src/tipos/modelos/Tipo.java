package tipos.modelos;
import java.util.Objects;

public class Tipo {

    private String nombre;

    public Tipo(String nombre) {
        this.nombre = nombre;
    }
    
    public void mostrar() {     
       System.out.println(this.nombre);
    } 

    public void asignarNombre(String nombre) {
        this.nombre = nombre;
    }
    public String verNombre() {
        return this.nombre;
    }    
    
    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nombre);
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
        final Tipo other = (Tipo) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    
    
}
