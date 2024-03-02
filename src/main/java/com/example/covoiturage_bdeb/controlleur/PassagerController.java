package com.example.covoiturage_bdeb.controlleur;

import com.example.covoiturage_bdeb.EmailSender.EmailSenderService;
import com.example.covoiturage_bdeb.entity.*;
import com.example.covoiturage_bdeb.repository.ConducteurRepository;
import com.example.covoiturage_bdeb.repository.PassagerRepository;
import com.example.covoiturage_bdeb.repository.ReservationRepository;
import com.example.covoiturage_bdeb.repository.TrajetRepository;
import com.example.covoiturage_bdeb.service.AvisService;
import com.example.covoiturage_bdeb.service.TrajetService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Controller
public class PassagerController {
    @Autowired
    private final ConducteurRepository conducteurRepository;
    @Autowired
    private final TrajetRepository trajetRepository;
    private final TrajetService trajetService;
    @Autowired
    private final PassagerRepository passagerRepository;
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    EmailSenderService senderService;
    @Autowired
    private AvisService avisService;

    public PassagerController(ConducteurRepository conducteurRepository, TrajetRepository trajetRepository, TrajetService trajetService, PassagerRepository passagerRepository, ReservationRepository reservationRepository) {
        this.conducteurRepository = conducteurRepository;
        this.trajetRepository = trajetRepository;
        this.trajetService = trajetService;
        this.passagerRepository = passagerRepository;
        this.reservationRepository = reservationRepository;
    }


    @GetMapping("/espacePassager")
    public String espacePassager(HttpSession session, Model model) {
        List<Trajet> listTrajets = trajetRepository.findAll();
        // Récupérer les informations de l'utilisateur à partir de l'objet HttpSession
        Passager passager = (Passager) session.getAttribute("passager");
        model.addAttribute("passager", passager);
        model.addAttribute("trajet", new Trajet());
        model.addAttribute("trajets", listTrajets);

        return "espacePassager";
    }

    //Cherechet Trajet

    @GetMapping("/chercherTrajet")
    public String chercherTrajet(Model model) {
        List<Trajet> listPointDepart = trajetRepository.findAll();
        List<Trajet> listPointArrivee = trajetRepository.findAll();
        model.addAttribute("listPointDepart", listPointDepart);
        model.addAttribute("listPointArrivee", listPointArrivee);
        model.addAttribute("trajet", new Trajet());
        model.addAttribute("trajets", listPointDepart);
        return "chercherTrajet";
    }


    @RequestMapping("/chercherTrajetparPointDeparPointArrivee")
    public String chercherTrajetByPointDepart(@RequestParam(name = "pointDepart", required = false) String pointDepart, @RequestParam(name = "pointArrivee", required = false) String pointArrivee, @RequestParam(name = "heureDepart", required = false) String heureDepart, @RequestParam(name = "chercher", required = true) String chercher, Model model) {

        List<Trajet> trajets = null;
        Trajet trajet = new Trajet();
        List<Trajet> listPointDepart = trajetService.findAll();
        model.addAttribute("listPointDepart", listPointDepart);
        List<Trajet> listPointArrivee = trajetService.findAll();
        model.addAttribute("listPointArrivee", listPointArrivee);


        if (chercher.equals("1")) {  // chercherTrajetByHeureDepart
            trajets = trajetService.chercherTrajetByHeureDepart(heureDepart);
        } else if (chercher.equals("2")) {  // chercherTrajetByPointDepart
            trajets = trajetService.chercherUnTrajet(pointDepart, pointArrivee);
        }


        model.addAttribute("trajet", trajet);
        model.addAttribute("trajets", trajets);

        model.addAttribute("trajet", trajet);
        model.addAttribute("trajets", trajets);
        return "chercherTrajet";
    }

    //Reservation Trajet
    @GetMapping("/reservationTrajet/{idTrajet}")
    public String showForm(@PathVariable("idTrajet") Integer idTrajet, Model model, HttpSession session) {
        Trajet trajet = trajetRepository.findById(idTrajet).orElseThrow(() -> new RuntimeException("Trajet not found"));
        Conducteur conducteur = trajet.getConducteur();
        // récupérer le passager connecté (à adapter en fonction de votre logique d'authentification)

        Passager passager = (Passager) session.getAttribute("passager");
        model.addAttribute("passager", passager);
        Reservation reservation = new Reservation();
        ReservationId id = new ReservationId();
        id.setIdPassager(passager);
        id.setIdTrajet(trajet);
        reservation.setIdreservation(id);

        model.addAttribute("trajet", trajet);
        model.addAttribute("conducteur", conducteur);
        model.addAttribute("reservation", reservation);

        return "reservationTrajet";
    }

    @PostMapping("/reservation/process")
    public String processReservation(@ModelAttribute("reservation") Reservation reservation,
                                     RedirectAttributes redirectAttributes) {
        Integer reservationId = reservation.getIdreservation().getIdPassager().getIdPassager();
        Integer trajetId = reservation.getIdreservation().getIdTrajet().getIdTrajet();

        // Récupérer le trajet depuis la base de données
        Trajet trajet = trajetRepository.findById(trajetId)
                .orElseThrow(() -> new RuntimeException("Trajet not found"));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime trajetDate = trajet.getDateTrajet().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        LocalDateTime reservationDate = reservation.getDateReservation().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        if (reservationDate.isAfter(now) && trajetDate.isAfter(now)) {
            // Calculer le montant total à payer en fonction du nombre de places réservées
            int nb_PlaceTotal = reservation.getNb_PlaceTotal();
            int nb_Placedisponible = trajet.getNb_Placedisponible();

            if (nb_Placedisponible == 0) {
                redirectAttributes.addFlashAttribute("error", "Désolé, les places pour ce trajet sont déjà toutes réservées.");
                return "redirect:/chercherTrajet";
            } else if (nb_PlaceTotal > nb_Placedisponible) {
                redirectAttributes.addFlashAttribute("error", "Le nombre de places demandées dépasse le nombre de places disponibles.");
                return "redirect:/chercherTrajet";
            }

            double prixTrajet = trajet.getPrixTrajet();
            float montantAPayer = (float) (prixTrajet / nb_PlaceTotal);
            reservation.setMontant_a_payer(montantAPayer);

            // Mettre à jour le nombre de places disponibles du trajet
            trajet.setNb_Placedisponible(nb_Placedisponible - nb_PlaceTotal);
            trajetRepository.save(trajet);

            // Sauvegarder la réservation
            reservation.getIdreservation().setIdTrajet(trajet);
            reservationRepository.save(reservation);
            senderService.sendEmail("houas_yora@hotmail.fr","confirmation Reservation","Nous sommes ravis de vous informer qu'un passager a réservé l'un de vos trajets disponibles. Cette réservation confirme l'intérêt d'un passager à voyager avec vous.");
            redirectAttributes.addFlashAttribute("success", "La réservation a été effectuée avec succès.");
            return "redirect:/historiqueReservation";
        } else {
            // Les conditions de réservation ne sont pas satisfaites
            redirectAttributes.addFlashAttribute("error", "La réservation est autorisée uniquement avant 24 heures.");
            return "redirect:/chercherTrajet";
        }
    }

    @GetMapping("/historiqueReservation")
    public String showReservationHistory(Model model, HttpSession session) {
        Passager passager = (Passager) session.getAttribute("passager");
        List<Reservation> reservations = reservationRepository.findByIdreservationIdPassager(passager);
        model.addAttribute("passager", passager);
        model.addAttribute("reservations", reservations);
        return "historiqueReservation";
    }

    @GetMapping("/reservation/delete/{passagerId}/{trajetId}")
    public String deleteReservation(@PathVariable("passagerId") Integer passagerId, @PathVariable("trajetId") Integer trajetId, RedirectAttributes redirectAttributes) {
        Passager passager = passagerRepository.findById(passagerId).orElseThrow(() -> new RuntimeException("Passager not found"));
        Trajet trajet = trajetRepository.findById(trajetId).orElseThrow(() -> new RuntimeException("Trajet not found"));

        ReservationId reservationId = new ReservationId(passager, trajet);
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("Reservation not found"));

        LocalDateTime reservationDate = reservation.getDateReservation().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        LocalDateTime trajetDate = trajet.getDateTrajet().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        LocalDateTime currentDate = LocalDateTime.now();

        if (reservationDate.isAfter(trajetDate)) {
            // La date de réservation dépasse la date de trajet, donc supprimer la réservation
            reservationRepository.delete(reservation);
        } else {
            // La date de réservation est avant la date de trajet, donc mettre à jour les valeurs du trajet et supprimer la réservation
            int nbPlaceTotal = reservation.getNb_PlaceTotal();
            int nbPlaceDisponible = trajet.getNb_Placedisponible();
            trajet.setNb_Placedisponible(nbPlaceDisponible + nbPlaceTotal);
            trajetRepository.save(trajet);
            reservationRepository.delete(reservation);
        }

        redirectAttributes.addFlashAttribute("success", "La réservation a été supprimée avec succès.");
        return "redirect:/historiqueReservation";
    }
    //Commentaire
    @PostMapping("/commentaire/{idPassager}/{idTrajet}")
    public String ajouterCommentaire(@PathVariable Integer idPassager, @PathVariable Integer idTrajet, @RequestParam(name="commentaire" ,required = false) String commentaire, @ModelAttribute Avis avis, RedirectAttributes re){
        AvisId avisId=new AvisId(idPassager,idTrajet);
        avis.setIdAvis(avisId);
        avisService.save(avis);

        re.addFlashAttribute("message", "Cette Commentaire a Insere avec succès");
        return "redirect:/espacePassager";

    }
    // envoyerEmail
    @RequestMapping("/envoyerEmail")
    public String envoyerEmail( @RequestParam(name="toEmail" ) String toEmail, @RequestParam(name="subject") String subject, @RequestParam(name="body" ) String body,RedirectAttributes re ){
        senderService.sendEmail(toEmail,subject,body);
        re.addFlashAttribute("message", "E-mail envoyè avec succès");
        return "redirect:/espacePassager";
    }

    @GetMapping("/notificationPassager")
    public String notificationPassager() {
        return "notificationPassager";
    }
}
