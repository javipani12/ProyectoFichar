package modelos;

/**
 *
 * @author Javier Fernandez Panigua
 */
public class Empleado {
    
    // Atributos de la clase
    private int idEmpleado;
    private String nombre;
    private String apellido;
    private float salario;
    private String email;
    private Departamento dpt;
    private int codigoEmp;
    
    // Constructores
    public Empleado() {
        this.idEmpleado = -1;
        this.nombre = null;
        this.apellido = null;
        this.salario = 0F;
        this.email = null;
        this.dpt = new Departamento();
        this.codigoEmp = -1;
    }

    public Empleado(int idEmpleado, String nombre, String apellido, float salario, String email, Departamento dpt) {
        this(nombre, apellido, salario, email, dpt);
        this.idEmpleado = idEmpleado;
    }

    public Empleado(String nombre, String apellido, float salario, String email, Departamento dpt) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
        this.email = email;
        this.dpt = dpt;
    }

    public Empleado(String nombre, String apellido, float salario, String email, Departamento dpt, int codigoEmp) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
        this.email = email;
        this.dpt = dpt;
        this.codigoEmp = codigoEmp;
    }

    public Empleado(int idEmpleado, String nombre, String apellido, float salario, String email, Departamento dpt, int codigoEmp) {
        this(nombre, apellido, salario, email, dpt, codigoEmp);
        this.idEmpleado = idEmpleado;
    }
    
    
    
    // Getters y setters
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Departamento getDpt() {
        return dpt;
    }

    public void setDpt(Departamento dpt) {
        this.dpt = dpt;
    }

    public int getCodigoEmp() {
        return codigoEmp;
    }

    public void setCodigoEmp(int codigoEmp) {
        this.codigoEmp = codigoEmp;
    }

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", apellido=" + apellido + ", salario=" + salario + ", email=" + email + ", dpt=" + dpt + '}';
    }    
    
}