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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

/**
 *
 * @author Ari
 */
public class SOAddIznajmljivanje extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Iznajmljivanje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Iznajmljivanje!");
        }

        Iznajmljivanje i = (Iznajmljivanje) ado;

        if (i.getStavkeIznajmljivanja().isEmpty()) {
            throw new Exception("Iznajmljivanje mora imati barem jednu stavku!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        // Vracamo ps sa generisanim kljucem
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        // Uzimamo taj kljuc
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        int iznajmljivanjeID = tableKeys.getInt(1);
        
        // Setujemo ga za nase iznajmljivanje (pre je bilo -1, sad je taj novi ID)
        Iznajmljivanje i = (Iznajmljivanje) ado;
        i.setIznajmljivanjeID(iznajmljivanjeID);
        
        // Dodajemo redom stavku po stavku nakon sto setujemo da potice iz naseg iznajmljivanja
        for (StavkaIznajmljivanja stavkaIznajmljivanja : i.getStavkeIznajmljivanja()) {
            stavkaIznajmljivanja.setIznajmljivanje(i); // Ovo smo morali da uradimo jer je prethodno
                                                       // iznajmljivanje bilo NULL, vidi btnDodajStavku
                                                       // na MainFormi
            DBBroker.getInstance().insert(stavkaIznajmljivanja);
        }
        
    }

}
