package ar.org.centro8.curso.java.entities;

import ar.org.centro8.curso.java.enums.Letra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {
    private int id_factura;
    private Letra letra;
    private int numero;
    private String fecha;
    private double monto;
    private int id_cliente;
    private int legajo_vendedor;
}
