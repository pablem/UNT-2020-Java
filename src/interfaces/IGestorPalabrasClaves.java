package interfaces;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import palabrasclaves.modelos.PalabraClave;


public interface IGestorPalabrasClaves {
        public static final String MENSAJE_EXITO="Operación Exitosa";
        public static final String MENSAJE_REPETIDO="La Palabra Clave ingresada ya existe";
        public static final String MENSAJE_ERROR="Error";
    
    public String nuevaPalabraClave(String nombre);
    public List<PalabraClave> verPalabrasClaves();
    public PalabraClave verPalabraClave(String nombre);
    public String borrarPalabraClave(PalabraClave palabraClave);
    public List<PalabraClave> buscarPalabrasClaves(String nombre);
    public boolean existeEstaPalabraClave(PalabraClave palabraClave);
    /***Métodos "auxiliares"***/
    public void mostrarPalabrasClaves();
}
