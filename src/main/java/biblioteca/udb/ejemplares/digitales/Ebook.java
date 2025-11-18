package biblioteca.udb.ejemplares.digitales;

import biblioteca.udb.ejemplares.ejemplares;

public class Ebook extends ejemplares {
    private String autor;
    private String editorial;
    private String isbn;
    private String formato;
    private int tamaño;
    private String plataforma;
    private String urlAcceso;

    public Ebook(String codigo, String titulo, String autor, int año, String ubicacion,
                 String editorial, String isbn, String formato, int tamaño,
                 String plataforma, String urlAcceso) {
        super(codigo, titulo, autor, año, ubicacion);
        this.tipo = "EBook";
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.formato = formato;
        this.tamaño = tamaño;
        this.plataforma = plataforma;
        this.urlAcceso = urlAcceso;
    }

    public String getAutor() { return autor; }
    public String getEditorial() { return editorial; }
    public String getIsbn() { return isbn; }
    public String getFormato() { return formato; }
    public int getTamaño() { return tamaño; }
    public String getPlataforma() { return plataforma; }
    public String getUrlAcceso() { return urlAcceso; }
}
