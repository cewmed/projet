package com.ism.views;

import java.util.Random;

public class EmploiDuTempsGenerator {
    private static final String[] joursSemaine = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};

    public static void main(String[] args) {
        // Tableaux de classes, modules et professeurs
        String[] classes = {"Classe A", "Classe B", "Classe C"};
        String[] modules = {"Mathématiques", "Français", "Histoire", "Physique", "Chimie"};
        String[] professeurs = {"Professeur 1", "Professeur 2", "Professeur 3", "Professeur 4", "Professeur 5"};

        // Génération de l'emploi du temps
        String[][] emploiDuTemps = generateEmploiDuTemps(classes[0], modules, professeurs);

        // Affichage de l'emploi du temps
        printEmploiDuTemps(emploiDuTemps);
    }

    public static String[][] generateEmploiDuTemps(String classe, String[] modules, String[] professeurs) {
        Random random = new Random();
        int jours = joursSemaine.length;
        int plagesHoraires = 8; // Nombre de plages horaires par jour
        String[][] emploiDuTemps = new String[jours][plagesHoraires];

        for (int jour = 0; jour < jours; jour++) {
            for (int plage = 0; plage < plagesHoraires; plage++) {
                int moduleIndex = random.nextInt(modules.length);
                int professeurIndex = random.nextInt(professeurs.length);
                String module = modules[moduleIndex];
                String professeur = professeurs[professeurIndex];
                emploiDuTemps[jour][plage] = classe + " - " + module + " (Professeur: " + professeur + ")";
            }
        }

        return emploiDuTemps;
    }

    public static void printEmploiDuTemps(String[][] emploiDuTemps) {
        int jours = joursSemaine.length;
        int plagesHoraires = 8;

        for (int jour = 0; jour < jours; jour++) {
            System.out.println(joursSemaine[jour] + ":");
            for (int plage = 0; plage < plagesHoraires; plage++) {
                System.out.println("Plage horaire " + (plage + 1) + ": " + emploiDuTemps[jour][plage]);
            }
            System.out.println();
        }
    }
}