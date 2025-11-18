package biblioteca.udb.ejemplares.audio_video;

import biblioteca.udb.ejemplares.ejemplares;

public class DVD extends ejemplares {
    private String director;
    private String productora;
    private int duracion;
    private String genero;
    private String formato;

    public DVD(String codigo, String titulo, String director, int año, String ubicacion,
               String productora, int duracion, String genero, String formato) {
        super(codigo, titulo, director, año, ubicacion);
        this.tipo = "DVD";
        this.director = director;
        this.productora = productora;
        this.duracion = duracion;
        this.genero = genero;
        this.formato = formato;
    }

    public String getDirector() { return director; }
    public String getProductora() { return productora; }
    public int getDuracion() { return duracion; }
    public String getGenero() { return genero; }
    public String getFormato() { return formato; }
}