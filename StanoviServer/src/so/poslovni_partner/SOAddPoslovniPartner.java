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
public class SOAddPoslovniPartner extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PoslovniPartner)) {
            throw new Exception("Prosledjeni objekat nije instanca klase PoslovniPartner!");
        }

        PoslovniPartner pp = (PoslovniPartner) ado;

        ArrayList<PoslovniPartner> poslovniPartneri
                = (ArrayList<PoslovniPartner>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (PoslovniPartner poslovniPartner : poslovniPartneri) {
            if (poslovniPartner.getEmail().equals(pp.getEmail())) {
                throw new Exception("Email vec postoji!");
            }
            if (poslovniPartner.getTelefon().equals(pp.getTelefon())) {
                throw new Exception("Telefon vec postoji!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
