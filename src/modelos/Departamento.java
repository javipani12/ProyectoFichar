package modelos;

/**
 *
 * @author Javier Fernandez Paniagua
 */
public class Departamento {
    
    // Atributos
    private int idDepartamento;
    private String nombre;

    // Constructores
    public Departamento() {
        this.idDepartamento = -1;
        this.nombre = null;
    }

    public Departamento(int idDepartamento, String nombre) {
        this(nombre);
        this.idDepartamento = idDepartamento;
    }

    public Departamento(String nombre) {
        this.nombre = nombre;
    }
    
    // Getters y Setters

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Departamento{" + "idDepartamento=" + idDepartamento + ", nombre=" + nombre + '}';
    }
    
    /**
     * Método para comprobar si un departamento es nulo
     * @return - boolean - True si el departamento es nulo, 
     * false en caso contrario
     */
    public boolean isNull() {
        boolean isNull = false;
        
        if (this.getIdDepartamento() == -1 && this.getNombre() == null) {
            isNull = true;
        }
        
        return isNull;
    }
    
}
