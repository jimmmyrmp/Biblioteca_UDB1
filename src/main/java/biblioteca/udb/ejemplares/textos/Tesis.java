package biblioteca.udb.ejemplares.textos;

import biblioteca.udb.ejemplares.ejemplares;

public class Tesis extends ejemplares {
    private String carrera;
    private String facultad;
    private String director;
    private String gradoAcademico;

    public Tesis(String codigo, String titulo, String autor, int año, String ubicacion,
                 String carrera, String facultad, String director, String gradoAcademico) {
        super(codigo, titulo, autor, año, ubicacion);
        this.tipo = "Tesis";
        this.carrera = carrera;
        this.facultad = facultad;
        this.director = director;
        this.gradoAcademico = gradoAcademico;
    }

    public String getCarrera() { return carrera; }
    public String getFacultad() { return facultad; }
    public String getDirector() { return director; }
    public String getGradoAcademico() { return gradoAcademico; }
}
