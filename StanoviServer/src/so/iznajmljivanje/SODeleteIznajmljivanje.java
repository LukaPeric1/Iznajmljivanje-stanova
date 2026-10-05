/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.iznajmljivanje;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Iznajmljivanje;
import so.AbstractSO;

/**
 *
 * @author Ari
 */
public class SODeleteIznajmljivanje extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Iznajmljivanje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Iznajmljivanje!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
