package lugares.modelos;
import interfaces.IGestorLugares;
import interfaces.IGestorPublicaciones;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import publicaciones.modelos.GestorPublicaciones;
import publicaciones.modelos.Publicacion;

public class GestorLugares implements IGestorLugares{
IGestorPublicaciones gestorPublicaciones=GestorPublicaciones.crear();
private List<Lugar> lugares= new ArrayList<>();

    //Se implementa el patrón Singleton para crear un solo gestor autores:
    
    public static GestorLugares instancia; // variable de clase del tipo GestorAutores, instancia valor predeterminado es null
    private GestorLugares(){               // para evitar que el operador new instanciado ejecute el constructor
    }
    public static GestorLugares crear(){
            if(instancia==null){
            instancia= new GestorLugares();
            }
           return instancia;
    }
    
    //Se crea el comparador con el que se ordenan listas por nombre:
    Comparator<Lugar> lugarComparador=(Lugar lugar1, Lugar lugar2) -> lugar1.verNombre().compareTo(lugar2.verNombre());
    
        @Override
    public String nuevoLugar(String nombre) {
        if((nombre!= null) && (!nombre.isBlank())){
            Lugar lugarNuevo= new Lugar(nombre);              
                if(!this.lugares.contains(lugarNuevo)){
                    this.lugares.add(lugarNuevo);
                    return MENSAJE_EXITO;
                }
                else 
                    return MENSAJE_REPETIDO;
        }
        else
            return MENSAJE_ERROR;
    }
    @Override
    public List<Lugar> verLugares() {
       this.lugares.sort(lugarComparador);
       return this.lugares;
    }
    
    @Override
    public Lugar verLugar(String nombre) {
        Lugar lugarNuevo= new Lugar(nombre); 
        int indice=lugares.indexOf(lugarNuevo);
        if(indice == (-1))
            return null;
        else
            return lugares.get(indice);
    }

    @Override
    public String borrarLugar(Lugar lugar){
    if((lugar!=null)&&(!lugar.verNombre().isBlank())){
        for(Publicacion p: gestorPublicaciones.verPublicaciones()) {
            if(lugar.equals(p.verLugar()))
                return MENSAJE_REPETIDO;
        }
        if(this.existeEsteLugar(lugar)){
            this.lugares.remove(lugar);
            return MENSAJE_EXITO;
        }                
        else
            return MENSAJE_ERROR;
    }                  
    else 
        return MENSAJE_ERROR;
    }

    @Override
    public List<Lugar> buscarLugares(String nombre){
        List<Lugar> lugaresBuscados = new ArrayList<>();
        if((nombre!= null) && (!nombre.isBlank())){
            for(Lugar l: lugares){
                if((l.verNombre().equals(nombre)) || (l.verNombre().startsWith(nombre))){
                    if((!lugaresBuscados.contains(l))){
                     lugaresBuscados.add(l);
                    }
                } 
            }
            if(lugaresBuscados != null){
                lugaresBuscados.sort(lugarComparador);
                return lugaresBuscados;
            }      
        }
        return lugaresBuscados; //QUITÉ DOS ELSE RETURN NULL
    } 

    @Override
    public boolean existeEsteLugar(Lugar lugar) {
        for(Lugar t: this.lugares){
                if(t.equals(lugar))
                    return true;
                }
        return false;
    }
    
    @Override
    public void mostrarLugares(){
    for(Lugar t: this.lugares)
        t.mostrar();
    }
}
