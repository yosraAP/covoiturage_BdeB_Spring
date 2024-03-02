package com.example.covoiturage_bdeb.controlleur;

import com.example.covoiturage_bdeb.entity.Conducteur;
import com.example.covoiturage_bdeb.entity.Passager;
import com.example.covoiturage_bdeb.entity.Trajet;
import com.example.covoiturage_bdeb.repository.ConducteurRepository;
import com.example.covoiturage_bdeb.repository.PassagerRepository;
import com.example.covoiturage_bdeb.repository.TrajetRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ConnexionController {

    @Autowired
    private ConducteurRepository conducteurRepository;

    @Autowired
    private PassagerRepository passagerRepository;

    @Autowired
    private TrajetRepository trajetRepository;
    @GetMapping("/connexion")
    public String connexionForm() {
        return "connexion";
    }

    @GetMapping("/espaceConducteur")
    public String espaceConducteur() {
        return "espaceConducteur";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String email, @RequestParam String password,
                              @RequestParam String typeCompte, HttpSession session, RedirectAttributes re ) {
        ModelAndView modelAndView;

        switch(typeCompte) {
            case "conducteur":
                Conducteur conducteur = conducteurRepository.findByEmailConducteur(email);

                if(conducteur != null && conducteur.getPasswordConducteur().equals(password)) {
                    session.setAttribute("conducteur", conducteur);
                    modelAndView = new ModelAndView("redirect:/espaceConducteur");
                    re.addFlashAttribute("success", "Bienvenue conducteur sur Covoiturage_BdeB");

                } else {
                    // Afficher un message d'erreur approprié
                    modelAndView = new ModelAndView("redirect:/connexion");
                    re.addFlashAttribute("error", "Invalid Email or password !");
                }

                break;
            case "passager":
                Passager passager = passagerRepository.findByEmailPassager(email);

                if(passager != null && passager.getPasswordPassager().equals(password)) {
                    session.setAttribute("passager", passager);
                    modelAndView = new ModelAndView("redirect:/espacePassager");
                    re.addFlashAttribute("success", "Bienvenue passager sur Covoiturage_BdeB !");

                } else {
                    // Afficher un message d'erreur approprié
                    modelAndView = new ModelAndView("redirect:/connexion");
                    re.addFlashAttribute("error", "Invalid Email or password !");
                }

                break;
            default:
                // Afficher un message d'erreur approprié
                modelAndView = new ModelAndView("redirect:/connexion");
                re.addFlashAttribute("error", "Type Compte invalide !");
        }
        return modelAndView;
    }
}
