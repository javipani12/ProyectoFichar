package BD;

import com.mysql.cj.protocol.Resultset;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Departamento;
import modelos.Departamentos;
import modelos.Empleado;
import modelos.Empleados;
import modelos.Ficha;
import modelos.Fichas;

/**
 *
 * @author Javier Fernandez Paniagua
 */
public class GestionBD {
    
    // Atributos
    private final String HOST;
    private final String USUARIO;
    private final String PASSWORD;
    private final String BD;
    private Connection conexion;

    public GestionBD(String HOST, String USUARIO, String PASSWORD, String BD) {
        this.HOST = HOST;
        this.USUARIO = USUARIO;
        this.PASSWORD = PASSWORD;
        this.BD = BD;
    }

    // ------------- CONEXIÓN Y DESCONEXIÓN BD ---------------//
    
    /**
     * Método para realizar la conexión con la BD
     * @return - boolean - Devuelve true si se ha conectado, en caso contrario
     * false.
     */
    private boolean conectar(){
        
        boolean isConectado = true;
        
        try {
            // Indicamos el driver utilizado para la conexión
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Inicializamos la cadena de conexion
            this.conexion = DriverManager.getConnection( "jdbc:mysql://" + HOST + "/" + BD + "?serverTimezone=UTC", USUARIO, PASSWORD );
            
        } catch(ClassNotFoundException e) {
            System.err.println("Error al cargar el driver MySQL, " + e.getMessage());
            isConectado = false;
            
        }  catch(SQLException e) {
            System.err.println("Error de conexión, " + e.getMessage());
            isConectado = false;
            
        }
        
        return isConectado;
        
    }
    
    /**
     * Método para finalizar la conexión
     * @return boolean - Devulve true si se ha finalizado la conexión,
     * false en caso contrario.
     */
    private boolean desconectar(){
        
        boolean isDesconectado = true;
        
        try {
            this.conexion.close();
        } catch(SQLException e) {
            System.err.println("Error en la desconexión, " + e.getMessage());
            isDesconectado = false;
        }
        
        return isDesconectado;
        
    }
    
    
    // ----------------- MÉTODOS DEPARTAMENTO --------------//
    
    /**
     * Método para insertar un departamento en la BD
     * @param departamento - Departamento - departamento a insertar
     * @return boolean - Devuelve true si se ha insertado, false en
     * caso contrario
     */
    public boolean insertarDepartamento( Departamento departamento ) {
        boolean insertado = false;
        
        // Nos conectamos a la BD
        conectar();
        
        // Preparamos la cadena de inserción
        try {
            PreparedStatement ps = conexion.prepareStatement(
                    "INSERT INTO Departamentos"
                            + " VALUES (NULL, ?)"
            );
            
            // Enlazamos los valores con su posición correspondiente
            ps.setString(1, departamento.getNombre());
            
            // Ejecutamos la sentencia
            if (ps.execute()) {
                insertado = true;
            }
            
            // Nos desconectamos
            desconectar();
            
        } catch (SQLException ex) {
            System.err.println("Error al insertar, " + ex.getMessage());
        }
        
        return insertado;
    }
    
    /**
     * Método para borrar un departamento de la BD
     * @param departamento - Empleado - departamento a borrar de la BD
     * @return - boolean - Retorna true si lo ha borrado, en caso contrario
     * retornará false.
     */
    public boolean borrarDepartamento( Departamento departamento ){
        
        boolean borrado = false;
        
        conectar();
        
        try {
            PreparedStatement ps = this.conexion.prepareStatement(
                    "DELETE FROM departamentos "
                            + "WHERE idDepartamento = ?"
            );
            
            ps.setInt(1, departamento.getIdDepartamento());
            
            if (ps.execute()) {
                borrado = ps.execute();
            }
            
            desconectar();
            
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error al borrar, " + ex.getMessage());
        }
        
        return borrado;
        
    }
    
    /**
     * Método para actualizar los datos de un departamento
     * @param depOld - Departamento - departamento al que actualizaremos los datos
     * @param depNew - Departamento - nuevos datos del departamento
     * @return boolean - Devuelve true si lo ha podido actualizar, en caso
     * contrario devolverá false
     */
    public boolean modificarDepartamento( Departamento depOld, Departamento depNew ) {
        
        boolean modificado = false;
        
        conectar();
        
        try {
            PreparedStatement ps = this.conexion.prepareStatement(
                    "UPDATE departamentos "
                            + "SET nombre = ? "
                            + "WHERE idDepartamento = ?"
            );
            
            ps.setString(1, depNew.getNombre());
            ps.setInt(2, depOld.getIdDepartamento());
            
            if (ps.execute()) {
                modificado = true;
            }
            
            desconectar();
            
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error al actualizar, " + ex.getMessage());
        }
        
        return modificado;
        
    }
    
    /**
     * Método para listar todos los departamentos de la tabla departamentos
     * @return Departamentos - Nos devuelve una lista con todos los departamentos
     * de la tabla.
     */
    public Departamentos listarDepartamentos(){
        
        Departamentos listaDepartamentos = new Departamentos();
        
        conectar();
        
        try {
            PreparedStatement ps = this.conexion.prepareStatement(
                    "SELECT * FROM departamentos"
            );
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Departamento departamento = new Departamento(rs.getInt(1), 
                        rs.getString(2)
                );
                                
                listaDepartamentos.add(departamento);
            }
            
            desconectar();
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error al recoger los datos, " + ex.getMessage());
        }  
        
        return listaDepartamentos;
        
    }
    
    /**
     * Método para buscar un departamento mediante su nombre
     * @param nombreDepartamento - String - nombre del departamento a buscar 
     * @return - Departamento - Si encuentra el departamento indicado
     * nos devuelve todos sus datos, en caso contrario nos devuelve un 
     * departamento nulo
     */
    public Departamento buscarDepartamento(String nombreDepartamento){
        
        Departamento dept = new Departamento();
        
        conectar();
        
        try {
            PreparedStatement ps = this.conexion.prepareStatement(
                    "SELECT * FROM departamentos WHERE nombre = ?"
            );
            
            ps.setString(1, nombreDepartamento);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                dept.setIdDepartamento(rs.getInt(1));
                dept.setNombre(rs.getString(2));
            }
            
            desconectar();
            
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error");
        }
        
        return dept;
        
    }
    
    
    // ------------------- MÉTODOS EMPLEADOS ----------------//
    
    /**
     * Método para insertar un empleado en la BD
     * @param empleado - Empleado - empleado a insertar
     * @return boolean - Devuelve true si se ha insertado, en caso contrario
     * false.
     */
    public boolean insertarEmpleado( Empleado empleado ) {
        boolean insertado = false;
        
        // Nos conectamos a la BD
        conectar();
        
        try {
            // Preparamos la cadena de inserción
            PreparedStatement ps = conexion.prepareStatement(
                    "INSERT INTO empleados"
                    + " VALUES ( NULL, ?, ?, ?, ?, ?, ?)"
            );
            
            // Enlazamos los valores a la sentencia en su posición correspondiente
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setFloat(3, empleado.getSalario());
            ps.setString(4, empleado.getEmail());
            ps.setInt(5, empleado.getDpt().getIdDepartamento());
            ps.setInt(6, empleado.getCodigoEmp());
            
            // Ejecutamos la sentencia
            if( ps.execute() ) {
                insertado = true;
            }
            
            // Nos desconectamos de la BD
            desconectar();
            
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error al insertar" + ex.getMessage());
        }
        
        return insertado;
    }
    
    /**
     * Método para borrar un empleado de la BD
     * @param empleado - Empleado - empleado a borrar de la BD
     * @return - boolean - Retorna true si lo ha borrado, en caso contrario
     * retornará false.
     */
    public boolean borrarEmpleado( Empleado empleado ){
        
        boolean borrado = false;
        
        conectar();
        
        try {
            PreparedStatement ps = this.conexion.prepareStatement(
                    "DELETE FROM empleados WHERE idEmpleado = ?"
            );
            
            ps.setInt(1, empleado.getIdEmpleado());
            
            if (ps.execute()) {
                borrado = ps.execute();
            }
            
            desconectar();
            
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error al borrar, " + ex.getMessage());
        }
        
        return borrado;
        
    }
    
    /**
     * Método para actualizar los datos de un empleado
     * @param empOld - Empleado - empleado al que actualizaremos los datos
     * @param empNew - Empleado - nuevos datos del empleado
     * @return boolean - Devuelve true si lo ha podido actualizar, en caso
     * contrario devolverá false
     */
    public boolean modificarEmpleado( Empleado empOld, Empleado empNew ) {
        
        boolean modificado = false;
        
        conectar();
        
        try {
            PreparedStatement ps = this.conexion.prepareStatement(
                    "UPDATE empleados "
                            + "SET nombre = ?, apellidos = ?, "
                            + "salario = ?, email = ?, idDepartamento = ? "
                            + "codigoEmp = ? WHERE idEmpleado = ?"
            );
            
            ps.setString(1, empNew.getNombre());
            ps.setString(2, empNew.getApellido());
            ps.setFloat(3, empNew.getSalario());
            ps.setString(4, empNew.getEmail());
            ps.setInt(5, empNew.getDpt().getIdDepartamento());
            ps.setInt(6, empNew.getCodigoEmp());
            ps.setInt(7, empOld.getIdEmpleado());
            
            if (ps.execute()) {
                modificado = true;
            }
            
            desconectar();
            
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error al actualizar, " + ex.getMessage());
        }
        
        return modificado;
        
    }
    
    /**
     * Método para listar todos los empleados de la tabla empleados
     * @return Empleados - Nos devuelve una lista con todos los empleados
     * de la tabla.
     */
    public Empleados listarEmpleados(){
        
        Empleados listaEmpleados = new Empleados();
        Departamentos departamentos = new Departamentos();
        
        conectar();
        
        try {
            PreparedStatement ps = this.conexion.prepareStatement(
                    "SELECT * FROM empleados INNER JOIN Departamentos "
                            + " ON Departamentos.idDepartamento = "
                            + " empleados.idDepartamento"
            );
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Empleado empleado = new Empleado(rs.getInt(1), 
                        rs.getString(2), rs.getString(3), 
                        rs.getFloat(4), rs.getString(5),
                        new Departamento(rs.getInt(6), 
                                           rs.getString(9)
                        ), rs.getInt(7)
                );
                
                listaEmpleados.addEmpleado(empleado);
            }
            desconectar();
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error al recoger los datos, " + ex.getMessage());
        }  
        
        return listaEmpleados;
        
    }
    
    /**
     * Método para buscar un empleado mediante su id
     * @param - idEmpleado - int - id del empleado a buscar
     * @return - Empleado - Si encuentra el empleado indicado
     * nos devuelve todos sus datos, en caso contrario nos devuelve un 
     * empleado nulo
     */
    public Empleado buscarEmpleado(int idEmpleado){
        
        Empleado emp = new Empleado();
        Departamentos depts = new Departamentos();
        
        conectar();
        
        try {
            PreparedStatement ps = this.conexion.prepareStatement(
                    "SELECT * FROM empleados WHERE idEmpleado = ?"
            );
            
            ps.setInt(1, idEmpleado);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                emp.setIdEmpleado(rs.getInt(1));
                emp.setNombre(rs.getString(2));
                emp.setApellido(rs.getString(3));
                emp.setSalario(rs.getFloat(4));
                emp.setEmail(rs.getString(5));
                emp.setDpt(depts.getDepartamento(rs.getInt(6)));
                emp.setCodigoEmp(rs.getInt(7));
            }
            
            desconectar();
            
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error");
        }
        
        return emp;
        
    }
    
    /**
     * Método para buscar un empleado mediante su nombre
     * @param nombreEmp - int - id del empleado a buscar
     * @return Empleado - Si encuentra el empleado indicado
     * nos devuelve todos sus datos, en caso contrario nos devuelve un 
     * empleado nulo
     */
    public Empleado buscarEmpleado(String nombreEmp){
        
        Empleado emp = new Empleado();
        Departamentos depts = new Departamentos();
        
        conectar();
        
        try {
            PreparedStatement ps = this.conexion.prepareStatement(
                    "SELECT * FROM empleados WHERE nombre = ?"
            );
            
            ps.setString(1, nombreEmp);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                emp.setIdEmpleado(rs.getInt(1));
                emp.setNombre(rs.getString(2));
                emp.setApellido(rs.getString(3));
                emp.setSalario(rs.getFloat(4));
                emp.setEmail(rs.getString(5));
                emp.setDpt(depts.getDepartamento(rs.getInt(6)));
                emp.setCodigoEmp(rs.getInt(7));
            }
            
            desconectar();
            
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error");
        }
        
        return emp;
        
    }
    
    /**
     * Método para comprobar si el código del Empleado que está intentado
     * loguearse es correcto
     * @param codigo - Código de acceso del Empleado 
     * @param nombre - Nombre del Empleado
     * @return boolean - Devuelve true si el código coincide con el ID del
     * Empleado, en caso contrario false.
     */
    public boolean comprobarCodigo(int codigo, String nombre){
        boolean encontrado = false;
        
        conectar();
        
        try {
            PreparedStatement ps = this.conexion.prepareStatement(
                    "SELECT * FROM empleados WHERE nombre = ? "
                            + "AND idEmpleado = ?"
            );
            
            ps.setString(1, nombre);
            ps.setInt(2, buscarEmpleado(nombre).getIdEmpleado());
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                encontrado = true;
            }
            
            
            desconectar();
            
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error");
        }
        
        desconectar();
        
        return encontrado;
    }
    
    
    // ------------------- MÉTODOS FICHAS ---------------------//

    
    /**
     * Método para insertar un Ficha de entrada en la tabla fichar
     * @param ficha Ficha - Ficha que queremos insertar en la BD
     * @return boolean - Devuelve true si se ha insertado, 
     */
    public boolean insertarFichaEntrada(Ficha ficha) {
        boolean insertado = false;
        
        // Nos conectamos a la BD
        conectar();
        
        try {
            // Preparamos la cadena de inserción
            PreparedStatement ps = conexion.prepareStatement(
                    "INSERT INTO fichar"
                    + " VALUES ( NULL, ?, 1, 0, ?)"
            );
            
            // Enlazamos los valores a la sentencia en su posición correspondiente
            ps.setInt(1, ficha.getEmpleado().getIdEmpleado());
            ps.setTimestamp(2, ficha.getFechaFicha());
            
            // Ejecutamos la sentencia
            if( ps.execute() ) {
                insertado = true;
            }
            
            // Nos desconectamos de la BD
            desconectar();
            
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error al insertar" + ex.getMessage());
        }
        
        return insertado;
    }
    
    /**
     * Método para insertar un Ficha de salida en la tabla fichar
     * @param ficha Ficha - Ficha que queremos insertar en la BD
     * @return boolean - Devuelve true si se ha insertado, 
     */
    public boolean insertarFichaSalida(Ficha ficha) {
        boolean insertado = false;
        
        // Nos conectamos a la BD
        conectar();
        
        try {
            // Preparamos la cadena de inserción
            PreparedStatement ps = conexion.prepareStatement(
                    "INSERT INTO fichar"
                    + " VALUES ( NULL, ?, 0, 1, ?)"
            );
            
            // Enlazamos los valores a la sentencia en su posición correspondiente
            ps.setInt(1, ficha.getEmpleado().getIdEmpleado());
            ps.setTimestamp(2, ficha.getFechaFicha());
            
            // Ejecutamos la sentencia
            if( ps.execute() ) {
                insertado = true;
            }
            
            // Nos desconectamos de la BD
            desconectar();
            
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error al insertar" + ex.getMessage());
        }
        
        return insertado;
    }
    
    /**
     * Método para borrar un ficha de la BD
     * @param ficha Ficha - Ficha que queremos borrar de la BD
     * @return boolean - Nos devuelve true si logra borrar la ficha de la BD,
     * en caso contrario false
     */
    public boolean borrarFicha(Ficha ficha){
        boolean borrado = false;
        
        conectar();
        
        try {
            PreparedStatement ps = this.conexion.prepareStatement(
                    "DELETE FROM fichar WHERE idFicha = ?"
            );
            
            ps.setInt(1, ficha.getIdFicha());
            
            if (ps.execute()) {
                borrado = ps.execute();
            }
            
            desconectar();
            
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error al borrar, " + ex.getMessage());
        }
        
        return borrado;
    }
    
    /**
     * Método para obtener todas de la BD
     * @return Fichas - Nos devuelve un objeto de tipo Fichas, que en sí es
     * un listado de fichas
     */
    public Fichas listarFichas(){
        Fichas listaFichas = new Fichas();
        
        conectar();
        
        try {
            PreparedStatement ps = this.conexion.prepareStatement(
                    "SELECT * FROM fichar f INNER JOIN empleados e "
                            + " ON f.idEmpleado = e.idEmpleado "
                            + "INNER JOIN Departamentos d "
                            + "ON e.idDepartamento = d.idDepartamento"
            );
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Ficha ficha = new Ficha();
                Empleado emp = new Empleado();
                Departamento dep = new Departamento();
                
                dep.setIdDepartamento(rs.getInt(13));
                dep.setNombre(rs.getString(14));
                
                emp.setIdEmpleado(rs.getInt(6));
                emp.setNombre(rs.getString(7));
                emp.setApellido(rs.getString(8));
                emp.setSalario(rs.getFloat(9));
                emp.setEmail(rs.getString(10));
                emp.setDpt(dep);
                emp.setCodigoEmp(rs.getInt(12));
                
                ficha.setIdFicha(rs.getInt(1));
                ficha.setEmpleado(emp);
                ficha.setEntrada(rs.getBoolean(3));
                ficha.setSalida(rs.getBoolean(4));
                ficha.setFechaFicha(rs.getTimestamp(5));
                
                
                listaFichas.annadirFicha(ficha);
            }
            desconectar();
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error al recoger los datos, " + ex.getMessage());
        }  
        
        return listaFichas;
    }
    
    // ------------------ MÉTODO FECHA Y HORA SERVIDOR ---------------//
    
    /**
     * Método para obtener la fecha y hora actual del servidor al que estamos 
     * conectados
     * @return Timestamp - Nos devuelve la fecha y hora actual del servidor
     */
    public Timestamp obtenerFechaServidor(){
        
        Timestamp fechaHora = new Timestamp(0);
        
        conectar();
        
        try {
            PreparedStatement ps = this.conexion.prepareStatement(
                    "SELECT localtimestamp()"
            );
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                fechaHora = rs.getTimestamp("localtimestamp()");
            }
            desconectar();
        } catch (SQLException ex) {
            System.err.println("Se ha producido un error al recoger los datos, " + ex.getMessage());
        }  
        
        return fechaHora;
    }
    
}
