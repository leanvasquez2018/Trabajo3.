package ar.org.centro8.curso.java.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.org.centro8.curso.java.entities.Cliente;
import ar.org.centro8.curso.java.repositories.ClienteRepository;

@Controller
public class ClienteController {

    private String mensaje="Ingrese un nuevo cliente!";
    private ClienteRepository clienteRepository=new ClienteRepository();

    @GetMapping("/clientes")
    public String getClientes(Model model, @RequestParam(name="buscar", defaultValue = "") String buscar){
        Cliente cliente=new Cliente();
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("cliente", cliente);
        model.addAttribute("likeApellido", clienteRepository.getLikeApellido(buscar));
        return "clientes";
    }

    @PostMapping("/clientesSave")
    public String clientesSave(@ModelAttribute Cliente cliente){
        //System.out.println("***************************************");
        //System.out.println(cliente);
        //System.out.println("***************************************");
        clienteRepository.save(cliente);
        if(cliente.getId_cliente()>0){
            mensaje="Se guardo con éxito el cliente con ID: "+cliente.getId_cliente();
        }else{
            mensaje="Error! No se pudo guardar el cliente!";
        }
        return "redirect:clientes";
    }

    @PostMapping("/clientesRemove")
    public String clientesRemove(@RequestParam(name="idBorrar", defaultValue = "0", required = false) int idBorrar){
        clienteRepository.remove(clienteRepository.getById(idBorrar));
        mensaje = "Se borró el cliente id: "+idBorrar+"!";   
        return "redirect:clientes";     
    }
    
}
