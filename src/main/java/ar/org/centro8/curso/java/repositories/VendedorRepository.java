package ar.org.centro8.curso.java.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.org.centro8.curso.java.connectors.Connector;
import ar.org.centro8.curso.java.entities.Vendedor;

public class VendedorRepository {
    private Connection conn = Connector.getConnection();

    public void save(Vendedor vendedor) {
        if (vendedor == null) return;
        try(PreparedStatement ps=conn.prepareStatement(
            "insert into vendedores (nombre, apellido, dni, tel_celular, email) values (?,?,?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, vendedor.getNombre());
                ps.setString(2, vendedor.getApellido());
                ps.setInt(3, vendedor.getDni());
                ps.setInt(4, vendedor.getTel_celular());
                ps.setString(5, vendedor.getEmail());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                   vendedor.setId_vendedor(rs.getInt(1));
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    public void remove(Vendedor vendedor) {
        if (vendedor == null)
            return;
        try (PreparedStatement ps = conn.prepareStatement("update vendedores set activo=false where id_vendedor=?")) {
            ps.setInt(1, vendedor.getId_vendedor());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Vendedor> getAll() {
        List<Vendedor> list = new ArrayList();
        try (ResultSet rs = conn
                .createStatement()
                .executeQuery("select * from vendedores")) {
            while (rs.next()) {
                list.add(new Vendedor(
                    rs.getInt("id_vendedor"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("dni"),
                    rs.getInt("tel_celular"),
                    rs.getString("email")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Vendedor getById(int id){
        return getAll()
                        .stream()
                        .filter(vendedor->vendedor.getId_vendedor()==id)
                        .findFirst()
                        .orElse(new Vendedor());
    }

    public List<Vendedor>getLikeApellido(String apellido){
        if(apellido==null) return new ArrayList();
        return getAll()
                         .stream()
                         .filter(vendedor->vendedor
                                               .getApellido()
                                               .toLowerCase()
                                               .contains(apellido.toLowerCase()))
                         .toList();
    }
}
