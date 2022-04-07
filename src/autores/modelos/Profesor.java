package autores.modelos;

public class Profesor extends Autor{
    
//    private int dni;
//    private String apellidos;
//    private String nombres;
//    private String clave;
//    private String cargo; 
//    private ArrayList<MiembroEnGrupo> miembros; 
      private Cargo cargo;
      
    public Profesor(int dni, String apellidos, String nombres, String clave, Cargo cargo) {
        super(dni, apellidos, nombres, clave);
        this.cargo = cargo;
    }
    
    public Cargo verCargo() {
        return cargo;
    }
    public void asignarCargo(Cargo cargo) {
        this.cargo = cargo;
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
        System.out.printf(this.cargo+": "); 
        super.mostrar();  
    }
    
}