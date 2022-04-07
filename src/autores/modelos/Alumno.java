package autores.modelos;

import java.util.Objects;

public class Alumno extends Autor {
    
//    private int dni;
//    private String apellidos;
//    private String nombres;
//    private String clave;
    private String cx;

    public Alumno(int dni, String apellidos, String nombres, String clave, String cx) {
        super(dni, apellidos, nombres, clave);
//        this.dni = dni;
//        this.apellidos = apellidos;
//        this.nombres = nombres;
//        this.clave = clave;
        this.cx = cx;
    }

    public String verCx() {
        return cx;
    }

    public void asignarCx(String cx) {
        this.cx = cx;
    }
    
    public int verDni() {
    return super.verDni();
    }
    public void asignarDni(int dni) {
        super.asignarDni(dni);
    }
    public String verApellidos() {
        return super.verApellidos();
    }
    public void asignarApellidos(String apellidos) {
        super.asignarApellidos(apellidos);
    }
    public String verNombres() {
        return super.verNombres();
    }
    public void asignarNombres(String nombres) {
        super.asignarNombres(nombres);
    }

    @Override
    public void mostrar() {
        System.out.print( cx + ": ");        
        super.mostrar();
    }

   @Override
    public int hashCode() {
        return super.hashCode() + Objects.hashCode(this.cx);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            if (obj instanceof Alumno) {
                return Objects.equals(this.cx, ((Alumno)obj).verCx());
            }
            else {
                return false;
            }
        }
        return true;
    }       
       
}
