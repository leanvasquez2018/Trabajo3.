package ar.org.centro8.curso.java.controllers;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.org.centro8.curso.java.entities.Venta;
import ar.org.centro8.curso.java.enums.Letra;
import ar.org.centro8.curso.java.repositories.ArticuloRepository;
import ar.org.centro8.curso.java.repositories.FacturaRepository;
import ar.org.centro8.curso.java.repositories.VentaRepository;

@Controller
public class VentaController {

    private String mensaje="Ingrese una nueva venta!";
    private VentaRepository ventaRepository=new VentaRepository();
    FacturaRepository facturaRepository=new FacturaRepository();
    ArticuloRepository articuloRepository=new ArticuloRepository();

    @GetMapping("/ventas")
    public String getClientes(Model model, @RequestParam(name="buscar", defaultValue = "") String buscar){
        Venta venta = new Venta();
        model.addAttribute("letras", Arrays.asList(Letra.values()));
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("venta", venta);
        model.addAttribute("ventas", ventaRepository.getAll());
        model.addAttribute("articulos", articuloRepository.getAll());
        model.addAttribute("facturas", facturaRepository.getAll());
        return "ventas";
    }

    @PostMapping("/ventasSave")
    public String ventasSave(@ModelAttribute Venta venta){
        //System.out.println("***************************************");
        //System.out.println(venta);
        //System.out.println("***************************************");
        ventaRepository.save(venta);
        if(venta.getId_venta()>0){
            mensaje="Se guardo con éxito la venta con ID: "+venta.getId_venta();
        }else{
            mensaje="Error! No se pudo guardar la venta!";
        }
        return "redirect:ventas";
    }

    @PostMapping("/ventasRemove")
    public String ventasRemove(@RequestParam(name="idBorrar", defaultValue = "0", required = false) int idBorrar){
        ventaRepository.remove(ventaRepository.getById(idBorrar));
        mensaje = "Se borró la venta id: "+idBorrar+"!";   
        return "redirect:ventas";     
    }
    
}
