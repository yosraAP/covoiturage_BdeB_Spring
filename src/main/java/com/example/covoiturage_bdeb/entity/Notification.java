package com.example.covoiturage_bdeb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idNotification")
    private Integer idNotification;
    private String idDestinataire;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "idMessagerie_FK",nullable = false)
    private Messagerie idMessagerie;

    public Notification() {
    }

    public Notification(Integer idNotification, String idDestinataire, Messagerie idMessagerie) {
        this.idNotification = idNotification;
        this.idDestinataire = idDestinataire;
        this.idMessagerie = idMessagerie;
    }

    public Notification(String idDestinataire, Messagerie idMessagerie) {
        this.idDestinataire = idDestinataire;
        this.idMessagerie = idMessagerie;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "idNotification=" + idNotification +
                ", idDestinataire='" + idDestinataire + '\'' +
                ", idMessagerie=" + idMessagerie +
                '}';
    }
}
