import BD.GestionBD;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import modelos.Departamento;
import modelos.Empleado;
import modelos.Ficha;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author damm
 */
public class TestGestion {
    
    @Test
    public void testInsertarFichaEntrada(){
        GestionBD conexion = new GestionBD("localhost", "javier", "javier", "empresa_v2");

        // Comprobación para saber si está bien instanciada la Conexión a la BBDD        
        if (conexion == null) {
            fail("Fallo en testInsertarFichaEntrada (Error instanciando Ficha)");
        }
        
        
        Departamento dept = new Departamento(6, "Letras");
        
        // Comprobación para saber si está bien instanciado el Departamento
        if (dept == null) {
            fail("Fallo testInsertarFichaEntrada (Error instanciando Departamento");
        }
        
        // Insertamos el Departamento el BBDD
        if( !conexion.insertarDepartamento(dept) ){
            fail("Fallo testInsertarFichaEntrada (Error insertando Departamento");            
        }
        
        
        Empleado emp = new Empleado(10, "José Luís", "Ramírez", 1200.56F, "joseluis@joseluis.com", dept, 4896);
        
        // Comprobación para saber si está bien instanciado el Empleado
        if( emp == null ){
            fail("Fallo testInsertarFichaEntrada (Error instanciando Empleado)");
        }
        
        // Insertamos el Empleado en la BBDD
        if (!conexion.insertarEmpleado(emp)) {
            fail("Fallo testInsertarFichaEntrada (Error insertando Empleado)");           
        }
        
        
        // Insertamos la Ficha de Entrada en la BBDD
        if ( !conexion.insertarFichaEntrada(emp, Timestamp.valueOf(LocalDateTime.now())) ) {
            fail("Fallo testInsertarFichaEntrada (Error insertando Ficha Entrada)");
        }
        
        // Borramos la Ficha que acabamos de insertar
        if ( !conexion.borrarFicha(conexion.obtenerFichaEntrada(emp)) ) {
            fail("Fallo testInsertarFichaEntrada (Error borrando Ficha Entrada)");
        }
        
        // Borramos el Empleado que acabamos de insertar
        if ( !conexion.borrarEmpleado(conexion.buscarEmpleadoPorNombre(emp)) ) {
            fail("Fallo testInsertarFichaEntrada (Error borrando Empleado)");
        }
        
        // Borramos el Departamento que acabamos de insertar
        if ( !conexion.borrarDepartamento(conexion.buscarDepartamento(dept.getNombre())) ) {
            fail("Fallo testInsertarFichaEntrada (Error borrando Departamento)");
        }
    }
}
