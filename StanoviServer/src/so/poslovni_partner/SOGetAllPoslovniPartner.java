/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.poslovni_partner;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.PoslovniPartner;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Ari
 */
public class SOGetAllPoslovniPartner extends AbstractSO {

    private ArrayList<PoslovniPartner> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PoslovniPartner)) {
            throw new Exception("Prosledjeni objekat nije instanca klase PoslovniPartner!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> poslovniPartneri = DBBroker.getInstance().select(ado);
        lista = (ArrayList<PoslovniPartner>) (ArrayList<?>) poslovniPartneri;
    }

    public ArrayList<PoslovniPartner> getLista() {
        return lista;
    }

}
