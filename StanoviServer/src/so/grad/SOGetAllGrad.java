/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.grad;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Grad;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Ari
 */
public class SOGetAllGrad extends AbstractSO {

    private ArrayList<Grad> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Grad)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Grad!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> gradovi = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Grad>) (ArrayList<?>) gradovi;
    }

    public ArrayList<Grad> getLista() {
        return lista;
    }

}
