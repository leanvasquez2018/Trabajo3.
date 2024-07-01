package ar.org.centro8.curso.java.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.org.centro8.curso.java.entities.Articulo;
import ar.org.centro8.curso.java.repositories.ArticuloRepository;

@Controller
public class ArticuloController {

    private String mensaje="Ingrese un nuevo articulo!";
    private ArticuloRepository articuloRepository=new ArticuloRepository();

    @GetMapping("/articulos")
    public String getClientes(Model model, @RequestParam(name="buscar", defaultValue = "") String buscar){
        Articulo articulo=new Articulo();
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("articulo", articulo);
        model.addAttribute("likeProducto", articuloRepository.getLikeProducto(buscar));
        return "articulos";
    }

    @PostMapping("/articulosSave")
    public String articulosSave(@ModelAttribute Articulo articulo){
        //System.out.println("***************************************");
        //System.out.println(articulo);
        //System.out.println("***************************************");
        articuloRepository.save(articulo);
        if(articulo.getId_articulo()>0){
            mensaje="Se guardo con éxito el articulo con ID: "+articulo.getId_articulo();
        }else{
            mensaje="Error! No se pudo guardar el articulo!";
        }
        return "redirect:articulos";
    }

    @PostMapping("/articulosRemove")
    public String articulosRemove(@RequestParam(name="idBorrar", defaultValue = "0", required = false) int idBorrar){
        articuloRepository.remove(articuloRepository.getById(idBorrar));
        mensaje = "Se borró el articulo id: "+idBorrar+"!";   
        return "redirect:articulos";     
    }
    
}
