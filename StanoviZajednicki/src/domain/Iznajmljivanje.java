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
public class Iznajmljivanje extends AbstractDomainObject {

    private int iznajmljivanjeID;
    private String opis;
    private double ukupanIznos;
    private PoslovniPartner poslovniPartner;
    private Zaposleni zaposleni;
    private ArrayList<StavkaIznajmljivanja> stavkeIznajmljivanja;

    public Iznajmljivanje(int iznajmljivanjeID, String opis, double ukupanIznos, PoslovniPartner poslovniPartner, Zaposleni zaposleni, ArrayList<StavkaIznajmljivanja> stavkeIznajmljivanja) {
        this.iznajmljivanjeID = iznajmljivanjeID;
        this.opis = opis;
        this.ukupanIznos = ukupanIznos;
        this.poslovniPartner = poslovniPartner;
        this.zaposleni = zaposleni;
        this.stavkeIznajmljivanja = stavkeIznajmljivanja;
    }

    public Iznajmljivanje() {
    }

    @Override
    public String nazivTabele() {
        return " Iznajmljivanje ";
    }

    @Override
    public String alijas() {
        return " i ";
    }

    @Override
    public String join() {
        return " JOIN POSLOVNIPARTNER PP ON (PP.POSLOVNIPARTNERID = I.POSLOVNIPARTNERID)\n"
                + "JOIN GRAD G ON (G.GRADID = PP.GRADID)\n"
                + "JOIN ZAPOSLENI Z ON (Z.ZAPOSLENIID = I.ZAPOSLENIID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Zaposleni z = new Zaposleni(rs.getInt("ZaposleniID"),
                    rs.getString("z.Ime"), rs.getString("z.Prezime"),
                    rs.getString("KorisnickoIme"), rs.getString("Lozinka"));

            Grad g = new Grad(rs.getInt("GradID"),
                    rs.getString("naziv"));

            PoslovniPartner pp = new PoslovniPartner(rs.getInt("PoslovniPartnerID"),
                    rs.getString("pp.Ime"), rs.getString("pp.Prezime"),
                    rs.getString("Email"), rs.getString("Telefon"), g);

            Iznajmljivanje i = new Iznajmljivanje(rs.getInt("iznajmljivanjeID"),
                    rs.getString("opis"), rs.getDouble("ukupanIznos"), pp, z, null);

            lista.add(i);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (opis, ukupanIznos, PoslovniPartnerID, zaposleniID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + opis + "', " + ukupanIznos + ", "
                + " " + poslovniPartner.getPoslovniPartnerID() + ", "
                + " " + zaposleni.getZaposleniID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " opis = '" + opis + "', ukupanIznos = " + ukupanIznos + " ";
    }

    @Override
    public String uslov() {
        return " iznajmljivanjeID = " + iznajmljivanjeID;
    }

    @Override
    public String uslovZaSelect() {
        if (poslovniPartner != null) {
            return " WHERE PP.POSLOVNIPARTNERID = " + poslovniPartner.getPoslovniPartnerID();
        }
        return "";
    }

    public int getIznajmljivanjeID() {
        return iznajmljivanjeID;
    }

    public void setIznajmljivanjeID(int iznajmljivanjeID) {
        this.iznajmljivanjeID = iznajmljivanjeID;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public PoslovniPartner getPoslovniPartner() {
        return poslovniPartner;
    }

    public void setPoslovniPartner(PoslovniPartner poslovniPartner) {
        this.poslovniPartner = poslovniPartner;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public ArrayList<StavkaIznajmljivanja> getStavkeIznajmljivanja() {
        return stavkeIznajmljivanja;
    }

    public void setStavkeIznajmljivanja(ArrayList<StavkaIznajmljivanja> stavkeIznajmljivanja) {
        this.stavkeIznajmljivanja = stavkeIznajmljivanja;
    }

}
