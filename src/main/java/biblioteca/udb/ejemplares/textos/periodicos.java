package biblioteca.udb.ejemplares.textos;

import biblioteca.udb.ejemplares.ejemplares;

import java.util.Date;

public class periodicos extends ejemplares {
    private Date fechaPublicacion;
    private String seccion;
    private int numeroPaginas;
    private String frecuencia; // DIARIO, SEMANAL, MENSUAL

    public periodicos(String codigo, String titulo, String autor, int año, String ubicacion,
                      Date fechaPublicacion, String seccion, int numeroPaginas, String frecuencia) {
        super(codigo, titulo, autor, año, ubicacion);
        this.tipo = "Periodico";
        this.fechaPublicacion = fechaPublicacion;
        this.seccion = seccion;
        this.numeroPaginas = numeroPaginas;
        this.frecuencia = frecuencia;
    }

    // Getters
    public Date getFechaPublicacion() { return fechaPublicacion; }
    public String getSeccion() { return seccion; }
    public int getNumeroPaginas() { return numeroPaginas; }
    public String getFrecuencia() { return frecuencia; }
}