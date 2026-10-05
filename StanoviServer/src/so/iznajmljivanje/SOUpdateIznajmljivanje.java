/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.iznajmljivanje;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Iznajmljivanje;
import domain.StavkaIznajmljivanja;
import so.AbstractSO;

/**
 *
 * @author Ari
 */
public class SOUpdateIznajmljivanje extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Iznajmljivanje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Iznajmljivanje!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // Azuriramo iznajmljivanje
        DBBroker.getInstance().update(ado);

        Iznajmljivanje i = (Iznajmljivanje) ado;
        // Obrisemo stare stavke
        // Sledeca linija koda izvrsava naredbu
        // DELETE FROM STAVKAIZNAJMLJIVANJA WHERE IZNAJMLJIVANJEID = nasID
        // cime brisemo SVE stavke naseg iznajmljivanja ODJEDNOM !!!
        DBBroker.getInstance().delete(i.getStavkeIznajmljivanja().get(0));
        // Prosledili smo nasu prvu stavku jer ona uvek postoji jer moramo
        // barem jednu da imamo i ima ID naseg iznajmljivanja

        // Dodajemo nove
        for (StavkaIznajmljivanja stavkaIznajmljivanja : i.getStavkeIznajmljivanja()) {
            DBBroker.getInstance().insert(stavkaIznajmljivanja);
        }

        // ovaj nacin nije optimalan, mogu da te pitaju sta ako imas 
        // milion stavki, onda bi milion brisao i milion novih dodavao
        // sto oduzima mnogo resursa
        // optimalan nacin je da imamo uvid u to (neki status) koja stavka je 
        // obrisana, koja izmenjena, koja dodata i te odredjene
        // da brisemo, menjamo i dodajemo
        // mi smo odradili na ovaj daleko laksi i brzi nacin
        // ako te pitaju za bolji nacin i zasto si ovako radio, samo ovo ispricaj
    }

}
