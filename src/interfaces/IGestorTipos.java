package interfaces;
import java.util.Comparator;
import java.util.List;
import tipos.modelos.Tipo;


public interface IGestorTipos {
    public static final String MENSAJE_EXITO="Operación Exitosa";
    public static final String MENSAJE_REPETIDO="El Tipo ingresado existe";
    public static final String MENSAJE_ERROR="Error";

public String nuevoTipo(String nombre); 
public List<Tipo> verTipos();
public Tipo verTipo(String nombre);
public String borrarTipo(Tipo tipo);
public List<Tipo> buscarTipos(String nombre);
public boolean existeEsteTipo(Tipo tipo);
/***Métodos "auxiliares"***/
//  public List<Tipo> ordenarTipos(Comparator<Tipo> tipoComparador , List<Tipo> tiposAOrdenar);
    public void mostrarTipos();
    public List<Tipo> verTiposSinOrdenar();
}
