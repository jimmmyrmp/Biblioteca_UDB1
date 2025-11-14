package biblioteca.udb.ejemplares.audio_video;

import biblioteca.udb.ejemplares.ejemplares;

public class AudioLibro extends ejemplares {
    private String autor;
    private String narrador;
    private String editorial;
    private int duracion;
    private String formatoAudio;
    private String idioma;

    public AudioLibro(String codigo, String titulo, String autor, int año, String ubicacion,
                      String narrador, String editorial, int duracion, String formatoAudio, String idioma) {
        super(codigo, titulo, autor, año, ubicacion);
        this.tipo = "AUDIOLIBRO";
        this.autor = autor;
        this.narrador = narrador;
        this.editorial = editorial;
        this.duracion = duracion;
        this.formatoAudio = formatoAudio;
        this.idioma = idioma;
    }

    public String getAutor() { return autor; }
    public String getNarrador() { return narrador; }
    public String getEditorial() { return editorial; }
    public int getDuracion() { return duracion; }
    public String getFormatoAudio() { return formatoAudio; }
    public String getIdioma() { return idioma; }
}
