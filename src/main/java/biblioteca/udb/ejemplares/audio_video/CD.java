package biblioteca.udb.ejemplares.audio_video;
import biblioteca.udb.ejemplares.ejemplares;

public class CD extends ejemplares {
    private String artista;
    private String discografia;
    private int duracion; // en minutos
    private String generoMusical;
    private int numeroPistas;

    public CD(String codigo, String titulo, String artista, int año, String ubicacion,
               String discografia, int duracion, String generoMusical, int numeroPistas) {
        super(codigo, titulo, artista, año, ubicacion);
        this.tipo = "CD";
        this.artista = artista;
        this.discografia = discografia;
        this.duracion = duracion;
        this.generoMusical = generoMusical;
        this.numeroPistas = numeroPistas;
    }

    public String getArtista() { return artista; }
    public String getDiscografia() { return discografia; }
    public int getDuracion() { return duracion; }
    public String getGeneroMusical() { return generoMusical; }
    public int getNumeroPistas() { return numeroPistas; }
}