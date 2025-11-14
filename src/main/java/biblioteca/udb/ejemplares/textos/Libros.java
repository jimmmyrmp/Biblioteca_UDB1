package biblioteca.udb.ejemplares.textos;

import biblioteca.udb.ejemplares.ejemplares;

public class Libros extends ejemplares {
    private String isbn;
    private String editorial;
    private int num_Paginas;
    private String genero;
    private String disponible;

    public Libros(String codigo, String titulo, String autor, int año, String ubicacion, String isbn, String editorial, int num_Paginas, String genero) {
        super(codigo, titulo, autor, año, ubicacion);
        this.tipo = "Libro";
        this.isbn = isbn;
        this.editorial = editorial;
        this.num_Paginas = num_Paginas;
        this.genero = genero;
    }

    public String getIsbn() { return isbn; }
    public String getEditorial() { return editorial; }
    public int getNumPaginas() { return num_Paginas; }
    public String getGenero() { return genero; }
}
