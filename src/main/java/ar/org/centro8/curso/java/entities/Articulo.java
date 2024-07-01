package ar.org.centro8.curso.java.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Articulo {
    private int id_articulo;
    private int codigo;
    private String producto;
    private String color;
    private int stock;
    private int stock_minimo;
    private int stock_maximo;
}
