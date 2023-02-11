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
        // Instanciamos la Ficha
        Ficha ficha = new Ficha();
        
        // Comprobación para saber si está bien instanciada la Ficha
        if (ficha == null) {
            fail("Fallo en testCrearFicha (Error instanciando Ficha)");
        }
        
        
        if ( ficha.getIdFicha() != -1 || ficha.getEmpleado() == null ||
                ficha.isEntrada() || ficha.isSalida() || 
                ficha.getFechaFicha() != null) 
        {   
            fail("Fallo en testCrearFicha (Error valores por defecto Ficha)");
        }
    }
}
