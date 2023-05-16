package com.example.money_beast.wallet;
import java.time.LocalDate;

public class Tehing {

    private double maksumus;

    private String kategooria;

    private boolean sissetulek;

    private LocalDate kuupäev;

    public Tehing(double maksumus, String kategooria, boolean sissetulek, LocalDate kuupäev) {
        this.maksumus = maksumus;
        this.kategooria = kategooria;
        this.sissetulek = sissetulek;
        this.kuupäev = kuupäev;
    }

    public String faili() {
        return maksumus + "," + kategooria + "," + sissetulek + "," + kuupäev;
    }

    public double getMaksumus() {
        return maksumus;
    }

    public void setMaksumus(double maksumus) {
        this.maksumus = maksumus;
    }

    public String getKategooria() {
        return kategooria;
    }

    public void setKategooria(String kategooria) {
        this.kategooria = kategooria;
    }

    public boolean isSissetulek() {
        return sissetulek;
    }

    public void setSissetulek(boolean sissetulek) {
        this.sissetulek = sissetulek;
    }

    public LocalDate getKuupäev() {
        return kuupäev;
    }

    public void setKuupäev(LocalDate kuupäev) {
        this.kuupäev = kuupäev;
    }

    @Override
    public String toString() {
        return "Tehing{" +
                "maksumus=" + maksumus +
                ", kategooria='" + kategooria + '\'' +
                ", sissetulek=" + sissetulek +
                ", kuupäev=" + kuupäev +
                '}';
    }
}
