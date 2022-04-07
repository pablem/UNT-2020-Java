package palabrasclaves.modelos;
import interfaces.IGestorPalabrasClaves;
import interfaces.IGestorPublicaciones;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import publicaciones.modelos.GestorPublicaciones;
import publicaciones.modelos.Publicacion;

public class GestorPalabrasClaves implements IGestorPalabrasClaves {
    IGestorPublicaciones gestorPublicaciones=GestorPublicaciones.crear();
    private List<PalabraClave> palabrasClaves= new ArrayList<>();
    
//Se implementa el patrón Singleton para crear un solo gestor autores:
    
    public static GestorPalabrasClaves instancia; // variable de clase del tipo GestorAutores, instancia valor predeterminado es null
    private GestorPalabrasClaves(){               // para evitar que el operador new instanciado ejecute el constructor
    }
    public static GestorPalabrasClaves crear(){
            if(instancia==null){
            instancia= new GestorPalabrasClaves();
            }
           return instancia;
    }
    //Se crea el comparador con el que se ordenan listas por nombre:
    Comparator<PalabraClave> palabraClaveComparador=(PalabraClave palabraClave1, PalabraClave palabraClave2) -> palabraClave1.verNombre().compareTo(palabraClave2.verNombre());
    
   @Override
    public String nuevaPalabraClave(String nombre) {
        if((nombre!= null) && (!nombre.isBlank())){
            PalabraClave palabraClaveNueva= new PalabraClave(nombre);              
                if(!this.palabrasClaves.contains(palabraClaveNueva)){
                    this.palabrasClaves.add(palabraClaveNueva);
                    return MENSAJE_EXITO;
                }
                else 
                    return MENSAJE_REPETIDO;
        }
        else
            return MENSAJE_ERROR;
    }

    @Override
    public List<PalabraClave> verPalabrasClaves() {
        this.palabrasClaves.sort(palabraClaveComparador);
        return this.palabrasClaves;
    }
    
    @Override
    public PalabraClave verPalabraClave(String nombre) {
        PalabraClave palabraClaveNueva= new PalabraClave(nombre); 
        int indice=palabrasClaves.indexOf(palabraClaveNueva);
        if(indice == (-1)){
            return null;
            }
        else{
            return palabrasClaves.get(indice);
            }
    }

@Override
    public String borrarPalabraClave(PalabraClave palabraClave){
    if((palabraClave!=null)&&(!palabraClave.verNombre().isBlank())){
        for(Publicacion p: gestorPublicaciones.verPublicaciones()) {
            if(palabraClave.equals(p.verPalabras()))
                return MENSAJE_REPETIDO;
        }
        if(this.existeEstaPalabraClave(palabraClave)) {
            this.palabrasClaves.remove(palabraClave);
            return MENSAJE_EXITO;
        }                
        else
            return MENSAJE_ERROR;
    }                  
    else 
        return MENSAJE_ERROR;
    }

    @Override
    public List<PalabraClave> buscarPalabrasClaves(String nombre) {
    List<PalabraClave> palabrasClavesBuscadas= new ArrayList<>();
    if((nombre!= null) && (!nombre.isBlank())){
         for(PalabraClave p: palabrasClaves){
                if((p.verNombre().equals(nombre)) || (p.verNombre().startsWith(nombre))){
                    if((!palabrasClavesBuscadas.contains(p))){
                     palabrasClavesBuscadas.add(p);
                    }
                } 
            }
            if(palabrasClavesBuscadas != null){
                palabrasClavesBuscadas.sort(palabraClaveComparador);
                return palabrasClavesBuscadas;
            }      
        }
        return palabrasClavesBuscadas; 
    } 

    @Override
    public boolean existeEstaPalabraClave(PalabraClave palabraClave) {
        for(PalabraClave p: this.palabrasClaves){
                if(p.equals(palabraClave))
                    return true;
        }
        return false;
    }
    
/***Métodos "auxiliares"***/   
    
    @Override
    public void mostrarPalabrasClaves(){
    for(PalabraClave p: this.palabrasClaves)
        p.mostrar();
    }   
}
