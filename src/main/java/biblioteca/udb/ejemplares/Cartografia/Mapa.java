package biblioteca.udb.ejemplares.Cartografia;

import biblioteca.udb.ejemplares.ejemplares;

public class Mapa extends ejemplares {
    private String region;
    private String escala;
    private String tipoMapa;
    private String editorial;
    private int añoEdicion;
    private String dimensiones;

    public Mapa(String codigo, String titulo, String region, int año, String ubicacion,
                String escala, String tipoMapa, String editorial, int añoEdicion, String dimensiones) {
        super(codigo, titulo, region, año, ubicacion);
        this.tipo = "Mapa";
        this.region = region;
        this.escala = escala;
        this.tipoMapa = tipoMapa;
        this.editorial = editorial;
        this.añoEdicion = añoEdicion;
        this.dimensiones = dimensiones;
    }

    public String getRegion() { return region; }
    public String getEscala() { return escala; }
    public String getTipoMapa() { return tipoMapa; }
    public String getEditorial() { return editorial; }
    public int getAñoEdicion() { return añoEdicion; }
    public String getDimensiones() { return dimensiones; }
}
