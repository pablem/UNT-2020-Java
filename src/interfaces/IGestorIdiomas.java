package interfaces;
import idiomas.modelos.Idioma;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface IGestorIdiomas {
        public static final String MENSAJE_EXITO="Operación Exitosa";
        public static final String MENSAJE_REPETIDO="El Idioma ingresado ya existe";
        public static final String MENSAJE_ERROR="Error";    
        
    public String nuevoIdioma(String noombre);
    public List<Idioma> verIdiomas();
    public Idioma verIdioma(String nombre);
    public String borrarIdioma(Idioma idioma);
    public List<Idioma> buscarIdiomas(String nombre);
    public boolean existeEsteIdioma(Idioma idioma);
/***Métodos "auxiliares"***/
    public void mostrarIdiomas();
}
