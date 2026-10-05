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
public class TerminDezurstva extends AbstractDomainObject {

    private int terminDezurstvaID;
    private String smena;

    public TerminDezurstva(int terminDezurstvaID, String smena) {
        this.terminDezurstvaID = terminDezurstvaID;
        this.smena = smena;
    }

    public TerminDezurstva() {
    }
    
    @Override
    public String nazivTabele() {
        return " TerminDezurstva ";
    }

    @Override
    public String alijas() {
        return " td ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            TerminDezurstva td = new TerminDezurstva(rs.getInt("TerminDezurstvaID"),
                    rs.getString("Smena"));

            lista.add(td);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Smena) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + smena + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " smena = '" + smena + "' ";
    }

    @Override
    public String uslov() {
        return " terminDezurstvaID = " + terminDezurstvaID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public int getTerminDezurstvaID() {
        return terminDezurstvaID;
    }

    public void setTerminDezurstvaID(int terminDezurstvaID) {
        this.terminDezurstvaID = terminDezurstvaID;
    }

    public String getSmena() {
        return smena;
    }

    public void setSmena(String smena) {
        this.smena = smena;
    }

}
