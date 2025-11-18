package biblioteca.udb.ejemplares.textos;

import biblioteca.udb.ejemplares.ejemplares;

public class Folletos extends ejemplares {
    private String tema;
    private String institucion;
    private int numeroPaginas;
    private String tipoFolleto; // INFORMATIVO, TURISTICO, EDUCATIVO, PROMOCIONAL

    public Folletos(String codigo, String titulo, String autor, int año, String ubicacion,
                    String tema, String institucion, int numeroPaginas, String tipoFolleto) {
        super(codigo, titulo, autor, año, ubicacion);
        this.tipo = "Folleto";
        this.tema = tema;
        this.institucion = institucion;
        this.numeroPaginas = numeroPaginas;
        this.tipoFolleto = tipoFolleto;
    }

    public String getTema() { return tema; }
    public String getInstitucion() { return institucion; }
    public int getNumeroPaginas() { return numeroPaginas; }
    public String getTipoFolleto() { return tipoFolleto; }
}