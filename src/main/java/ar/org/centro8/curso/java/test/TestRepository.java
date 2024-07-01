package ar.org.centro8.curso.java.test;

import ar.org.centro8.curso.java.entities.Articulo;
import ar.org.centro8.curso.java.entities.Cliente;
import ar.org.centro8.curso.java.entities.Factura;
import ar.org.centro8.curso.java.entities.Vendedor;
import ar.org.centro8.curso.java.entities.Venta;
import ar.org.centro8.curso.java.enums.Letra;
import ar.org.centro8.curso.java.repositories.ArticuloRepository;
import ar.org.centro8.curso.java.repositories.ClienteRepository;
import ar.org.centro8.curso.java.repositories.FacturaRepository;
import ar.org.centro8.curso.java.repositories.VendedorRepository;
import ar.org.centro8.curso.java.repositories.VentaRepository;

public class TestRepository {
    public static void main(String[] args) {
        ClienteRepository clienteRepository=new ClienteRepository();

        System.out.println("-- Test Cliente Repository --");
        System.out.println("-- Método .save() --");
        Cliente cliente=new Cliente(
                                        0, 
                                        "Jorge", 
                                        "Moyano", 
                                        33568948, 
                                        1189574935, 
                                        "jorge_moyano@hotmail.com");
        clienteRepository.save(cliente);
        System.out.println(cliente);

        System.out.println("-- Método .getById() --");
        System.out.println(clienteRepository.getById(3));

        System.out.println("-- Método .remove() --");
        clienteRepository.remove(clienteRepository.getById(6));

        System.out.println("-- Método .getLikeApellido() --");
        clienteRepository.getLikeApellido("ez").forEach(System.out::println);

        System.out.println("-- Método .getAll() --");
        clienteRepository.getAll().forEach(System.out::println);

        System.out.println("========================================");

        System.out.println("-- Test Vendedor Repository --");
        VendedorRepository vendedorRepository=new VendedorRepository();

        System.out.println("-- Método .save() --");
        Vendedor vendedor=new Vendedor(
                                           0, 
                                           "Sergio", 
                                           "Ramirez", 
                                           36874923, 
                                           1147289469, 
                                           "sergio_ramirez@hotmail.com");
        vendedorRepository.save(vendedor);
        System.out.println(vendedor);

        System.out.println("-- Método .getById() --");
        System.out.println(vendedorRepository.getById(4));

        System.out.println("-- Método .remove() --");
        vendedorRepository.remove(vendedorRepository.getById(8));

        System.out.println("-- Método .getLikeApellido() --");
        vendedorRepository.getLikeApellido("ez").forEach(System.out::println);

        System.out.println("-- Método .getAll() --");
        vendedorRepository.getAll().forEach(System.out::println);

        System.out.println("========================================");

        System.out.println("-- Test Articulo Repository --");
        ArticuloRepository articuloRepository=new ArticuloRepository();

        System.out.println("-- Método .save() --");
        Articulo articulo=new Articulo(
                                           0,
                                           14, 
                                           "Micrófono", 
                                           "Azul", 
                                           36, 
                                           4, 
                                           100);
        articuloRepository.save(articulo);
        System.out.println(articulo);

        System.out.println("-- Método .getById() --");
        System.out.println(articuloRepository.getById(5));

        System.out.println("-- Método .remove() --");
        articuloRepository.remove(articuloRepository.getById(10));

        System.out.println("-- Método .getLikeProducto() --");
        articuloRepository.getLikeProducto("Tarjeta de Memoria").forEach(System.out::println);

        System.out.println("-- Método .getAll() --");
        articuloRepository.getAll().forEach(System.out::println);

        System.out.println("========================================");

        System.out.println("-- Test Factura Repository --");
        FacturaRepository facturaRepository=new FacturaRepository();

        System.out.println("-- Método .save() --");
        Factura factura=new Factura(
                                           0,
                                           Letra.A, 
                                           2, 
                                           "2024-02-18", 
                                           19000.00, 
                                           3, 
                                           8);
        facturaRepository.save(factura);
        System.out.println(factura);

        System.out.println("-- Método .getById() --");
        System.out.println(facturaRepository.getById(1));

        System.out.println("-- Método .remove() --");
        facturaRepository.remove(facturaRepository.getById(3));

        System.out.println("-- Método .getLikeFecha() --");
        facturaRepository.getLikeFecha("2024-03-26").forEach(System.out::println);

        System.out.println("-- Método .getAll() --");
        facturaRepository.getAll().forEach(System.out::println);

        System.out.println("========================================");

        System.out.println("-- Test Venta Repository --");
        VentaRepository ventaRepository=new VentaRepository();
        
        System.out.println("-- Método .save() --");
        Venta venta=new Venta(
                                           0,
                                           2,
                                           4,
                                           Letra.B, 
                                           5, 
                                           3, 
                                           4);
        ventaRepository.save(venta);
        System.out.println(venta);

        System.out.println("-- Método .getById() --");
        System.out.println(ventaRepository.getById(2));

        System.out.println("-- Método .remove() --");
        ventaRepository.remove(ventaRepository.getById(4));

        System.out.println("-- Método .getAll() --");
        ventaRepository.getAll().forEach(System.out::println);


    }
}
