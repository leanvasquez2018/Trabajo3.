package ar.org.centro8.curso.java.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.org.centro8.curso.java.entities.Vendedor;
import ar.org.centro8.curso.java.repositories.VendedorRepository;

@Controller
public class VendedorController {

    private String mensaje="Ingrese un nuevo vendedor!";
    private VendedorRepository vendedorRepository=new VendedorRepository();

    @GetMapping("/vendedores")
    public String getVendedores(Model model, @RequestParam(name="buscar", defaultValue = "") String buscar){
        Vendedor vendedor=new Vendedor();
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("vendedor", vendedor);
        model.addAttribute("likeApellido", vendedorRepository.getLikeApellido(buscar));
        return "vendedores";
    }

    @PostMapping("/vendedoresSave")
    public String vendedoresSave(@ModelAttribute Vendedor vendedor){
        //System.out.println("***************************************");
        //System.out.println(vendedor);
        //System.out.println("***************************************");
        vendedorRepository.save(vendedor);
        if(vendedor.getId_vendedor()>0){
            mensaje="Se guardo con éxito el vendedor con ID: "+vendedor.getId_vendedor();
        }else{
            mensaje="Error! No se pudo guardar el vendedor!";
        }
        return "redirect:vendedores";
    }

    @PostMapping("/vendedoresRemove")
    public String vendedoresRemove(@RequestParam(name="idBorrar", defaultValue = "0", required = false) int idBorrar){
        vendedorRepository.remove(vendedorRepository.getById(idBorrar));
        mensaje = "Se borró el vendedor id: "+idBorrar+"!";   
        return "redirect:vendedores";     
    }
    
}
