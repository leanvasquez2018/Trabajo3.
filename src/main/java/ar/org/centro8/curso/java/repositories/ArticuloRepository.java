package ar.org.centro8.curso.java.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.org.centro8.curso.java.connectors.Connector;
import ar.org.centro8.curso.java.entities.Articulo;

public class ArticuloRepository {
    private Connection conn = Connector.getConnection();

    public void save(Articulo articulo) {
        if (articulo == null) return;
        try(PreparedStatement ps=conn.prepareStatement(
            "insert into articulos (codigo, producto, color, stock, stock_minimo, stock_maximo) values (?,?,?,?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, articulo.getCodigo());
                ps.setString(2, articulo.getProducto());
                ps.setString(3, articulo.getColor());
                ps.setInt(4, articulo.getStock());
                ps.setInt(5, articulo.getStock_minimo());
                ps.setInt(6, articulo.getStock_maximo());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                   articulo.setId_articulo(rs.getInt(1));
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    public void remove(Articulo articulo) {
        if (articulo == null)
            return;
        try (PreparedStatement ps = conn.prepareStatement("update articulos set activo=false where id_articulo=?")) {
            ps.setInt(1, articulo.getId_articulo());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Articulo> getAll() {
        List<Articulo> list = new ArrayList();
        try (ResultSet rs = conn
                .createStatement()
                .executeQuery("select * from articulos")) {
            while (rs.next()) {
                list.add(new Articulo(
                    rs.getInt("id_articulo"),
                    rs.getInt("codigo"),
                    rs.getString("producto"),
                    rs.getString("color"),
                    rs.getInt("stock"),
                    rs.getInt("stock_minimo"),
                    rs.getInt("stock_maximo")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Articulo getById(int id){
        return getAll()
                        .stream()
                        .filter(articulo->articulo.getId_articulo()==id)
                        .findFirst()
                        .orElse(new Articulo());
    }

    public List<Articulo>getLikeProducto(String producto){
        if(producto==null) return new ArrayList();
        return getAll()
                         .stream()
                         .filter(articulo->articulo
                                                 .getProducto()
                                                 .toLowerCase()
                                                 .contains(producto.toLowerCase()))
                         .toList();
    }
}
