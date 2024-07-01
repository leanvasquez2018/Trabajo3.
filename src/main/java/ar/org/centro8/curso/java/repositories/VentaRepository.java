package ar.org.centro8.curso.java.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.org.centro8.curso.java.connectors.Connector;
import ar.org.centro8.curso.java.entities.Venta;
import ar.org.centro8.curso.java.enums.Letra;

public class VentaRepository {
    private Connection conn = Connector.getConnection();

    public void save(Venta venta) {
        if (venta == null) return;
        try(PreparedStatement ps=conn.prepareStatement(
            "insert into ventas (letra, numero, codigo, cantidad) values (?,?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, venta.getId_factura());
                ps.setInt(2, venta.getId_articulo());
                ps.setString(3, venta.getLetra().toString());
                ps.setInt(4, venta.getNumero());
                ps.setInt(5, venta.getCodigo());
                ps.setInt(6, venta.getCantidad());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                   venta.setId_venta(rs.getInt(1));
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    public void remove(Venta venta) {
        if (venta == null)
            return;
        try (PreparedStatement ps = conn.prepareStatement("update ventas set activo=false where id_venta=?")) {
            ps.setInt(1, venta.getId_venta());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Venta> getAll() {
        List<Venta> list = new ArrayList();
        try (ResultSet rs = conn
                .createStatement()
                .executeQuery("select * from ventas")) {
            while (rs.next()) {
                list.add(new Venta(
                    rs.getInt("id_venta"),
                    rs.getInt("id_factura"),
                    rs.getInt("id_articulo"),
                    Letra.valueOf(rs.getString("letra")),
                    rs.getInt("numero"),
                    rs.getInt("codigo"),
                    rs.getInt("cantidad")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Venta getById(int id){
        return getAll()
                        .stream()
                        .filter(venta->venta.getId_venta()==id)
                        .findFirst()
                        .orElse(new Venta());
    }
}
