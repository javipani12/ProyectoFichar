package modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Javier Fernandez Paniagua
 */
public class Empleados {
    
    private List<Empleado> listaEmpleados;

    public Empleados() {
        listaEmpleados = new ArrayList();
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }
    
    
    
    // Métodos GET
    /**
     * Método para obtener un empleado de la lista de empleados
     * @param idEmpleado - int - ID del empleado a buscar
     * @return Empleado - Si encuentra el empleado nos lo devuelve, 
     * si no lo encuentra nos devuelve uno nulo.
     */
    public Empleado getEmpleado( int idEmpleado ){
        
        Empleado empleadoEncontrado = null;
        
        for (Empleado empleado : listaEmpleados) {
            if ( empleado.getIdEmpleado() == idEmpleado ) {
                empleadoEncontrado = empleado;
            }
        }
        
        return empleadoEncontrado;
    }
    
    /**
     * Método que nos devuelve el empleado que se encuentra en la posición
     * que se recibe como parámetro
     * @param posicion - int - Posición sobre la que vamos a buscar
     * @return - Empleado - Nos devuelve el empleado encontrado
     */
    public Empleado getEmpleadoBis( int posicion ) {
        return listaEmpleados.get(posicion);
    }
    
    
    // Métodos SET
    /**
     * Método para eliminar un empleado de lista de empleados
     * @param idEmpleado - int - ID del empleado a eliminar
     * @return boolean - Nos devuelve true si lo ha elimanado,
     * en caso contrario false.
     */
    public boolean deleteEmpleado( int idEmpleado ){
        return listaEmpleados.remove(getEmpleado(idEmpleado));
    }
    
    /**
     * Método para añadir un empleado a la lista
     * @param emp - Empleado - empleado que se añadirá a la lista
     * @return - boolean - Devuelve true si se ha insertado, false 
     * en caso contrario
     */
    public boolean addEmpleado( Empleado emp ){
        return listaEmpleados.add(emp);
    }
    
    /**
     * Método para obtener el tamaño de la lista de Empleados
     * @return int - Tamaño de la lista
     */
    public int size(){
        return listaEmpleados.size();
    }
    
    
    
}
