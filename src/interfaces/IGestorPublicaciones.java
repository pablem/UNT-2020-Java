package interfaces;
import autores.modelos.Autor;
import grupos.modelos.MiembroEnGrupo;
import idiomas.modelos.Idioma;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lugares.modelos.Lugar;
import palabrasclaves.modelos.PalabraClave;
import publicaciones.modelos.Publicacion;
import tipos.modelos.Tipo;

public interface IGestorPublicaciones {
    public static final String MENSAJE_EXITO="Se ha creado la nueva Publicacion";
    public static final String MENSAJE_REPETIDO="La Publicacion ingresada ya existe";
    public static final String MENSAJE_ERROR="Error";    

    public static final String MENSAJE_MODIFICACION_EXITO="Se modifico con exito la Publicaion";
    public static final String MENSAJE_MODIFICACION_BLANCO="¡El cambio ingesado esta en blanco!";
    public static final String MENSAJE_MODIFICACION_SINPUBLICACION="¡La Publicacion ingresada no existe!";
    public static final String MENSAJE_MODIFICACION_ERROR="Error al modificar el Autor";
    
    public static final String MENSAJE_BORRADO_EXITO="Se ha borrado la publicación";

    public String nuevaPublicacion(String titulo, MiembroEnGrupo miembroEnGrupo,
                                   LocalDate fechaPublicacion, Tipo tipo, 
                                   Idioma idioma, Lugar lugar, List<PalabraClave> palabrasClaves,
                                   String enlace, String resumen);
    public String modificarPublicacion(Publicacion publicacion, MiembroEnGrupo miembroEnGrupo,
                                       LocalDate fechaPublicacion, Tipo tipo, Idioma idioma,
                                       Lugar lugar, List<PalabraClave> palabrasClaves,
                                       String enlace, String resumen);
    public boolean hayPublicacionesConEstaPalabraClave(PalabraClave palabraClave);
    public boolean hayPublicacionesConEsteLugar(Lugar lugar);
    public boolean hayPublicacionesConEsteIdioma(Idioma idioma);
    public boolean hayPublicacionesConEsteTipo(Tipo tipo);
    public boolean hayPublicacionesConEsteAutor(Autor autor);
    public boolean existeEstaPublicacion(Publicacion publicacion);
    public Publicacion verPublicaion(String titulo);
    public String borrarPublicacion(Publicacion publicacion);
    public List<Publicacion> buscarPublicaciones(String titulo);
    public List<Publicacion> verPublicaciones();
     /***Métodos "auxiliares"***/
    public void mostrarPublicaciones();
}
