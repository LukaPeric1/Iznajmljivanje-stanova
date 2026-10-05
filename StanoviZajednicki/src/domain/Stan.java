/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ari
 */
public class Stan extends AbstractDomainObject {

    private int stanID;
    private double kvadratura;
    private String lokacija;
    private String opis;
    private double cenaPoDanu;

    @Override
    public String toString() {
        return lokacija + " (Cena po danu: " + cenaPoDanu + "€, Opis: " + opis + ")";
    }

    public Stan(int stanID, double kvadratura, String lokacija, String opis, double cenaPoDanu) {
        this.stanID = stanID;
        this.kvadratura = kvadratura;
        this.lokacija = lokacija;
        this.opis = opis;
        this.cenaPoDanu = cenaPoDanu;
    }

    public Stan() {
    }

    @Override
    public String nazivTabele() {
        return " Stan ";
    }

    @Override
    public String alijas() {
        return " s ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Stan s = new Stan(rs.getInt("StanID"),
                    rs.getDouble("kvadratura"), rs.getString("Lokacija"),
                    rs.getString("Opis"), rs.getDouble("cenaPoDanu"));

            lista.add(s);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (kvadratura, Lokacija, Opis, cenaPoDanu) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + kvadratura + ", '" + lokacija + "', "
                + "'" + opis + "', " + cenaPoDanu + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " opis = '" + opis + "', cenaPoDanu = " + cenaPoDanu + " ";
    }

    @Override
    public String uslov() {
        return " stanID = " + stanID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public int getStanID() {
        return stanID;
    }

    public void setStanID(int stanID) {
        this.stanID = stanID;
    }

    public double getKvadratura() {
        return kvadratura;
    }

    public void setKvadratura(double kvadratura) {
        this.kvadratura = kvadratura;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(double cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
    }

}
