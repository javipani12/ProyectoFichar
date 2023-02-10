import modelos.Departamento;
import modelos.Empleado;
import modelos.Ficha;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author damm
 */
public class TestFicha {
    
    
    @Test
    public void testCrearFicha(){
        Departamento dept = new Departamento();
        
        if (dept == null) {
            fail("Fallo testCrearFicha (Error instanciando Departamento");
        }
        
        if (dept.getIdDepartamento() != -1 || dept.getNombre() != null) {
            fail("Fallo testCrearFicha (Fallo Constructor Departamento (Valor por defecto)");

        }
        
        Empleado emp = new Empleado();
        
        // Comprobaciones para saber si está bien instanciado
        if( emp == null ){
            // resultado = false;
            fail("Fallo testCrearFicha (Error instanciando Empleado)");
        }
        
        if( emp.getIdEmpleado() != -1 || emp.getNombre() != null || 
            emp.getApellido() != null || emp.getSalario() != 0F ||
            emp.getEmail() != null || emp.getDpt() != null)
        {
            fail("Fallo Constructor (Valores por defecto)");
        }
        
        
        Ficha ficha = new Ficha();
        
        if (ficha == null) {
            fail("Fallo en testCrearFicha (Error instanciando Ficha)");
        }
        
        if ( ficha.getIdFicha() != -1 ) {
            
        }
    }
}
