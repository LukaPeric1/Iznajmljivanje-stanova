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
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Ari
 */
public class SOGetAllIznajmljivanje extends AbstractSO {

    private ArrayList<Iznajmljivanje> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Iznajmljivanje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Iznajmljivanje!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // Vracamo svа iznajmljivanja
        ArrayList<AbstractDomainObject> svaIznajmljivanja = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Iznajmljivanje>) (ArrayList<?>) svaIznajmljivanja;

        // Prolazimo kroz listu iznajmljivanja i vracamo sve stavke tog iznajmljivanja
        for (Iznajmljivanje iznajmljivanje : lista) {

            // Pravimo novu stavku gde setujemo iznajmljivanje, da bi ovaj dole
            // DBBroker.getInstance().select(stavkaIznajmljivanja) imao u sebi 
            // to iznajmljivanje i uradio uslovZaSelect() koji ce da vrati
            // WHERE IZNAJMLJIVANJEID = iznajmljivanjeID (od ovog u kome smo)
            StavkaIznajmljivanja stavkaIznajmljivanja = new StavkaIznajmljivanja();
            stavkaIznajmljivanja.setIznajmljivanje(iznajmljivanje);

            // Ovo nam vraca sve stavke naseg trenutnog iznajmljivanja
            ArrayList<StavkaIznajmljivanja> stavkeTrenutnogIznajmljivanja
                    = (ArrayList<StavkaIznajmljivanja>) (ArrayList<?>) DBBroker.getInstance().select(stavkaIznajmljivanja);

            // Setujemo te stavke za nase trenutno iznajmljivanje
            iznajmljivanje.setStavkeIznajmljivanja(stavkeTrenutnogIznajmljivanja);
        }

        
    }

    public ArrayList<Iznajmljivanje> getLista() {
        return lista;
    }

}
