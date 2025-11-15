/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.udb;

import java.util.Date;

public class Prestamo {
    
    private int idPrestamo;
    private int idUsuario;
    private String codigoEjemplar;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private Date fechaDevolucionReal;
    private String estado; // "ACTIVO", "DEVUELTO", "MORA"
    private double montoMora;
    
    // Constructor vacío
    public Prestamo() {
    }
    
    // Constructor con parámetros
    public Prestamo(int idPrestamo, int idUsuario, String codigoEjemplar, 
                    Date fechaPrestamo, Date fechaDevolucion, String estado) {
        this.idPrestamo = idPrestamo;
        this.idUsuario = idUsuario;
        this.codigoEjemplar = codigoEjemplar;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
        this.montoMora = 0.0;
    }
    
    // Getters y Setters
    public int getIdPrestamo() {
        return idPrestamo;
    }
    
    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getCodigoEjemplar() {
        return codigoEjemplar;
    }
    
    public void setCodigoEjemplar(String codigoEjemplar) {
        this.codigoEjemplar = codigoEjemplar;
    }
    
    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }
    
    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
    
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
    
    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    public Date getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }
    
    public void setFechaDevolucionReal(Date fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public double getMontoMora() {
        return montoMora;
    }
    
    public void setMontoMora(double montoMora) {
        this.montoMora = montoMora;
    }
    
    @Override
    public String toString() {
        return "Prestamo{" +
                "idPrestamo=" + idPrestamo +
                ", idUsuario=" + idUsuario +
                ", codigoEjemplar='" + codigoEjemplar + '\'' +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", estado='" + estado + '\'' +
                '}';
    }
}