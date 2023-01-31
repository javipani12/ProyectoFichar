package interfaz;

import BD.GestionBD;
import modelos.Departamento;
import modelos.Departamentos;
import modelos.Empleado;
import modelos.Empleados;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import modelos.Ficha;

/**
 *
 * @author Javier Fernandez Paniagua
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GestionBD gestionBD = new GestionBD("localhost", "javier", "javier", "empresa_v2");
        
        Departamento departamento = new Departamento(1,"Administración");
        Departamento departamento2 = new Departamento(2, "Dirección");
        
        Empleado empleado = new Empleado(1, "admin", "admin", 5678.23F, "admin@a.com", departamento, 0000);
        Empleado empleado2 = new Empleado(2, "Maria", "Farias", 1345.69F, "lolalolitalola@lola.com", departamento, 4321);
        
        Ficha ficha1_1 = new Ficha(1, empleado, true, true, 
                Timestamp.valueOf(LocalDateTime.of(2023, Month.MARCH, 2, 16, 45, 22))
        );
        
        Timestamp fechaHora = gestionBD.obtenerFechaServidor();
        System.out.println(fechaHora);
        
        Ficha ficha1_2 = new Ficha(1, empleado, true, true, fechaHora);
        
//        gestionBD.insertarDepartamento(departamento);
//        gestionBD.insertarDepartamento(departamento2);
//        gestionBD.insertarEmpleado(empleado);
//        gestionBD.insertarEmpleado(empleado2);
    }
    
}
