package biblioteca.udb.ejemplares;

public class ejemplares {

    protected String tipo = "Ejemplar";
    //codigo para los ejemplares
    private String codigo;
    private String titulo;
    private String autor;
    private int año;
    private String ubicacion;
    private boolean disponible;

    public ejemplares(String codigo, String titulo, String autor, int año, String ubicacion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.año = año;
        this.ubicacion = ubicacion;
        this.disponible = true;
    }

    public ejemplares() {
        this.disponible = true;
    }

    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAño() { return año; }
    public String getUbicacion() { return ubicacion; }
    public boolean isDisponible() { return disponible; }
    public String getTipo() { return tipo; };

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Título: " + titulo + ", Autor: " + autor;
    }
}