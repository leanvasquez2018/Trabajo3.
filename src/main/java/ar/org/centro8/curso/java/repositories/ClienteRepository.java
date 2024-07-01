package ar.org.centro8.curso.java.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.org.centro8.curso.java.connectors.Connector;
import ar.org.centro8.curso.java.entities.Cliente;

public class ClienteRepository {
    private Connection conn = Connector.getConnection();

    public void save(Cliente cliente) {
        if (cliente == null) return;
        try(PreparedStatement ps=conn.prepareStatement(
            "insert into clientes (nombre, apellido, dni, tel_celular, email) values (?,?,?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getApellido());
                ps.setInt(3, cliente.getDni());
                ps.setInt(4, cliente.getTel_celular());
                ps.setString(5, cliente.getEmail());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                   cliente.setId_cliente(rs.getInt(1));
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    public void remove(Cliente cliente) {
        if (cliente == null)
            return;
        try (PreparedStatement ps = conn.prepareStatement("update clientes set activo=false where id_cliente=?")) {
            ps.setInt(1, cliente.getId_cliente());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Cliente> getAll() {
        List<Cliente> list = new ArrayList();
        try (ResultSet rs = conn
                .createStatement()
                .executeQuery("select * from clientes")) {
            while (rs.next()) {
                list.add(new Cliente(
                    rs.getInt("id_cliente"),
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

    public Cliente getById(int id){
        return getAll()
                        .stream()
                        .filter(cliente->cliente.getId_cliente()==id)
                        .findFirst()
                        .orElse(new Cliente());
    }

    public List<Cliente>getLikeApellido(String apellido){
        if(apellido==null) return new ArrayList();
        return getAll()
                         .stream()
                         .filter(cliente->cliente
                                               .getApellido()
                                               .toLowerCase()
                                               .contains(apellido.toLowerCase()))
                         .toList();
    }
}
