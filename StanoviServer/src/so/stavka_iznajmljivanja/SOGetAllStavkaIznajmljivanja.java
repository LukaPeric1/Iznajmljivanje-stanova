/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavka_iznajmljivanja;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.StavkaIznajmljivanja;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Ari
 */
public class SOGetAllStavkaIznajmljivanja extends AbstractSO {

    private ArrayList<StavkaIznajmljivanja> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaIznajmljivanja)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaIznajmljivanja!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> stavkeIznajmljivanja = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaIznajmljivanja>) (ArrayList<?>) stavkeIznajmljivanja;
    }

    public ArrayList<StavkaIznajmljivanja> getLista() {
        return lista;
    }

}
