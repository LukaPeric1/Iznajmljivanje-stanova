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
public class PoslovniPartner extends AbstractDomainObject {

    private int poslovniPartnerID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private Grad grad;

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public PoslovniPartner(int poslovniPartnerID, String ime, String prezime, String email, String telefon, Grad grad) {
        this.poslovniPartnerID = poslovniPartnerID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.grad = grad;
    }

    public PoslovniPartner() {
    }

    @Override
    public String nazivTabele() {
        return " PoslovniPartner ";
    }

    @Override
    public String alijas() {
        return " pp ";
    }

    @Override
    public String join() {
        return " JOIN GRAD G ON (G.GRADID = PP.GRADID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Grad g = new Grad(rs.getInt("GradID"),
                    rs.getString("naziv"));

            PoslovniPartner pp = new PoslovniPartner(rs.getInt("PoslovniPartnerID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Email"), rs.getString("Telefon"), g);

            lista.add(pp);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Email, Telefon, GradID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + email + "', '" + telefon + "', " + grad.getGradID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " email = '" + email + "', telefon = '" + telefon + "', "
                + "gradID = " + grad.getGradID() + " ";
    }

    @Override
    public String uslov() {
        return " poslovniPartnerID = " + poslovniPartnerID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public int getPoslovniPartnerID() {
        return poslovniPartnerID;
    }

    public void setPoslovniPartnerID(int poslovniPartnerID) {
        this.poslovniPartnerID = poslovniPartnerID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

}
