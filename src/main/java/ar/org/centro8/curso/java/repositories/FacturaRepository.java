package ar.org.centro8.curso.java.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.org.centro8.curso.java.connectors.Connector;
import ar.org.centro8.curso.java.entities.Factura;
import ar.org.centro8.curso.java.enums.Letra;

public class FacturaRepository {
    private Connection conn = Connector.getConnection();

    public void save(Factura factura) {
        if (factura == null) return;
        try(PreparedStatement ps=conn.prepareStatement(
            "insert into facturas (letra, numero, fecha, monto, id_cliente, legajo_vendedor) values (?,?,?,?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, factura.getLetra().toString());
                ps.setInt(2, factura.getNumero());
                ps.setString(3, factura.getFecha());
                ps.setDouble(4, factura.getMonto());
                ps.setInt(5, factura.getId_cliente());
                ps.setInt(6, factura.getLegajo_vendedor());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                   factura.setId_factura(rs.getInt(1));
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    public void remove(Factura factura) {
        if (factura == null)
            return;
        try (PreparedStatement ps = conn.prepareStatement("update facturas set activo=false where id_factura=?")) {
            ps.setInt(1, factura.getId_factura());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Factura> getAll() {
        List<Factura> list = new ArrayList();
        try (ResultSet rs = conn
                .createStatement()
                .executeQuery("select * from facturas")) {
            while (rs.next()) {
                list.add(new Factura(
                    rs.getInt("id_factura"),
                    Letra.valueOf(rs.getString("letra")),
                    rs.getInt("numero"),
                    rs.getString("fecha"),
                    rs.getDouble("monto"),
                    rs.getInt("id_cliente"),
                    rs.getInt("legajo_vendedor")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Factura getById(int id){
        return getAll()
                        .stream()
                        .filter(factura->factura.getId_factura()==id)
                        .findFirst()
                        .orElse(new Factura());
    }

    public List<Factura>getLikeFecha(String fecha){
        if(fecha==null) return new ArrayList();
        return getAll()
                         .stream()
                         .filter(factura->factura
                                                 .getFecha()
                                                 .toLowerCase()
                                                 .contains(fecha.toLowerCase()))
                         .toList();
    }
}
