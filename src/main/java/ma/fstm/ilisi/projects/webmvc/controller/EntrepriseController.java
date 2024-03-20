package ma.fstm.ilisi.projects.webmvc.controller;

import ma.fstm.ilisi.projects.webmvc.dto.EntrepriseDTO;
import ma.fstm.ilisi.projects.webmvc.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins="*")
public class EntrepriseController {
    @Autowired
    private EntrepriseService entrepriseservice;

    @GetMapping("/")
    public String getAllEntreprises(Model model) {
        model.addAttribute("entreprises", entrepriseservice.getAllEntreprise());
        return "index";
    }

    @GetMapping("/entreprises/new")
    public String NouvelEntreprise(Model model) {
        model.addAttribute("entreprisedto",new EntrepriseDTO());
        return "new_entreprise";
    }

    @PostMapping("/entreprises/save")
    public String addEntreprise(@ModelAttribute EntrepriseDTO dto) {
        entrepriseservice.saveEntrepriseDTO(dto);
        return "redirect:/";
    }

    @GetMapping("/entreprises/edit/{id}")
    public String updateEntreprise(@PathVariable(value = "") int id, Model model) {
        model.addAttribute("entreprisedto",entrepriseservice.getEntreprise(id));
        return "update_entreprise";
    }

    @GetMapping("/entreprises/delete/{id}")
    public String deleteEntreprise(@PathVariable(value = "") int id) {
        EntrepriseDTO dto=entrepriseservice.getEntreprise(id);
        entrepriseservice.deleteEntrepriseDTO(dto);
        return "redirect:/";
    }

}
