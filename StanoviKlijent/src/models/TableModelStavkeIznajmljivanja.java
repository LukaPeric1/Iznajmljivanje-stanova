/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domain.Stan;
import domain.StavkaIznajmljivanja;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ari
 */
public class TableModelStavkeIznajmljivanja extends AbstractTableModel {

    private ArrayList<StavkaIznajmljivanja> lista;
    private String[] kolone = {"Rb", "Datum od", "Datum do", "Broj dana", "Iznos"};
    private int rb;

    public TableModelStavkeIznajmljivanja() {
        lista = new ArrayList<>();
    }

    public TableModelStavkeIznajmljivanja(ArrayList<StavkaIznajmljivanja> stavkeIznajmljivanja) {
        lista = stavkeIznajmljivanja;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        StavkaIznajmljivanja si = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        switch (column) {
            case 0:
                return si.getRb();
            case 1:
                return sdf.format(si.getDatumOd());
            case 2:
                return sdf.format(si.getDatumDo());
            case 3:
                return si.getBrojDana();
            case 4:
                return si.getIznos() + "€";

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaIznajmljivanja si) {
        rb = lista.size();
        si.setRb(++rb);
        lista.add(si);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        lista.remove(row);

        rb = 0;
        for (StavkaIznajmljivanja stavkaIznajmljivanja : lista) {
            stavkaIznajmljivanja.setRb(++rb);
        }

        fireTableDataChanged();
    }

    public double vratiUkupanIznos() {
        double ukupanIznos = 0;

        for (StavkaIznajmljivanja stavkaIznajmljivanja : lista) {
            ukupanIznos += stavkaIznajmljivanja.getIznos();
        }

        return ukupanIznos;
    }

    public ArrayList<StavkaIznajmljivanja> getLista() {
        return lista;
    }

//    public Date vratiPrviDatum() {
//        Date prviDatum = new Date();
//
//        for (StavkaIznajmljivanja stavkaIznajmljivanja : lista) {
//            Date datumStavke = new Date(stavkaIznajmljivanja.getDatumOd().getTime());
//            if (datumStavke.before(prviDatum)) {
//                prviDatum = datumStavke;
//            }
//        }
//
//        return prviDatum;
//    }
    public boolean postojiStan(Stan stan) {
        for (StavkaIznajmljivanja stavkaIznajmljivanja : lista) {
            if (stan.getStanID() == stavkaIznajmljivanja.getStan().getStanID()) {
                return true;
            }
        }
        return false;
    }

}
