package publicaciones.modelos;
import autores.modelos.Alumno;
import autores.modelos.Autor;
import autores.modelos.Profesor;
import grupos.modelos.MiembroEnGrupo;
import idiomas.modelos.Idioma;
import interfaces.IGestorPublicaciones;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lugares.modelos.Lugar;
import palabrasclaves.modelos.PalabraClave;
import tipos.modelos.Tipo;



public class GestorPublicaciones implements IGestorPublicaciones{
private List<Publicacion> publicaciones= new ArrayList<>();

    //Se implementa el patrón Singleton para crear un solo gestor autores:
    
    public static GestorPublicaciones instancia; // variable de clase del tipo GestorAutores, instancia valor predeterminado es null
    private GestorPublicaciones(){               // para evitar que el operador new instanciado ejecute el constructor
    }
    public static GestorPublicaciones crear(){
            if(instancia==null){
            instancia = new GestorPublicaciones();
            }
           return instancia;
    }
    
    //Se crea el comparador con el que se ordenan listas por nombre:
    Comparator<Publicacion> publicacionComparador=(Publicacion p1, Publicacion p2) -> p1.verTitulo().compareTo(p2.verTitulo());
    
    @Override
    public String nuevaPublicacion(String titulo, MiembroEnGrupo miembroEnGrupo, LocalDate fechaPublicacion, Tipo tipo,
                                   Idioma idioma, Lugar lugar, List<PalabraClave> palabrasClaves, String enlace,
                                   String resumen) {
        if(    (titulo != null) && (!titulo.isBlank()) 
            && (miembroEnGrupo != null) 
            && (miembroEnGrupo.verAutor() != null) && (miembroEnGrupo.verAutor().verDni() != 0) 
            && (miembroEnGrupo.verAutor().verApellidos() != null) && (!miembroEnGrupo.verAutor().verApellidos().isBlank())
            && (miembroEnGrupo.verAutor().verNombres() !=null) && (!miembroEnGrupo.verAutor().verNombres().isBlank())
            && (miembroEnGrupo.verGrupo() != null)
            && (miembroEnGrupo.verGrupo().verNombre() != null) && (!miembroEnGrupo.verGrupo().verNombre().isBlank())
            && (miembroEnGrupo.verGrupo().verDescripcion() != null) && (!miembroEnGrupo.verGrupo().verDescripcion().isBlank())
            && (miembroEnGrupo.verRol() != null) 
            && (fechaPublicacion !=null) 
            && (tipo != null) && (tipo.verNombre() != null) && (!tipo.verNombre().isBlank())
            && (idioma != null) && (idioma.verNombre() != null) && (!idioma.verNombre().isBlank())
            && (lugar != null) && (lugar.verNombre() != null) && (!lugar.verNombre().isBlank())
            && (palabrasClaves != null) && (!palabrasClaves.isEmpty()) 
            && (enlace != null) && (!enlace.isBlank())
            && (resumen != null) && (!resumen.isBlank())
              ){
                    Publicacion nuevaPublicacion = new Publicacion(titulo, miembroEnGrupo, fechaPublicacion,tipo, idioma, lugar, palabrasClaves,enlace,resumen);
                    if(!publicaciones.contains(nuevaPublicacion)){
                        this.publicaciones.add(nuevaPublicacion);
                        return MENSAJE_EXITO;
                        }
                    else{return MENSAJE_REPETIDO;}
        }
        else{return MENSAJE_ERROR;}
    }

    @Override
    public String modificarPublicacion(Publicacion publicacion, MiembroEnGrupo miembroEnGrupo,
                                       LocalDate fechaPublicacion, Tipo tipo, Idioma idioma, 
                                       Lugar lugar, List<PalabraClave> palabrasClaves, String enlace, String resumen) {
       if(publicaciones.contains(publicacion)){
                if(     (miembroEnGrupo != null) 
                    && (miembroEnGrupo.verAutor() != null) && (miembroEnGrupo.verAutor().verDni() != 0) 
                    && (miembroEnGrupo.verAutor().verApellidos() != null) && (!miembroEnGrupo.verAutor().verApellidos().isBlank())
                    && (miembroEnGrupo.verAutor().verNombres() !=null) && (!miembroEnGrupo.verAutor().verNombres().isBlank())
                    && (miembroEnGrupo.verGrupo() != null)
                    && (miembroEnGrupo.verGrupo().verNombre() != null) && (!miembroEnGrupo.verGrupo().verNombre().isBlank())
                    && (miembroEnGrupo.verGrupo().verDescripcion() != null) && (!miembroEnGrupo.verGrupo().verDescripcion().isBlank())
                    && (miembroEnGrupo.verRol() != null) 
                    && (fechaPublicacion !=null) 
                    && (tipo != null) && (tipo.verNombre() != null) && (!tipo.verNombre().isBlank())
                    && (idioma != null) && (idioma.verNombre() != null) && (!idioma.verNombre().isBlank())
                    && (lugar != null) && (lugar.verNombre() != null) && (!lugar.verNombre().isBlank())
                    && (palabrasClaves != null) && (!palabrasClaves.isEmpty()) 
                    && (enlace != null) && (!enlace.isBlank())
                    && (resumen != null) && (!resumen.isBlank())){
                    
                        int indice=publicaciones.indexOf(publicacion);
                        if(indice == (-1)){
                            return MENSAJE_MODIFICACION_SINPUBLICACION;
                            }
                        else{
                            publicaciones.get(indice).asignarMiembro(miembroEnGrupo);
                            publicaciones.get(indice).asignarFechaPublicacion(fechaPublicacion);
                            publicaciones.get(indice).asignarTipo(tipo);
                            publicaciones.get(indice).asignarIdioma(idioma);
                            publicaciones.get(indice).asignarLugar(lugar);
                            publicaciones.get(indice).asignarPalabras(palabrasClaves);
                            publicaciones.get(indice).asignarEnlace(enlace);
                            publicaciones.get(indice).asignarResumen(resumen);
                            return MENSAJE_MODIFICACION_EXITO;
                        }
                    }
                else{return MENSAJE_MODIFICACION_BLANCO;}
        }
       else {return MENSAJE_MODIFICACION_SINPUBLICACION;}
    }

    @Override
    public boolean hayPublicacionesConEstaPalabraClave(PalabraClave palabraClave) {
       if((palabraClave != null) && (!palabraClave.verNombre().isBlank()) ){
           for(Publicacion p: publicaciones){
               for(PalabraClave c: p.verPalabras()){
                    if(c.equals(palabraClave))
                        return true;
                }
            }
       }
       return false;
    }

    @Override
    public boolean hayPublicacionesConEsteLugar(Lugar lugar) {
        if((lugar != null) && (!lugar.verNombre().isBlank())){  
            for(Publicacion p: publicaciones){
                if(lugar.equals(p.verLugar()))
                    return true;   
            }
        }
        return false;
    }

    @Override
    public boolean hayPublicacionesConEsteIdioma(Idioma idioma) {
       if((idioma != null) && (!idioma.verNombre().isBlank())){  
            for(Publicacion p: publicaciones){
                if(idioma.equals(p.verIdioma()))
                    return true;   
            }
        }
        return false;
    }

    @Override
    public boolean hayPublicacionesConEsteTipo(Tipo tipo) {
        if((tipo != null) && (!tipo.verNombre().isBlank())){  
            for(Publicacion p: publicaciones){
                if(tipo.equals(p.verTipo()))
                    return true;   
            }
        }
        return false;
    }

    @Override
    public boolean hayPublicacionesConEsteAutor(Autor autor) {
        if((autor != null) && (autor.verDni() !=0) && (autor.verApellidos() != null) && (!autor.verApellidos().isBlank())
            && (autor.verNombres() !=null) && (!autor.verNombres().isBlank()) ){
            
            for(Publicacion p: publicaciones){
                if(autor.equals(p.verMiembro().verAutor()))
                    return true;   
            }
        }
        return false;
    }

    @Override
    public boolean existeEstaPublicacion(Publicacion publicacion) {
        if(publicaciones.contains(publicacion)){
                return true;
        }
        return false;
    }

    @Override
    public List<Publicacion> verPublicaciones() {
        this.publicaciones.sort(publicacionComparador);
        return this.publicaciones;
    }

    @Override
    public Publicacion verPublicaion(String titulo) {
        Publicacion publicacionBuscada= new Publicacion(titulo,null,null,null,null,null,null,null,null);
        int indice=publicaciones.indexOf(publicacionBuscada);
        if(indice != (-1))
            return publicaciones.get(indice);
        else
            return null;
    }

    @Override
    public String borrarPublicacion(Publicacion publicacion) {
        if(this.existeEstaPublicacion(publicacion)){
            this.publicaciones.remove(publicacion);
            return MENSAJE_BORRADO_EXITO;
        }
        return MENSAJE_MODIFICACION_SINPUBLICACION;
    }

    @Override
    public List<Publicacion> buscarPublicaciones(String titulo) {
        List<Publicacion> publicacionesBuscadas = new ArrayList<>();
        if((titulo!= null) && (!titulo.isBlank())){
            for(Publicacion p: publicaciones){
                if((p.verTitulo().equals(titulo)) || (p.verTitulo().startsWith(titulo))){
                    if((!publicacionesBuscadas.contains(p))){
                     publicacionesBuscadas.add(p);
                    }
                } 
            }
            if(publicacionesBuscadas != null){
                publicacionesBuscadas.sort(publicacionComparador);
                return publicacionesBuscadas;
            }      
        }
        return publicacionesBuscadas;
    }
    
@Override
    public void mostrarPublicaciones(){
        for(Publicacion p: this.publicaciones) {
            p.mostrar();
        }
    }
}
