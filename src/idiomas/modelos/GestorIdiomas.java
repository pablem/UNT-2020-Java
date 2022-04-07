package idiomas.modelos;
import interfaces.IGestorIdiomas;
import interfaces.IGestorPublicaciones;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import publicaciones.modelos.GestorPublicaciones;
import publicaciones.modelos.Publicacion;

public class GestorIdiomas implements IGestorIdiomas {
    IGestorPublicaciones gestorPublicaciones=GestorPublicaciones.crear();
    private List<Idioma> idiomas= new ArrayList<>();
    
    //Se implementa el patrón Singleton para crear un solo gestor autores:
    
    public static GestorIdiomas instancia; // variable de clase del tipo GestorAutores, instancia valor predeterminado es null
    private GestorIdiomas(){               // para evitar que el operador new instanciado ejecute el constructor
    }
    public static GestorIdiomas crear(){
            if(instancia==null){
            instancia= new GestorIdiomas();
            }
           return instancia;
    }
    
    //Se crea el comparador con el que se ordenan listas por nombre:
    Comparator<Idioma> idiomaComparador=(Idioma idioma1, Idioma idioma2) -> idioma1.verNombre().compareTo(idioma2.verNombre());
    
    @Override
    public String nuevoIdioma(String nombre) {
        if((nombre!= null) && (!nombre.isBlank())){
            Idioma idiomaNuevo = new Idioma(nombre);              
                if(!this.idiomas.contains(idiomaNuevo)){
                    this.idiomas.add(idiomaNuevo);
                    return MENSAJE_EXITO;
                }
                else 
                    return MENSAJE_REPETIDO;
        }
        else
            return MENSAJE_ERROR;
    }
    
    @Override
    public List<Idioma> verIdiomas() {      
       this.idiomas.sort(idiomaComparador);
       return this.idiomas;
    }

    @Override
    public Idioma verIdioma(String nombre) {
        Idioma idiomaNuevo= new Idioma(nombre); 
        int indice=idiomas.indexOf(idiomaNuevo);
        if(indice == (-1))
            return null;
        else
            return idiomas.get(indice);
    }

    @Override
    public String borrarIdioma(Idioma idioma) {
    if((idioma!=null)&&(!idioma.verNombre().isBlank())){
        for(Publicacion p: gestorPublicaciones.verPublicaciones()) {
            if(idioma.equals(p.verIdioma()))
                return MENSAJE_REPETIDO;
        }
        if(this.existeEsteIdioma(idioma)) {
            this.idiomas.remove(idioma);
            return MENSAJE_EXITO;
        }                
        else
            return MENSAJE_ERROR;
    }                  
    else 
        return MENSAJE_ERROR;
    }

    @Override
    public List<Idioma> buscarIdiomas(String nombre) {
        List<Idioma> idiomasBuscados = new ArrayList<>();
        if((nombre!= null) && (!nombre.isBlank())){
            for(Idioma i: idiomas){
                if((i.verNombre().equals(nombre)) || (i.verNombre().startsWith(nombre))){
                    if((!idiomasBuscados.contains(i))){
                     idiomasBuscados.add(i);
                    }
                } 
            }
            if(idiomasBuscados != null){
                idiomasBuscados.sort(idiomaComparador);
                return idiomasBuscados;
            }      
        }
        return idiomasBuscados; //QUITÉ DOS ELSE RETURN NULL
    } 

    @Override
    public boolean existeEsteIdioma(Idioma idioma) {
        for(Idioma t: this.idiomas){
            if(t.equals(idioma))
                return true;   
        }
        return false;
    }
    
    @Override
    public void mostrarIdiomas(){
    for(Idioma t: this.idiomas)
        t.mostrar();
    }
    
}
