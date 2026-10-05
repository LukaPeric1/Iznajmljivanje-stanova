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
public class ZaposleniTermin extends AbstractDomainObject {

    private TerminDezurstva terminDezurstva;
    private Zaposleni zaposleni;
    private Date datum;

    public ZaposleniTermin(TerminDezurstva terminDezurstva, Zaposleni zaposleni, Date datum) {
        this.terminDezurstva = terminDezurstva;
        this.zaposleni = zaposleni;
        this.datum = datum;
    }

    public ZaposleniTermin() {
    }

    @Override
    public String nazivTabele() {
        return " ZaposleniTermin ";
    }

    @Override
    public String alijas() {
        return " zt ";
    }

    @Override
    public String join() {
        return " JOIN ZAPOSLENI Z ON (Z.ZAPOSLENIID = ZT.ZAPOSLENIID)\n"
                + "JOIN TERMINDEZURSTVA TD ON (TD.TERMINDEZURSTVAID = ZT.TERMINDEZURSTVAID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Zaposleni z = new Zaposleni(rs.getInt("ZaposleniID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("KorisnickoIme"), rs.getString("Lozinka"));

            TerminDezurstva td = new TerminDezurstva(rs.getInt("TerminDezurstvaID"),
                    rs.getString("Smena"));

            ZaposleniTermin zt = new ZaposleniTermin(td, z, rs.getDate("datum"));

            lista.add(zt);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (TerminDezurstvaID, ZaposleniID, datum) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + terminDezurstva.getTerminDezurstvaID() + ", "
                + " " + zaposleni.getZaposleniID() + ", "
                + "'" + new java.sql.Date(datum.getTime()) + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " zaposleniID = " + zaposleni.getZaposleniID();
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public TerminDezurstva getTerminDezurstva() {
        return terminDezurstva;
    }

    public void setTerminDezurstva(TerminDezurstva terminDezurstva) {
        this.terminDezurstva = terminDezurstva;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

}
