package ar.org.centro8.curso.java.entities;

import ar.org.centro8.curso.java.enums.Letra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    private int id_venta;
    private int id_factura;
    private int id_articulo;
    private Letra letra;
    private int numero;
    private int codigo;
    private int cantidad;
}
