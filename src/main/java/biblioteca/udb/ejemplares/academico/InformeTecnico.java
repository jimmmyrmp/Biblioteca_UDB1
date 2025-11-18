package biblioteca.udb.ejemplares.academico;

import biblioteca.udb.ejemplares.ejemplares;

public class InformeTecnico extends ejemplares {
    private String institucion;
    private String areaTecnica;
    private String numeroInforme;
    private String autores;
    private String departamento;
    private int numeroPaginas;
    private String tipoInforme;

    public InformeTecnico(String codigo, String titulo, String institucion, int año, String ubicacion,
                          String areaTecnica, String numeroInforme, String autores, String departamento,
                          int numeroPaginas, String tipoInforme) {
        super(codigo, titulo, institucion, año, ubicacion);
        this.tipo = "Informe_Tecnico";
        this.institucion = institucion;
        this.areaTecnica = areaTecnica;
        this.numeroInforme = numeroInforme;
        this.autores = autores;
        this.departamento = departamento;
        this.numeroPaginas = numeroPaginas;
        this.tipoInforme = tipoInforme;
    }

    public String getInstitucion() { return institucion; }
    public String getAreaTecnica() { return areaTecnica; }
    public String getNumeroInforme() { return numeroInforme; }
    public String getAutores() { return autores; }
    public String getDepartamento() { return departamento; }
    public int getNumeroPaginas() { return numeroPaginas; }
    public String getTipoInforme() { return tipoInforme; }
}