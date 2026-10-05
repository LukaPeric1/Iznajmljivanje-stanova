/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ari
 */
public class StavkaIznajmljivanja extends AbstractDomainObject {

    private Iznajmljivanje iznajmljivanje;
    private int rb;
    private Date datumOd;
    private Date datumDo;
    private int brojDana;
    private double cenaPoDanu;
    private double iznos;
    private Stan stan;

    public StavkaIznajmljivanja(Iznajmljivanje iznajmljivanje, int rb, Date datumOd, Date datumDo, int brojDana, double cenaPoDanu, double iznos, Stan stan) {
        this.iznajmljivanje = iznajmljivanje;
        this.rb = rb;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.brojDana = brojDana;
        this.cenaPoDanu = cenaPoDanu;
        this.iznos = iznos;
        this.stan = stan;
    }

    public StavkaIznajmljivanja() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaIznajmljivanja ";
    }

    @Override
    public String alijas() {
        return " si ";
    }

    @Override
    public String join() {
        return " JOIN IZNAJMLJIVANJE I ON (I.IZNAJMLJIVANJEID = SI.IZNAJMLJIVANJEID)\n"
                + "JOIN POSLOVNIPARTNER PP ON (I.POSLOVNIPARTNERID = PP.POSLOVNIPARTNERID)\n"
                + "JOIN GRAD G ON (G.GRADID = PP.GRADID)\n"
                + "JOIN ZAPOSLENI Z ON (Z.ZAPOSLENIID = I.ZAPOSLENIID)\n"
                + "JOIN STAN S ON (S.STANID = SI.STANID)";
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
                    rs.getString("opis"), rs.getDouble("ukupanIznos"),
                    pp, z, null);

            Stan s = new Stan(rs.getInt("StanID"),
                    rs.getDouble("kvadratura"), rs.getString("Lokacija"),
                    rs.getString("Opis"), rs.getDouble("cenaPoDanu"));

            StavkaIznajmljivanja si = new StavkaIznajmljivanja(i, rs.getInt("rb"),
                    rs.getDate("datumOd"), rs.getDate("datumDo"), rs.getInt("brojDana"),
                    rs.getDouble("cenaPoDanu"), rs.getDouble("iznos"), s);

            lista.add(si);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (iznajmljivanjeID, rb, datumOd, datumDo, brojDana, cenaPoDanu, iznos, stanID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + iznajmljivanje.getIznajmljivanjeID() + ", " + rb + ", "
                + "'" + new java.sql.Date(datumOd.getTime()) + "', "
                + "'" + new java.sql.Date(datumDo.getTime()) + "', "
                + brojDana + ", " + cenaPoDanu + ", " + iznos + ", " + stan.getStanID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " iznajmljivanjeID = " + iznajmljivanje.getIznajmljivanjeID();
    }

    @Override
    public String uslovZaSelect() {
        if(iznajmljivanje != null){
            return " WHERE I.IZNAJMLJIVANJEID = " + iznajmljivanje.getIznajmljivanjeID();
        }
        return " WHERE S.STANID = " + stan.getStanID();
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(double cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Stan getStan() {
        return stan;
    }

    public void setStan(Stan stan) {
        this.stan = stan;
    }

}
