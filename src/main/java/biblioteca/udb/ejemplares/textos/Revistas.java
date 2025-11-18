package biblioteca.udb.ejemplares.textos;

import biblioteca.udb.ejemplares.ejemplares;

public class Revistas extends ejemplares {
    private String issn;
    private int numero;
    private int volumen;
    private String periodicidad;

    public Revistas(String codigo, String titulo, String autor, int año, String ubicacion, String issn, int numero, int volumen, String periodicidad) {
        super(codigo, titulo, autor, año, ubicacion);
        this.tipo = "Revista";
        this.issn = issn;
        this.numero = numero;
        this.volumen = volumen;
        this.periodicidad = periodicidad;
    }

    public String getIssn() { return issn; }
    public int getNumero() { return numero; }
    public int getVolumen() { return volumen; }
    public String getPeriodicidad() { return periodicidad; }
}
