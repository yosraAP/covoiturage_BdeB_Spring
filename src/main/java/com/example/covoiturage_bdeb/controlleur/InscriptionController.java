package com.example.covoiturage_bdeb.controlleur;

import com.example.covoiturage_bdeb.entity.Conducteur;
import com.example.covoiturage_bdeb.entity.Passager;
import com.example.covoiturage_bdeb.service.ConducteurService;
import com.example.covoiturage_bdeb.service.PassagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class InscriptionController {

    private final PassagerService passagerService;
    private final ConducteurService conducteurService;

    public InscriptionController(PassagerService passagerService, ConducteurService conducteurService) {
        this.passagerService = passagerService;
        this.conducteurService = conducteurService;
    }

    @GetMapping("/inscription")
    public String afficherFormulaireInscription(Model model) {
        // Préparer le modèle pour afficher le formulaire d'inscription
        model.addAttribute("passager", new Passager());
        model.addAttribute("conducteur", new Conducteur());
        return "inscription";
    }

    @PostMapping("/inscription/passager")
    public String traiterFormulaireInscriptionPassager(@ModelAttribute Passager passager) {
            passagerService.save(passager);
            return "redirect:/connexion";
    }
    @PostMapping("/inscription/conducteur")
    public String traiterFormulaireInscriptionConducteur(@ModelAttribute Conducteur conducteur) {
            conducteurService.inscrireConducteur(conducteur);
            return "redirect:/connexion";
    }
}