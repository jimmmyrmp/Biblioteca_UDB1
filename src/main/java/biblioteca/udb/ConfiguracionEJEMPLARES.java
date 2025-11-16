package biblioteca.udb;

public class ConfiguracionEJEMPLARES {
    private int idConfig;
    private String parametro;
    private String valor;
    private String descripcion;

    public ConfiguracionEJEMPLARES() {
    }

    public ConfiguracionEJEMPLARES(String parametro, String valor, String descripcion) {
        this.parametro = parametro;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public int getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(int idConfig) {
        this.idConfig = idConfig;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValorEntero() {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    
    public String toString() {
        return "Configuracion{parametro=" + parametro + ", valor=" + valor + '}';
    }
}