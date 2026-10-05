/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Grad;
import domain.Iznajmljivanje;
import domain.PoslovniPartner;
import domain.Stan;
import domain.StavkaIznajmljivanja;
import domain.Zaposleni;
import java.util.ArrayList;
import so.grad.SOGetAllGrad;
import so.iznajmljivanje.SOAddIznajmljivanje;
import so.iznajmljivanje.SODeleteIznajmljivanje;
import so.iznajmljivanje.SOGetAllIznajmljivanje;
import so.iznajmljivanje.SOUpdateIznajmljivanje;
import so.login.SOLogin;
import so.poslovni_partner.SOAddPoslovniPartner;
import so.poslovni_partner.SODeletePoslovniPartner;
import so.poslovni_partner.SOGetAllPoslovniPartner;
import so.poslovni_partner.SOUpdatePoslovniPartner;
import so.stan.SOAddStan;
import so.stan.SODeleteStan;
import so.stan.SOGetAllStan;
import so.stan.SOUpdateStan;
import so.stavka_iznajmljivanja.SOGetAllStavkaIznajmljivanja;

/**
 *
 * @author Ari
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Zaposleni> ulogovaniZaposleni = new ArrayList<>();

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Zaposleni> getUlogovaniZaposleni() {
        return ulogovaniZaposleni;
    }

    public void setUlogovaniZaposleni(ArrayList<Zaposleni> ulogovaniZaposleni) {
        this.ulogovaniZaposleni = ulogovaniZaposleni;
    }

    public Zaposleni login(Zaposleni zaposleni) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(zaposleni);
        return so.getUlogovani();
    }

    public void addPoslovniPartner(PoslovniPartner poslovniPartner) throws Exception {
        (new SOAddPoslovniPartner()).templateExecute(poslovniPartner);
    }

    public void addIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        (new SOAddIznajmljivanje()).templateExecute(iznajmljivanje);
    }

    public void addStan(Stan stan) throws Exception {
        (new SOAddStan()).templateExecute(stan);
    }

    public void deletePoslovniPartner(PoslovniPartner poslovniPartner) throws Exception {
        (new SODeletePoslovniPartner()).templateExecute(poslovniPartner);
    }

    public void deleteIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        (new SODeleteIznajmljivanje()).templateExecute(iznajmljivanje);
    }

    public void deleteStan(Stan stan) throws Exception {
        (new SODeleteStan()).templateExecute(stan);
    }

    public void updatePoslovniPartner(PoslovniPartner poslovniPartner) throws Exception {
        (new SOUpdatePoslovniPartner()).templateExecute(poslovniPartner);
    }

    public void updateIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        (new SOUpdateIznajmljivanje()).templateExecute(iznajmljivanje);
    }

    public void updateStan(Stan stan) throws Exception {
        (new SOUpdateStan()).templateExecute(stan);
    }

    public ArrayList<PoslovniPartner> getAllPoslovniPartner() throws Exception {
        SOGetAllPoslovniPartner so = new SOGetAllPoslovniPartner();
        so.templateExecute(new PoslovniPartner());
        return so.getLista();
    }

    public ArrayList<Iznajmljivanje> getAllIznajmljivanje(PoslovniPartner poslovniPartner) throws Exception {
        SOGetAllIznajmljivanje so = new SOGetAllIznajmljivanje();
        
        Iznajmljivanje i = new Iznajmljivanje();
        i.setPoslovniPartner(poslovniPartner);
        
        so.templateExecute(i);
        return so.getLista();
    }

    public ArrayList<Grad> getAllGrad() throws Exception {
        SOGetAllGrad so = new SOGetAllGrad();
        so.templateExecute(new Grad());
        return so.getLista();
    }

    public ArrayList<StavkaIznajmljivanja> getAllStavkaIznajmljivanja(Stan stan) throws Exception {
        SOGetAllStavkaIznajmljivanja so = new SOGetAllStavkaIznajmljivanja();
        
        StavkaIznajmljivanja si = new StavkaIznajmljivanja();
        si.setStan(stan);
        
        so.templateExecute(si);
        return so.getLista();
    }

    public ArrayList<Stan> getAllStan() throws Exception {
        SOGetAllStan so = new SOGetAllStan();
        so.templateExecute(new Stan());
        return so.getLista();
    }

    public void logout(Zaposleni ulogovani) {
        ulogovaniZaposleni.remove(ulogovani);
    }

}
