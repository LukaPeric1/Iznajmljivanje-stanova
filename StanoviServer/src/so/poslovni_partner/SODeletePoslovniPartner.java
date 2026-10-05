/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.poslovni_partner;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.PoslovniPartner;
import so.AbstractSO;

/**
 *
 * @author Ari
 */
public class SODeletePoslovniPartner extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PoslovniPartner)) {
            throw new Exception("Prosledjeni objekat nije instanca klase PoslovniPartner!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
