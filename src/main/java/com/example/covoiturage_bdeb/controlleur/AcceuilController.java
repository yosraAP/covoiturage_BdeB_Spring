package com.example.covoiturage_bdeb.controlleur;
import com.example.covoiturage_bdeb.entity.Passager;
import com.example.covoiturage_bdeb.entity.Trajet;
import com.example.covoiturage_bdeb.service.TrajetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AcceuilController {

    @GetMapping("/")
    public String acceuil( ){
        return "index";
    }

}
