package ar.org.centro8.curso.java.controllers;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.org.centro8.curso.java.entities.Factura;
import ar.org.centro8.curso.java.enums.Letra;
import ar.org.centro8.curso.java.repositories.ClienteRepository;
import ar.org.centro8.curso.java.repositories.FacturaRepository;
import ar.org.centro8.curso.java.repositories.VendedorRepository;

@Controller
public class FacturaController {

    private String mensaje="Ingrese una nueva factura!";
    private FacturaRepository facturaRepository=new FacturaRepository();
    ClienteRepository clienteRepository = new ClienteRepository();
    VendedorRepository vendedorRepository=new VendedorRepository();

    @GetMapping("/facturas")
    public String getClientes(Model model, @RequestParam(name="buscar", defaultValue = "") String buscar){
        Factura factura = new Factura();
        model.addAttribute("letras", Arrays.asList(Letra.values()));
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("factura", factura);
        model.addAttribute("clientes", clienteRepository.getAll());
        model.addAttribute("vendedores", vendedorRepository.getAll());
        model.addAttribute("facturas", facturaRepository.getAll());
        model.addAttribute("likeFecha", facturaRepository.getLikeFecha(buscar));
        return "facturas";
    }

    @PostMapping("/facturasSave")
    public String articulosSave(@ModelAttribute Factura factura){
        //System.out.println("***************************************");
        //System.out.println(factura);
        //System.out.println("***************************************");
        facturaRepository.save(factura);
        if(factura.getId_factura()>0){
            mensaje="Se guardo con éxito la factura con ID: "+factura.getId_factura();
        }else{
            mensaje="Error! No se pudo guardar la factura!";
        }
        return "redirect:facturas";
    }

    @PostMapping("/facturasRemove")
    public String facturasRemove(@RequestParam(name="idBorrar", defaultValue = "0", required = false) int idBorrar){
        facturaRepository.remove(facturaRepository.getById(idBorrar));
        mensaje = "Se borró la factura id: "+idBorrar+"!";   
        return "redirect:facturas";     
    }
    
}
