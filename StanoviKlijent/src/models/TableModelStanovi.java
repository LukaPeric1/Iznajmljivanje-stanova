/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.PoslovniPartner;
import domain.Stan;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ari
 */
public class TableModelStanovi extends AbstractTableModel implements Runnable {

    private ArrayList<Stan> lista;
    private String[] kolone = {"StanID", "Kvadratura", "Lokacija", "Opis", "Cena po danu"};
    private String parametar = "";

    public TableModelStanovi() {
        try {
            lista = ClientController.getInstance().getAllStan();
        } catch (Exception ex) {
            Logger.getLogger(TableModelStanovi.class.getName()).log(Level.SEVERE, null, ex);
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
        Stan s = lista.get(row);

        switch (column) {
            case 0:
                return s.getStanID();
            case 1:
                return s.getKvadratura();
            case 2:
                return s.getLokacija();
            case 3:
                return s.getOpis();
            case 4:
                return s.getCenaPoDanu();
           

            default:
                return null;
        }
    }

    public Stan getSelectedStan(int row) {
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
            Logger.getLogger(TableModelStanovi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllStan();
            if (!parametar.equals("")) {
                ArrayList<Stan> novaLista = new ArrayList<>();
                for (Stan ss : lista) {
                    if (ss.getLokacija().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(ss);
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
