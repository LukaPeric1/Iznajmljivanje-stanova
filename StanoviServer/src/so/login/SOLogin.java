/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import controller.ServerController;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Zaposleni;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Ari
 */
public class SOLogin extends AbstractSO {

    Zaposleni ulogovani;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Zaposleni)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Zaposleni!");
        }

        Zaposleni z = (Zaposleni) ado;

        for (Zaposleni zaposleni : ServerController.getInstance().getUlogovaniZaposleni()) {
            if (zaposleni.getKorisnickoIme().equals(z.getKorisnickoIme())) {
                throw new Exception("Ovaj zaposleni je vec ulogovan na sistem!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Zaposleni z = (Zaposleni) ado;

        ArrayList<Zaposleni> listaZaposlenih
                = (ArrayList<Zaposleni>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Zaposleni zaposleni : listaZaposlenih) {
            if (zaposleni.getKorisnickoIme().equals(z.getKorisnickoIme())
                    && zaposleni.getLozinka().equals(z.getLozinka())) {
                ulogovani = zaposleni;
                ServerController.getInstance().getUlogovaniZaposleni().add(zaposleni);
                return;
            }
        }

        throw new Exception("Ne postoji zaposleni sa tim kredencijalima.");

    }

    public Zaposleni getUlogovani() {
        return ulogovani;
    }

}
