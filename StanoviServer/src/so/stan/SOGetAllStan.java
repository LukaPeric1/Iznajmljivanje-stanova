/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stan;

import so.poslovni_partner.*;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.PoslovniPartner;
import domain.Stan;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Ari
 */
public class SOGetAllStan extends AbstractSO {

    private ArrayList<Stan> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Stan)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Stan!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> stanovi = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Stan>) (ArrayList<?>) stanovi;
    }

    public ArrayList<Stan> getLista() {
        return lista;
    }

}
