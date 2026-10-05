/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Grad;
import domain.Iznajmljivanje;
import domain.PoslovniPartner;
import domain.Stan;
import domain.StavkaIznajmljivanja;
import domain.Zaposleni;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author Ari
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Zaposleni login(Zaposleni zap) throws Exception {
        return (Zaposleni) sendRequest(Operation.LOGIN, zap);
    }

    public void logout(Zaposleni ulogovani) throws Exception {
        sendRequest(Operation.LOGOUT, ulogovani);
    }

    public void addPoslovniPartner(PoslovniPartner poslovniPartner) throws Exception {
        sendRequest(Operation.ADD_POSLOVNI_PARTNER, poslovniPartner);
    }

    public void addIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        sendRequest(Operation.ADD_IZNAJMLJIVANJE, iznajmljivanje);
    }

    public void addStan(Stan Stan) throws Exception {
        sendRequest(Operation.ADD_STAN, Stan);
    }

    public void deletePoslovniPartner(PoslovniPartner poslovniPartner) throws Exception {
        sendRequest(Operation.DELETE_POSLOVNI_PARTNER, poslovniPartner);
    }

    public void deleteIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        sendRequest(Operation.DELETE_IZNAJMLJIVANJE, iznajmljivanje);
    }

    public void deleteStan(Stan stan) throws Exception {
        sendRequest(Operation.DELETE_STAN, stan);
    }

    public void updatePoslovniPartner(PoslovniPartner poslovniPartner) throws Exception {
        sendRequest(Operation.UPDATE_POSLOVNI_PARTNER, poslovniPartner);
    }

    public void updateIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        sendRequest(Operation.UPDATE_IZNAJMLJIVANJE, iznajmljivanje);
    }

    public void updateStan(Stan stan) throws Exception {
        sendRequest(Operation.UPDATE_STAN, stan);
    }

    public ArrayList<Iznajmljivanje> getAllIznajmljivanje(PoslovniPartner poslovniPartner) throws Exception {
        return (ArrayList<Iznajmljivanje>) sendRequest(Operation.GET_ALL_IZNAJMLJIVANJE, poslovniPartner);
    }

    public ArrayList<PoslovniPartner> getAllPoslovniPartner() throws Exception {
        return (ArrayList<PoslovniPartner>) sendRequest(Operation.GET_ALL_POSLOVNI_PARTNER, null);
    }

    public ArrayList<Grad> getAllGrad() throws Exception {
        return (ArrayList<Grad>) sendRequest(Operation.GET_ALL_GRAD, null);
    }

    public ArrayList<StavkaIznajmljivanja> getAllStavkaIznajmljivanja() throws Exception {
        return (ArrayList<StavkaIznajmljivanja>) sendRequest(Operation.GET_ALL_STAVKA_IZNAJMLJIVANJA, null);
    }

    public ArrayList<Stan> getAllStan() throws Exception {
        return (ArrayList<Stan>) sendRequest(Operation.GET_ALL_STAN, null);
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }

    public ArrayList<StavkaIznajmljivanja> getAllStavkaStana(Stan stan) throws Exception {
        return (ArrayList<StavkaIznajmljivanja>) sendRequest(Operation.GET_ALL_STAVKA_IZNAJMLJIVANJA, stan);
    }

}
