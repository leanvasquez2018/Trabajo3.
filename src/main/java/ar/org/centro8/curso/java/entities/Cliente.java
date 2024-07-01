package ar.org.centro8.curso.java.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private int id_cliente;
    private String nombre;
    private String apellido;
    private int dni;
    private int tel_celular;
    private String email;
}
