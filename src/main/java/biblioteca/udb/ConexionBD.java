package biblioteca.udb;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConexionBD {

    // DataSource del pool de conexiones configurado en Tomcat
    private static DataSource dataSource;

static {
    try {
        System.out.println("DEBUG POOL V2 ARRANCANDO...");
        InitialContext ctx = new InitialContext();
        dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/bibliotecaPool");
        System.out.println("✅ DataSource 'jdbc/bibliotecaPool' inicializado correctamente.");
    } catch (NamingException e) {
        System.err.println("❌ ERROR al obtener el DataSource: " + e.getMessage());
    }
}


    /**
     * Obtiene una conexión desde el pool de conexiones de Tomcat.
     */
    public static Connection conectar() {
        Connection conexion = null;

        if (dataSource == null) {
            System.err.println("❌ DataSource no inicializado. Revisa la configuración JNDI en Tomcat.");
            return null;
        }

        try {
            conexion = dataSource.getConnection();
            System.out.println("🔵 Conexión obtenida desde el pool de Tomcat.");
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener conexión del pool: " + e.getMessage());
        }

        return conexion;
    }

    /**
     * Cierra la conexión devolviéndola al pool.
     */
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close(); // vuelve al pool
                System.out.println("🟢 Conexión devuelta al pool.");
            } catch (SQLException e) {
                System.err.println("❌ Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}
