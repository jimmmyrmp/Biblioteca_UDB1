package biblioteca.udb.ejemplares;

import biblioteca.udb.ConexionBD;
import biblioteca.udb.ejemplares.textos.*;
import biblioteca.udb.ejemplares.audio_video.*;
import biblioteca.udb.ejemplares.Cartografia.*;
import biblioteca.udb.ejemplares.digitales.*;
import biblioteca.udb.ejemplares.academico.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class catalogo {

    public boolean agregarEjemplar(ejemplares ejemplar) throws SQLException {
        Connection conexion = ConexionBD.conectar();

        if (conexion == null) return false;

        PreparedStatement stmtEjemplar = null;

        try {
            String sqlEjemplar = "INSERT INTO ejemplares (codigo, titulo, autor, año, ubicacion, disponible, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmtEjemplar = conexion.prepareStatement(sqlEjemplar);

            stmtEjemplar.setString(1, ejemplar.getCodigo());
            stmtEjemplar.setString(2, ejemplar.getTitulo());
            stmtEjemplar.setString(3, ejemplar.getAutor());
            stmtEjemplar.setInt(4, ejemplar.getAño());
            stmtEjemplar.setString(5, ejemplar.getUbicacion());
            stmtEjemplar.setInt(6, 1);
            stmtEjemplar.setString(7, ejemplar.getTipo());

            int filasEjemplar = stmtEjemplar.executeUpdate();

            if (filasEjemplar > 0) {
                return guardarEnTablaEspecifica(ejemplar, conexion);
            }
            return false;

        } finally {
        }
    }

    private boolean guardarEnTablaEspecifica(ejemplares ejemplar, Connection conexion) throws SQLException {
        PreparedStatement stmt = null;

        try {
            String tipo = ejemplar.getTipo();

            if ("Libro".equals(tipo)) {
                Libros libro = (Libros) ejemplar;
                String sql = "INSERT INTO libros (codigo_ejemplar, isbn, editorial, paginas, genero) VALUES (?, ?, ?, ?, ?)";
                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, libro.getCodigo());
                stmt.setString(2, libro.getIsbn());
                stmt.setString(3, libro.getEditorial());
                stmt.setInt(4, libro.getNumPaginas());
                stmt.setString(5, libro.getGenero());

            } else if ("Revista".equals(tipo)) {
                Revistas revista = (Revistas) ejemplar;
                String sql = "INSERT INTO revistas (codigo_ejemplar, issn, numero, volumen, periodicidad) VALUES (?, ?, ?, ?, ?)";
                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, revista.getCodigo());
                stmt.setString(2, revista.getIssn());
                stmt.setInt(3, revista.getNumero());
                stmt.setInt(4, revista.getVolumen());
                stmt.setString(5, revista.getPeriodicidad());

            } else if ("Tesis".equals(tipo)) {
                Tesis tesis = (Tesis) ejemplar;
                String sql = "INSERT INTO tesis (codigo_ejemplar, carrera, facultad, director, grado_academico) VALUES (?, ?, ?, ?, ?)";
                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, tesis.getCodigo());
                stmt.setString(2, tesis.getCarrera());
                stmt.setString(3, tesis.getFacultad());
                stmt.setString(4, tesis.getDirector());
                stmt.setString(5, tesis.getGradoAcademico());

            } else if ("Periodico".equals(tipo)) {
                periodicos periodico = (periodicos) ejemplar;
                String sql = "INSERT INTO periodicos (codigo_ejemplar, fecha_publicacion, seccion, numero_paginas, frecuencia) VALUES (?, ?, ?, ?, ?)";
                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, periodico.getCodigo());
                stmt.setDate(2, new java.sql.Date(periodico.getFechaPublicacion().getTime()));
                stmt.setString(3, periodico.getSeccion());
                stmt.setInt(4, periodico.getNumeroPaginas());
                stmt.setString(5, periodico.getFrecuencia());

            } else if ("Folleto".equals(tipo)) {
                Folletos folleto = (Folletos) ejemplar;
                String sql = "INSERT INTO folletos (codigo_ejemplar, tema, institucion, numero_paginas, tipo_folleto) VALUES (?, ?, ?, ?, ?)";
                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, folleto.getCodigo());
                stmt.setString(2, folleto.getTema());
                stmt.setString(3, folleto.getInstitucion());
                stmt.setInt(4, folleto.getNumeroPaginas());
                stmt.setString(5, folleto.getTipoFolleto());

            } else if ("CD".equals(tipo)) {
                CD cd = (CD) ejemplar;
                String sql = "INSERT INTO cds (codigo_ejemplar, artista, discografia, duracion, genero_musical, numero_pistas) VALUES (?, ?, ?, ?, ?, ?)";
                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, cd.getCodigo());
                stmt.setString(2, cd.getArtista());
                stmt.setString(3, cd.getDiscografia());
                stmt.setInt(4, cd.getDuracion());
                stmt.setString(5, cd.getGeneroMusical());
                stmt.setInt(6, cd.getNumeroPistas());

            } else if ("DVD".equals(tipo)) {
                DVD dvd = (DVD) ejemplar;
                String sql = "INSERT INTO dvds (codigo_ejemplar, director, productora, duracion, genero, formato) VALUES (?, ?, ?, ?, ?, ?)";
                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, dvd.getCodigo());
                stmt.setString(2, dvd.getDirector());
                stmt.setString(3, dvd.getProductora());
                stmt.setInt(4, dvd.getDuracion());
                stmt.setString(5, dvd.getGenero());
                stmt.setString(6, dvd.getFormato());

            } else if ("AudioLibro".equals(tipo)) {
                AudioLibro audioLibro = (AudioLibro) ejemplar;
                String sql = "INSERT INTO audiolibros (codigo_ejemplar, autor, narrador, editorial, duracion, formato_audio, idioma) VALUES (?, ?, ?, ?, ?, ?, ?)";
                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, audioLibro.getCodigo());
                stmt.setString(2, audioLibro.getAutor());
                stmt.setString(3, audioLibro.getNarrador());
                stmt.setString(4, audioLibro.getEditorial());
                stmt.setInt(5, audioLibro.getDuracion());
                stmt.setString(6, audioLibro.getFormatoAudio());
                stmt.setString(7, audioLibro.getIdioma());

            } else if ("Mapa".equals(tipo)) {
                Mapa mapa = (Mapa) ejemplar;
                String sql = "INSERT INTO mapas (codigo_ejemplar, region, escala, tipo_mapa, editorial, año_edicion, dimensiones) VALUES (?, ?, ?, ?, ?, ?, ?)";
                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, mapa.getCodigo());
                stmt.setString(2, mapa.getRegion());
                stmt.setString(3, mapa.getEscala());
                stmt.setString(4, mapa.getTipoMapa());
                stmt.setString(5, mapa.getEditorial());
                stmt.setInt(6, mapa.getAñoEdicion());
                stmt.setString(7, mapa.getDimensiones());

            } else if ("Atlas".equals(tipo)) {
                Atlas atlas = (Atlas) ejemplar;
                String sql = "INSERT INTO atlas (codigo_ejemplar, tema, editorial, numero_paginas, dimensiones, tipo_encuadernacion, isbn) VALUES (?, ?, ?, ?, ?, ?, ?)";
                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, atlas.getCodigo());
                stmt.setString(2, atlas.getTema());
                stmt.setString(3, atlas.getEditorial());
                stmt.setInt(4, atlas.getNumeroPaginas());
                stmt.setString(5, atlas.getDimensiones());
                stmt.setString(6, atlas.getTipoEncuadernacion());
                stmt.setString(7, atlas.getIsbn());

            } else if ("EBook".equals(tipo)) {
                Ebook ebook = (Ebook) ejemplar;
                String sql = "INSERT INTO ebooks (codigo_ejemplar, autor, editorial, isbn, formato, tamaño, plataforma, url_acceso) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, ebook.getCodigo());
                stmt.setString(2, ebook.getAutor());
                stmt.setString(3, ebook.getEditorial());
                stmt.setString(4, ebook.getIsbn());
                stmt.setString(5, ebook.getFormato());
                stmt.setInt(6, ebook.getTamaño());
                stmt.setString(7, ebook.getPlataforma());
                stmt.setString(8, ebook.getUrlAcceso());

            } else if ("Informe_Tecnico".equals(tipo)) {
                InformeTecnico informe = (InformeTecnico) ejemplar;
                String sql = "INSERT INTO informes_tecnicos (codigo_ejemplar, institucion, area_tecnica, numero_informe, autores, departamento, numero_paginas, tipo_informe) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, informe.getCodigo());
                stmt.setString(2, informe.getInstitucion());
                stmt.setString(3, informe.getAreaTecnica());
                stmt.setString(4, informe.getNumeroInforme());
                stmt.setString(5, informe.getAutores());
                stmt.setString(6, informe.getDepartamento());
                stmt.setInt(7, informe.getNumeroPaginas());
                stmt.setString(8, informe.getTipoInforme());
            }

            if (stmt != null) {
                return stmt.executeUpdate() > 0;
            }
            return false;

        } finally {
            if (stmt != null) stmt.close();
        }
    }
}