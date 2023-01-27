package modelos;

import java.sql.Timestamp;

/**
 *
 * @author Javier Fernández Paniagua
 */
public class Ficha {
    
    private int idFicha;
    private Empleado empleado;
    private boolean entrada;
    private boolean salida;
    private Timestamp fechaFicha;

    public Ficha() {
    }

    public Ficha(Empleado emp, boolean entrada, boolean salida, 
            Timestamp fechaFicha) 
    {
        this.empleado = emp;
        this.entrada = entrada;
        this.salida = salida;
        this.fechaFicha = fechaFicha;
    }

    public Ficha(int idFicha, Empleado emp, boolean entrada, boolean salida, 
            Timestamp fechaFicha) {
        this(emp, entrada, salida, fechaFicha);
        this.idFicha = idFicha;
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public boolean isEntrada() {
        return entrada;
    }

    public void setEntrada(boolean entrada) {
        this.entrada = entrada;
    }

    public boolean isSalida() {
        return salida;
    }

    public void setSalida(boolean salida) {
        this.salida = salida;
    }

    public Timestamp getFechaFicha() {
        return fechaFicha;
    }

    public void setFechaFicha(Timestamp fechaFicha) {
        this.fechaFicha = fechaFicha;
    }
    
    

    @Override
    public String toString() {
        return "Fichar{" + 
                    "idFicha=" + idFicha + 
                    ", idEmpleado=" + empleado + 
                    ", entrada=" + entrada + 
                    ", salida=" + salida + 
                    ", fechaFicha=" + fechaFicha + 
                '}';
    }
    
}
