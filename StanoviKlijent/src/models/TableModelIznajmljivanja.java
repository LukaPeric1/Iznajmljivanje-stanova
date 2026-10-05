/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Iznajmljivanje;
import domain.PoslovniPartner;
import domain.Stan;
import domain.StavkaIznajmljivanja;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ari
 */
public class TableModelIznajmljivanja extends AbstractTableModel implements Runnable {

    private ArrayList<Iznajmljivanje> lista;
    private String[] kolone = {"ID", "Poslovni partner", "Ukupan iznos"};
    private String parametar = "";

    public TableModelIznajmljivanja() {
        try {
            lista = ClientController.getInstance().getAllIznajmljivanje(null);
        } catch (Exception ex) {
            Logger.getLogger(TableModelIznajmljivanja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TableModelIznajmljivanja(PoslovniPartner poslovniPartner) {
        try {
            lista = ClientController.getInstance().getAllIznajmljivanje(poslovniPartner);
        } catch (Exception ex) {
            Logger.getLogger(TableModelIznajmljivanja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TableModelIznajmljivanja(Stan stan) {
        try {
            lista = new ArrayList<>();

            ArrayList<StavkaIznajmljivanja> stavkeStana
                    = ClientController.getInstance().getAllStavkaStana(stan);

            for (StavkaIznajmljivanja stavkaIznajmljivanja : stavkeStana) {
                lista.add(stavkaIznajmljivanja.getIznajmljivanje());
            }

        } catch (Exception ex) {
            Logger.getLogger(TableModelIznajmljivanja.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        Iznajmljivanje i = lista.get(row);

        switch (column) {
            case 0:
                return i.getIznajmljivanjeID();
            case 1:
                return i.getPoslovniPartner();
            case 2:
                return i.getUkupanIznos() + "€";

            default:
                return null;
        }
    }

    public Iznajmljivanje getSelectedIznajmljivanje(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelIznajmljivanja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllIznajmljivanje(null);
            if (!parametar.equals("")) {
                ArrayList<Iznajmljivanje> novaLista = new ArrayList<>();
                for (Iznajmljivanje i : lista) {
                    if (i.getPoslovniPartner().getIme().toLowerCase().contains(parametar.toLowerCase())
                            || i.getPoslovniPartner().getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(i);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
