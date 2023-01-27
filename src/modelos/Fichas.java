package modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Javier Fernández Paniagua
 */
public class Fichas {
    
    private List<Ficha> listaFichas;

    public Fichas() {
        listaFichas = new ArrayList();
    }
    
    public List<Ficha> getListaFichas(){
        return listaFichas;
    }
    
    /**
     * Método para obtener el tamaño de la lista de las Fichas
     * @return int - Nos devuelve el tamaño de la lista
     */
    public int size(){
        return listaFichas.size();
    }
    
    /**
     * Método para obtener la ficha que se encuentra en la posición indicada
     * @param posicion int - Posición en la lista donde buscaremos
     * @return Ficha - Nos devuelve la ficha que se encuentra en la posición
     * que se recibe como parámetro
     */
    public Ficha getFicha(int posicion){
        return listaFichas.get(posicion);
    }
    
    /**
     * Método para obtener las fichas de la lista que coincidan
     * con el idEmpleado del Empleado que se pasa por parámetro
     * @param emp Empleado - Empleado del que buscar las fichas
     * @return List<Ficha> - Nos devuelve una lista de las fichas
     * de ese empleado
     */
    public List<Ficha> getFicha(Empleado emp) {
        List<Ficha> fichasEmpleado = new ArrayList();
        
        for (Ficha fichaComprobar : listaFichas) {
            if (fichaComprobar.getEmpleado().getIdEmpleado() == 
                    emp.getIdEmpleado()) 
            {
                fichasEmpleado.add(fichaComprobar);
            }
        }
        
        return fichasEmpleado;
    }
    
    /**
     * Método para añadir una Ficha a la lista de Fichas
     * @param ficha Ficha - Ficha que queremos añadir a la lista.
     * @return boolean - Nos devulve true si se logra añadir la Ficha,
     * en caso contrario false
     */
    public boolean annadirFicha(Ficha ficha){
       return listaFichas.add(ficha);
    }
    
    /**
     * Método para borrar una Ficha de la lista de Fichas
     * @param ficha Ficha - Ficha que queremos borrar de la lista.
     * @return boolean - Nos devuelve true si se logra borrar la Ficha,
     * en caso contrario false.
     */
    public boolean borrarFicha(Ficha ficha){
        return listaFichas.remove(ficha);
    }
    
}
