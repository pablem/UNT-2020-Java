package interfaces;
import autores.modelos.Alumno;
import autores.modelos.Autor;
import autores.modelos.Cargo;
import autores.modelos.Profesor;
import grupos.modelos.Grupo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface IGestorAutores {
        public static final String MENSAJE_EXITO="Operación Exitosa";
        public static final String MENSAJE_REPETIDO="El Autor ingresado ya existe";
        public static final String MENSAJE_ERROR="Error";
        
        public static final String MENSAJE_MODIFICACION_EXITO="Se modifico con exito al Autor";
        public static final String MENSAJE_MODIFICACION_BLANCO="¡El cambio ingesado esta en blanco!";
        public static final String MENSAJE_MODIFICACION_SINAUTOR="¡El Autor ingresado no existe!";
        public static final String MENSAJE_MODIFICACION_ERROR="Error al modificar el Autor";
        public static final String MESAJE_MODIFICAION_SINPROFESOR="El autor ingresado es un Alumno";
        public static final String MESAJE_MODIFICAION_SINALUMNO="El autor ingresado es un Profesor";

    public String nuevoAutor(int dni, String apellidos, String nombres, Cargo cargo,String clave,String claveRepetida);
    public String nuevoAutor(int dni, String apellidos, String nombres,String cx, String clave, String claveRepetida);
    public String modificarAutor(Autor autor, String apellidos, String nombres, Cargo cargo, String clave, String claveRepetida);
    public String modificarAutor(Autor autor, String apellidos, String nombres, String cx, String clave, String claveRepetida);
    public boolean existeEsteAutor(Autor autor);
    public List<Autor> verAutores();
//    public List<Autor> verAutoresSinOrdenar();
    public List<Profesor> verProfesores();
//    public List<Profesor> verProfesoresSinOrdenar();
    public List<Alumno> verAlumnos();
//    public List<Alumno> verAlumnosSinOrdenar();
    public Autor verAutor(int dni);
    public String borrarAutor(Autor autor);
    public List<Profesor> buscarProfesores(String apellidos);
    public List<Alumno> buscarAlumnos(String apellidos);
    public boolean hayAutoresConEsteGrupo(Grupo grupo);
    /***Métodos "auxiliares"***/
    public void mostrarAutores();
    public void mostrarProfesores();
    public void mostrarAlumnos();
//    public List<Autor> ordenarAutores(Comparator<Autor> autorComparador , List<Autor> autoresAOrdenar);
//    public List<Profesor> ordenarProfesores(Comparator<Profesor> autorComparador , List<Profesor> profesoresAOrdenar);
//    public List<Alumno> ordenarAlumnos(Comparator<Alumno> alumnoComparador , List<Alumno> alumnosAOrdenar);

}
