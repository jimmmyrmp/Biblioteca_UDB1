package biblioteca.udb.ejemplares.Cartografia;

import biblioteca.udb.ejemplares.ejemplares;

public class Atlas extends ejemplares {
    private String tema;
    private String editorial;
    private int numeroPaginas;
    private String dimensiones;
    private String tipoEncuadernacion;
    private String isbn;

    public Atlas(String codigo, String titulo, String tema, int año, String ubicacion,
                 String editorial, int numeroPaginas, String dimensiones,
                 String tipoEncuadernacion, String isbn) {
        super(codigo, titulo, tema, año, ubicacion);
        this.tipo = "ATLAS";
        this.tema = tema;
        this.editorial = editorial;
        this.numeroPaginas = numeroPaginas;
        this.dimensiones = dimensiones;
        this.tipoEncuadernacion = tipoEncuadernacion;
        this.isbn = isbn;
    }

    public String getTema() { return tema; }
    public String getEditorial() { return editorial; }
    public int getNumeroPaginas() { return numeroPaginas; }
    public String getDimensiones() { return dimensiones; }
    public String getTipoEncuadernacion() { return tipoEncuadernacion; }
    public String getIsbn() { return isbn; }
}