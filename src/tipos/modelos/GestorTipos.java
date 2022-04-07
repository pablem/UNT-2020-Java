package tipos.modelos;
import interfaces.IGestorPublicaciones;
import interfaces.IGestorTipos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import publicaciones.modelos.GestorPublicaciones;
import publicaciones.modelos.Publicacion;


public class GestorTipos implements IGestorTipos {
    IGestorPublicaciones gestorPublicaciones=GestorPublicaciones.crear();
    private List<Tipo> tipos= new ArrayList<>(); // Permite cambiar el tipo de coleccion a usar en el programa (Polimorfismo)

//Se implementa el patrón Singleton para crear un solo gestor autores:
    
    public static GestorTipos instancia; // variable de clase del tipo GestorAutores, instancia valor predeterminado es null
    private GestorTipos(){               // para evitar que el operador new instanciado ejecute el constructor
    }
    public static GestorTipos crear(){
            if(instancia==null){
            instancia= new GestorTipos();
            }
           return instancia;
    }
    
    //Se crea el comparador con el que se ordenan listas por nombre:
    Comparator<Tipo> tipoComparador=(Tipo tipo1, Tipo tipo2) -> tipo1.verNombre().compareTo(tipo2.verNombre());
    
    @Override
    public String nuevoTipo(String nombre) {
        if((nombre!= null) && (!nombre.isBlank())){
            Tipo tipoNuevo= new Tipo(nombre);              
                if(!this.tipos.contains(tipoNuevo)){
                    this.tipos.add(tipoNuevo);
                    return MENSAJE_EXITO;
                }
                else 
                    return MENSAJE_REPETIDO;
        }
        else
            return MENSAJE_ERROR;
    }

//    @Override
//    public List<Tipo> verTipos() {
//        //Se crea el comparador con el que se ordena la lista
//        Comparator<Tipo> tipoComparador=(Tipo tipo1, Tipo tipo2) ->
//                tipo1.verNombre().compareTo(tipo2.verNombre());
//        
//       return this.ordenarTipos(tipoComparador, this.tipos);
//    }
    
    @Override
    public List<Tipo> verTipos() {
        this.tipos.sort(tipoComparador);
        return this.tipos;
    }
        
    @Override
    public Tipo verTipo(String nombre) {
        Tipo tipoNuevo = new Tipo(nombre); 
        int indice = this.tipos.indexOf(tipoNuevo);
        if(indice == (-1))
            return null;
        else
            return tipos.get(indice);
     }
    
//    @Override
//    public String borrarTipo(Tipo tipo){
//    boolean existeTipo= false;
//    int indiceBorrar = (-1);
//    if((tipo!=null)&&(!tipo.verNombre().isBlank())){
//                    for(int indice=0; indice<gestorPublicaciones.verPublicaciones().size(); indice++ ){
//                        if( tipo.equals(gestorPublicaciones.verPublicaciones().get(indice).verTipo()) ){
//                        existeTipo=true;}
//                        }
//                    if(existeTipo){return MENSAJE_REPETIDO; }
//                    else{
//                        indiceBorrar=this.tipos.indexOf(tipo);
//                        if((indiceBorrar!= (-1))){ this.tipos.remove(tipo);
//                        return MENSAJE_EXITO;}
//                        else{return MENSAJE_ERROR;}
//                        }
//                    }
//    else{return MENSAJE_ERROR;}
//    }
    
    @Override
    public String borrarTipo(Tipo tipo){
    if((tipo!=null)&&(!tipo.verNombre().isBlank())){
        for(Publicacion p: gestorPublicaciones.verPublicaciones()) {
            if(tipo.equals(p.verTipo()))
                return MENSAJE_REPETIDO;
        }
        if(this.existeEsteTipo(tipo)) {
            this.tipos.remove(tipo);
            return MENSAJE_EXITO;
        }                
        else
            return MENSAJE_ERROR;
    }                  
    else 
        return MENSAJE_ERROR;
    }
    
    @Override
    public List<Tipo> buscarTipos(String nombre){
        List<Tipo> tiposBuscados = new ArrayList<>();
        if((nombre!= null) && (!nombre.isBlank())){
            for(Tipo t: tipos){
                if((t.verNombre().equals(nombre)) || (t.verNombre().startsWith(nombre))){
                    if((!tiposBuscados.contains(t))){
                     tiposBuscados.add(t);
                    }
                } 
            }
            if(tiposBuscados != null){
//                            Comparator<Tipo> tipoComparador=(Tipo tipo1, Tipo tipo2) ->
//                            tipo1.verNombre().compareTo(tipo2.verNombre());
//                            return this.ordenarTipos(tipoComparador,tiposBuscados);
                tiposBuscados.sort(tipoComparador);
                return tiposBuscados;
            }      
        }
        return tiposBuscados; //QUITÉ DOS ELSE RETURN NULL
    } 
    
    @Override
    public boolean existeEsteTipo(Tipo tipo){
        for(Tipo t: this.tipos){
                if(t.equals(tipo))
                    return true;
        }
        return false;
    } 
  
/***Métodos "auxiliares"***/
    
//    public List<Tipo> ordenarTipos(Comparator<Tipo> tipoComparador , List<Tipo> tiposAOrdenar){
//        List<Tipo>  tiposOrdenados= new ArrayList<>();
//        tiposOrdenados.addAll(tiposAOrdenar);
//        tiposOrdenados.sort(tipoComparador);
//        return tiposOrdenados;
//    }
    
    @Override
    public void mostrarTipos(){     
    for(Tipo t: this.tipos)
        t.mostrar();
    }  
    
    @Override
    public List<Tipo> verTiposSinOrdenar(){
        return this.tipos;
    }
}
