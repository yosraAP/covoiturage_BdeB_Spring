package com.example.covoiturage_bdeb.controlleur;

import com.example.covoiturage_bdeb.entity.Conducteur;
import com.example.covoiturage_bdeb.entity.Trajet;
import com.example.covoiturage_bdeb.service.TrajetService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ConducteurController {
    private final TrajetService trajetService;

    public ConducteurController(TrajetService trajetService) {
        this.trajetService = trajetService;
    }

    @GetMapping("/acceuilConducteur")
    public String acceuilConducteur() {
        return "espaceConducteur";
    }

    @GetMapping("/listesTrajets")
    public String listesTrajets(Model model, HttpSession session) {
        // Obtenez l'objet Conducteur complet à partir de la session
        Conducteur conducteur = (Conducteur) session.getAttribute("conducteur");

        // Vérifiez si un Conducteur est connecté
        if (conducteur != null) {
            // Obtenez l'ID du Conducteur à partir de l'objet
            Integer idConducteur = conducteur.getIdConducteur();

            // Utilisez l'ID pour obtenir la liste des trajets
            List<Trajet> trajets = trajetService.getTrajetsByConducteurId(idConducteur);

            // Ajoutez la liste des trajets au modèle
            model.addAttribute("trajets", trajets);
        }

        // Retournez la vue
        return "listesTrajets";
    }

    // Ajouter Trajt
    @GetMapping("/ajouterTrajer")
    public String ajouterTrajet(Model model) {
        // Préparer le modèle pour afficher le formulaire d'ajout Trajet
        Trajet trajet = new Trajet();
        model.addAttribute("trajet",trajet);
        return "ajouterTrajer";
    }

    @PostMapping("/conducteur/ajouterTrajet")
    public String ajouterNouveauTrajet(@ModelAttribute Trajet trajet, HttpSession session, RedirectAttributes redirectAttributes) {
        // Obtenez le conducteur actuellement connecté à partir de la session
        Conducteur conducteur = (Conducteur) session.getAttribute("conducteur");
        trajet.setConducteur(conducteur);

        Date date = trajet.getDateTrajet();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // Vérifiez si la date du trajet est aujourd'hui ou dans le futur
        if (localDate.isBefore(LocalDate.now())) {
            redirectAttributes.addFlashAttribute("error", "La date du trajet doit être aujourd'hui ou dans le futur.");
            return "redirect:/ajouterTrajer";
        }

        // Vérifiez si le prix du trajet est supérieur à 0
        if (trajet.getPrixTrajet() <= 0) {
            redirectAttributes.addFlashAttribute("error", "Le prix du trajet doit être supérieur à 0.");
            return "redirect:/ajouterTrajer";
        }

        // Si les validations passent, sauvegardez le trajet
        try {
            trajetService.save(trajet);
            redirectAttributes.addFlashAttribute("success", "Le trajet a été ajouté avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Une erreur s'est produite lors de l'ajout du trajet.");
        }

        return "redirect:/listesTrajets";
    }

    //Supprimer Trajet
    @PostMapping("/conducteur/supprimerTrajet")
    public String supprimerTrajet(@RequestParam("trajetId") Integer trajetId, HttpSession session, RedirectAttributes redirectAttributes) {
        Conducteur conducteur = (Conducteur) session.getAttribute("conducteur");
        try {
            Trajet trajet = trajetService.getTrajetById(trajetId);
            if (trajet.getConducteur().getIdConducteur().equals(conducteur.getIdConducteur())) {
                trajetService.delete(trajetId);
                redirectAttributes.addFlashAttribute("success", "Le trajet a été supprimé avec succès.");
            } else {
                redirectAttributes.addFlashAttribute("error", "Vous n'êtes pas autorisé à supprimer ce trajet.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Une erreur s'est produite lors de la suppression du trajet.");
        }
        return "redirect:/listesTrajets";
    }

    //Update Trajet
    @GetMapping("/modifierTrajet/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Trajet> trajetOptional = trajetService.findById(id);

        if (!trajetOptional.isPresent()) {
            return "error";
        }

        Trajet trajet = trajetOptional.get();
        model.addAttribute("trajet", trajet);
        return "modifierTrajet";
    }

    @PostMapping("/conducteur/mettreAJourTrajet")
    public String mettreAJourTrajet(@RequestParam("idTrajet") Integer idTrajet, @ModelAttribute Trajet nouveauTrajet, HttpSession session, RedirectAttributes redirectAttributes) {
        Conducteur conducteur = (Conducteur) session.getAttribute("conducteur");
        try {
            Trajet trajetActuel = trajetService.getTrajetById(idTrajet);
            if (trajetActuel.getConducteur().getIdConducteur().equals(conducteur.getIdConducteur())) {
                // Mettre à jour les attributs de trajetActuel avec ceux de nouveauTrajet
                trajetActuel.setDateTrajet(nouveauTrajet.getDateTrajet());
                trajetActuel.setPrixTrajet(nouveauTrajet.getPrixTrajet());
                trajetActuel.setPointDepart(nouveauTrajet.getPointDepart());
                trajetActuel.setPointArrivee(nouveauTrajet.getPointArrivee());
                trajetActuel.setHeureDepart(nouveauTrajet.getHeureDepart());
                trajetActuel.setHeureArrivee(nouveauTrajet.getHeureArrivee());
                trajetService.update(trajetActuel);
                redirectAttributes.addFlashAttribute("success", "Le trajet a été mis à jour avec succès.");
            } else {
                redirectAttributes.addFlashAttribute("error", "Vous n'êtes pas autorisé à mettre à jour ce trajet.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Une erreur s'est produite lors de la mise à jour du trajet.");
        }
        return "redirect:/listesTrajets";
    }
    @GetMapping("/notificationConducteur")
    public String notificationDeConducteur() {
        return "notificationConducteur";
    }
}
