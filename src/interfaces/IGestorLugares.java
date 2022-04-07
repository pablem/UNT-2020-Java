package interfaces;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lugares.modelos.Lugar;

public interface IGestorLugares {
    public static final String MENSAJE_EXITO="Operación Exitosa";
    public static final String MENSAJE_REPETIDO="El Lugar ingresado ya existe";
    public static final String MENSAJE_ERROR="Error";
            
    public String nuevoLugar(String nombre);
    public List<Lugar> verLugares();
    public Lugar verLugar(String nombre);
    public String borrarLugar(Lugar lugar);
    public List<Lugar> buscarLugares(String nombre);
    public boolean existeEsteLugar(Lugar lugar);
    /***Métodos "auxiliares"***/
    public void mostrarLugares();
   
}
