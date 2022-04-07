package interfaces;
import grupos.modelos.Grupo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface IGestorGrupos {
        public static final String MENSAJE_EXITO="Operación Exitosa";
        public static final String MENSAJE_REPETIDO="El Grupo ingresado ya existe";
        public static final String MENSAJE_ERROR="Error";
        
        public static final String MENSAJE_MODIFICACION_EXITO="Se modifico con exito la descripción";
        public static final String MENSAJE_MODIFICACION_BLANCO="¡La descripcion ingesada está en blanco!";
        public static final String MENSAJE_MODIFICACION_SINGRUPO="¡El Grupo ingresado no existe!";
        public static final String MENSAJE_MODIFICACION_ERROR="Error al modificar la descripcion";
        
    public String nuevoGrupo(String noombre, String descripcion);
    public String modificarGrupo(Grupo grupo, String descripcion);
    public List<Grupo> verGrupos();
    public Grupo verGrupo(String nombre);
    public boolean existeEsteGrupo(Grupo grupo);
    public String borrarGrupo(Grupo grupo);
    public List<Grupo> buscarGrupos(String nombre);
    /***Métodos "auxiliares"***/
    public void mostrarGrupos();
}
