/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.PoslovniPartner;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ari
 */
public class TableModelPoslovniPartneri extends AbstractTableModel implements Runnable {

    private ArrayList<PoslovniPartner> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Email", "Telefon", "Grad"};
    private String parametar = "";

    public TableModelPoslovniPartneri() {
        try {
            lista = ClientController.getInstance().getAllPoslovniPartner();
        } catch (Exception ex) {
            Logger.getLogger(TableModelPoslovniPartneri.class.getName()).log(Level.SEVERE, null, ex);
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
        PoslovniPartner pp = lista.get(row);

        switch (column) {
            case 0:
                return pp.getPoslovniPartnerID();
            case 1:
                return pp.getIme();
            case 2:
                return pp.getPrezime();
            case 3:
                return pp.getEmail();
            case 4:
                return pp.getTelefon();
            case 5:
                return pp.getGrad();

            default:
                return null;
        }
    }

    public PoslovniPartner getSelectedPoslovniPartner(int row) {
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
            Logger.getLogger(TableModelPoslovniPartneri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllPoslovniPartner();
            if (!parametar.equals("")) {
                ArrayList<PoslovniPartner> novaLista = new ArrayList<>();
                for (PoslovniPartner pp : lista) {
                    if (pp.getIme().toLowerCase().contains(parametar.toLowerCase())
                            || pp.getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(pp);
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
