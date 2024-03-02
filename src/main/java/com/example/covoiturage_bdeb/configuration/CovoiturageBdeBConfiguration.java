package com.example.covoiturage_bdeb.configuration;

import com.example.covoiturage_bdeb.entity.Conducteur;
import com.example.covoiturage_bdeb.entity.Passager;
import com.example.covoiturage_bdeb.entity.Trajet;
import com.example.covoiturage_bdeb.repository.ConducteurRepository;
import com.example.covoiturage_bdeb.repository.PassagerRepository;
import com.example.covoiturage_bdeb.repository.TrajetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Configuration
public class CovoiturageBdeBConfiguration {

    private final TrajetRepository trajetRepository;
    private final ConducteurRepository conducteurRepository;
    private final PassagerRepository passagerRepository;

    public CovoiturageBdeBConfiguration(TrajetRepository trajetRepository, ConducteurRepository conducteurRepository, PassagerRepository passagerRepository) {
        this.trajetRepository = trajetRepository;
        this.conducteurRepository = conducteurRepository;
        this.passagerRepository = passagerRepository;
    }

    //@Bean
    CommandLineRunner ajouterQuatresConducteurs() {
        return args -> {
            Conducteur conducteur1 = new Conducteur("conducteur", "1", "5184 Rue Desmarteau", "5145492221", "adresse1@hotmail.fr", "1234", "conducteur", "Audi A4", 3, "audiA4.png", "Hou150891");
            Conducteur conducteur2 = new Conducteur("conducteur", "2", "Boulevard Laval, Laval-des-rapides, Laval, QC", "5145492221", "adresse1@hotmail.fr", "1234", "conducteur", "bmwX5", 3, "bmwX5.png", "AFS202306");
            Conducteur conducteur3 = new Conducteur("conducteur", "3", "Jean Talon Market, Avenue Henri-Julien, Montréal, QC", "5145492221", "adresse1@hotmail.fr", "1234", "conducteur", "Range Rover", 4, "rangRover.png", "ROG010523");
            Conducteur conducteur4 = new Conducteur("conducteur", "4", "Boulevard Langelier, Saint-Léonard, QC", "5145492221", "adresse1@hotmail.fr", "1234", "conducteur", "Toyota", 3, "toyota.png", "NGC202306");
            conducteurRepository.saveAll(List.of(conducteur1, conducteur2, conducteur3, conducteur4));
        };
    }

    //@Bean
    CommandLineRunner ajouterpassager() {
        return args -> {
            Passager passager1 = new Passager("Alain", "Flouclair", "1000 Verdun, Montreal", "5144385569", "flouclair@hotmail.fr", "password", "passager");
            Passager passager2 = new Passager("Massant", "John", "123 Marseille, Montreal", "4386583333", "john@hotmail.fr", "password", "passager");
            passagerRepository.saveAll(List.of(passager1, passager2));
        };
    }

    //@Bean
    CommandLineRunner ajouterListeTrajetsConducteur1() {
        return args -> {
            Integer id_conducteur = 1;
            Optional<Conducteur> conducteurOptional = conducteurRepository.findById(id_conducteur);
            Conducteur conducteur = conducteurOptional.orElse(null);

            if (conducteur != null) {
                // Le Format de Date
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                // Les date de trajet
                Date dateTrajet1 = format.parse("2024-03-18");
                Date dateTrajet2 = format.parse("2024-03-19");
                Date dateTrajet3 = format.parse("2024-03-20");

                // Ajouter Trajet
                Trajet trajet1 = new Trajet(dateTrajet1, "College de bois de boulogne", "Metro Montmorency Laval, Boul. le Corbusier, Laval, Quebec H7N 0A5", "7:30", "7:50", 15, 4, conducteur);
                Trajet trajet2 = new Trajet(dateTrajet1, "Laval, Quebec H7N 0A5", "College de bois de boulogne", "17:30", "17:50", 15, 4, conducteur);
                Trajet trajet3 = new Trajet(dateTrajet2, "College de bois de boulogne", "Honoré-Beaugrand, Montréal, QC H1L 1A8", "7:20", "7:55", 20, 3, conducteur);
                Trajet trajet4 = new Trajet(dateTrajet2, "Honoré-Beaugrand, Montréal, QC H1L 1A8", "College de bois de boulogne", "17:20", "17:55", 20, 3, conducteur);
                Trajet trajet5 = new Trajet(dateTrajet3, "College de bois de boulogne", "Anjou-sur-le-Lac, Anjou, Quebec H1J 2Z2", "7:20", "7:55", 20, 3, conducteur);
                Trajet trajet6 = new Trajet(dateTrajet3, "Anjou-sur-le-Lac, Anjou, Quebec H1J 2Z2", "College de bois de boulogne", "17:20", "17:55", 20, 3, conducteur);

                trajetRepository.saveAll(List.of(trajet1, trajet2, trajet3, trajet4, trajet5, trajet6));
            }
        };

    }

    //@Bean
    CommandLineRunner ajouterListeTrajetsConducteur2() {
        return args -> {
            Integer id_conducteur = 2;
            Optional<Conducteur> conducteurOptional = conducteurRepository.findById(id_conducteur);
            Conducteur conducteur = conducteurOptional.orElse(null);

            if (conducteur != null) {
                // Le Format de Date
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                // Les date de trajet
                Date dateTrajet1 = format.parse("2024-03-11");
                Date dateTrajet2 = format.parse("2024-03-12");

                // Ajouter Trajet
                Trajet trajet1 = new Trajet(dateTrajet1, "College de bois de boulogne", "Metro Montmorency Laval, Boul. le Corbusier, Laval, Quebec H7N 0A5", "7:30", "7:50", 15, 4, conducteur);
                Trajet trajet2 = new Trajet(dateTrajet1, "Laval, Quebec H7N 0A5", "College de bois de boulogne", "17:30", "17:50", 15, 4, conducteur);
                Trajet trajet3 = new Trajet(dateTrajet2, "Verdun, 4520 Rue de Verdun, Verdun, QC H4G 1M3", "College de bois de boulogne", "11:20", "12:00", 20, 3, conducteur);

                trajetRepository.saveAll(List.of(trajet1, trajet2, trajet3));
            }
        };

    }

    //@Bean
    CommandLineRunner commandLineRunnerpourConducteur3() {
        return args -> {
            Integer id_conducteur = 3;
            Optional<Conducteur> conducteurOptional = conducteurRepository.findById(id_conducteur);
            Conducteur conducteur = conducteurOptional.orElse(null);

            if (conducteur != null) {
                // Le Format de Date
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                // Les date de trajet
                Date dateTrajet1 = format.parse("2024-03-13");
                Date dateTrajet2 = format.parse("2024-03-14");

                // Ajouter Trajet
                Trajet trajet1 = new Trajet(dateTrajet1, "College de bois de boulogne", "Rue de Marseille, Montréal, QC", "7:20", "7:50", 20, 3, conducteur);
                Trajet trajet2 = new Trajet(dateTrajet1, "Rue de Marseille, Montréal, QC", "College de bois de boulogne", "17:30", "17:50", 15, 4, conducteur);
                Trajet trajet3 = new Trajet(dateTrajet2, "College de bois de boulogne", "Verdun, 4520 Rue de Verdun, Verdun, QC H4G 1M3", "7:35", "7:50", 10, 3, conducteur);

                trajetRepository.saveAll(List.of(trajet1, trajet2, trajet3));
            }
        };

    }

    //@Bean
    CommandLineRunner commandLineRunnerpourConducteur4() {
        return args -> {
            Integer id_conducteur = 4;
            Optional<Conducteur> conducteurOptional = conducteurRepository.findById(id_conducteur);
            Conducteur conducteur = conducteurOptional.orElse(null);

            if (conducteur != null) {
                // Le Format de Date
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                // Les date de trajet
                Date dateTrajet1 = format.parse("2024-03-15");
                Date dateTrajet2 = format.parse("2024-03-16");

                // Ajouter Trajet
                Trajet trajet1 = new Trajet(dateTrajet1, "College de bois de boulogne", "Ch Tiffin, Saint-Lambert, QC", "7:10", "7:50", 25, 3, conducteur);
                Trajet trajet2 = new Trajet(dateTrajet1, "Ch Tiffin, Saint-Lambert, QC", "College de bois de boulogne", "17:10", "17:50", 15, 4, conducteur);
                Trajet trajet3 = new Trajet(dateTrajet2, "College de bois de boulogne", "Drummondville, Québec", "6:35", "7:50", 10, 3, conducteur);

                trajetRepository.saveAll(List.of(trajet1, trajet2, trajet3));
            }
        };

    }

}

