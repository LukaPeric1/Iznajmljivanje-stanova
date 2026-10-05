/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stan;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Stan;
import so.AbstractSO;

/**
 *
 * @author Ari
 */
public class SOAddStan extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Stan)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Stan!");
        }

        Stan s = (Stan) ado;

        if (s.getKvadratura() < 20 || s.getKvadratura() > 1000) {
            throw new Exception("Kvadratura mora biti izmedju 20 i 1000 kvadrata!");
        }

        if (s.getCenaPoDanu() < 20 || s.getCenaPoDanu() > 1000) {
            throw new Exception("Cena po danu mora biti izmedju 20 i 1000 eura!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
