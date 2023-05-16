package com.example.money_beast.wallet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

public class Rahakott {

    private List<Tehing> tehinguInfo;

    public List<Tehing> getTehinguInfo() {
        return tehinguInfo;
    }

    public Rahakott() {
        tehinguInfo = new ArrayList<Tehing>();
    }


    //Loeb failist tehingud ja lisab need listi
    public void loeFailist(String failinimi) throws IOException {
        File fail = new File(failinimi);
        Scanner scanner = new Scanner(fail);


        while (scanner.hasNextLine()) {
            String rida = scanner.nextLine();
            String[] osad = rida.split(",");

            double maksumus = Double.parseDouble(osad[0]);
            String kategooria = osad[1];
            boolean Sissetulek = Boolean.parseBoolean(osad[2]);
            LocalDate kuupäev = LocalDate.parse(osad[3]);

            tehinguInfo.add(new Tehing(maksumus, kategooria, Sissetulek, kuupäev));
        }

        scanner.close();

    }

    //Salvestab listi faili (kirjutab üle olemasoleva faili)
    public void salvestaFaili(String failinimi) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(failinimi));
            for (Tehing tehing : tehinguInfo) {
                writer.println(tehing.faili());
            }
            writer.close();
    }

    //Lisab tehingu
    public void lisaTehing(Tehing tehing) {
        tehinguInfo.add(tehing);
    }

    public void eemaldaTehing(Tehing tehing) {
        tehinguInfo.remove(tehing);
    }

    //Näitab kõiki tehinguid sisestatud ajavahemikus
    public void näitaTehingud(LocalDate algusAeg, LocalDate lõppAeg) {
        for (Tehing tehing : tehinguInfo) {
            LocalDate kuupäev = tehing.getKuupäev();
            if (kuupäev.isAfter(algusAeg) && kuupäev.isBefore(lõppAeg)) {
                System.out.println(tehing);
            }
        }
        System.out.println();
    }

    //Näitab kui palju raha on arvel
    public double rahaArvel() {
        double rahaArvel = 0;
        for (Tehing tehing : tehinguInfo) {
            if (tehing.isSissetulek()) {
                rahaArvel += tehing.getMaksumus();
            } else {
                rahaArvel -= tehing.getMaksumus();
            }
        }
        return Math.round(rahaArvel * 100.0) / 100.0 ;
    }

    //Näitab sissetuleku antud ajavahemikus
    public double koguSissetulek(LocalDate algusAeg, LocalDate lõppAeg) {
        double koguSissetulek = 0;
        for (Tehing tehing : tehinguInfo) {
            if (tehing.isSissetulek() &&
                    tehing.getKuupäev().isAfter(algusAeg) &&
                    tehing.getKuupäev().isBefore(lõppAeg)) {
                koguSissetulek += tehing.getMaksumus();
            }
        }
        return koguSissetulek;
    }

    //Näitab väljamineku antud ajavahemikus
    public double koguVäljaminek(LocalDate algusAeg, LocalDate lõppAeg) {
        double koguVäljaminek = 0;
        for (Tehing tehing : tehinguInfo) {
            if (!tehing.isSissetulek() &&
                    tehing.getKuupäev().isAfter(algusAeg) &&
                    tehing.getKuupäev().isBefore(lõppAeg)) {
                koguVäljaminek += tehing.getMaksumus();
            }
        }
        return koguVäljaminek;
    }

    //Näitab kategooria kaupa väljaminekud
    public Map<String, Double> kategooriaKaupa(LocalDate algusAeg, LocalDate lõppAeg) {
        Map<String, Double> kategooriaKaupa = new HashMap<>();

        for (Tehing tehing : tehinguInfo) {
            if (!tehing.isSissetulek() &&
                    tehing.getKuupäev().isAfter(algusAeg) &&
                    tehing.getKuupäev().isBefore(lõppAeg)) {
                String kategooria = tehing.getKategooria();
                double maksumus = tehing.getMaksumus();
                if (!kategooriaKaupa.containsKey(kategooria)) {
                    kategooriaKaupa.put(kategooria, maksumus);
                } else {
                    double summa = kategooriaKaupa.get(kategooria);
                    kategooriaKaupa.put(kategooria, summa + maksumus);
                }
            }

        }
        return kategooriaKaupa;
    }

    //Näitab kuu kaupa sissetulekud ja väljaminekud sisestatud aastal
    public Map<String, Map<String, Double>> kuuKaupa(int aasta) {
        Map<String, Map<String, Double>> kuuKaupa = new HashMap<>();

        for (int i = 1; i <= 12; i++) {
            String kuu = Integer.toString(i);
            kuuKaupa.put(kuu, new HashMap<>());
            kuuKaupa.get(kuu).put("sissetulekud", 0.0);
            kuuKaupa.get(kuu).put("väljaminekud", 0.0);
        }

        for (Tehing tehing : tehinguInfo) {
            int tehinguAasta = tehing.getKuupäev().getYear();
            if (tehinguAasta == aasta) {
                String kuu = Integer.toString(tehing.getKuupäev().getMonthValue());
                double maksumus = tehing.getMaksumus();

                if (tehing.isSissetulek()) {
                    double summa = kuuKaupa.get(kuu).get("sissetulekud");
                    kuuKaupa.get(kuu).put("sissetulekud", summa + maksumus);
                } else {
                    double summa = kuuKaupa.get(kuu).get("väljaminekud");
                    kuuKaupa.get(kuu).put("väljaminekud", summa + maksumus);
                }
            }
        }
        return kuuKaupa;
    }
}

