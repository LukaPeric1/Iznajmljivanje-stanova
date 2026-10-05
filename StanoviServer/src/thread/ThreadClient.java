/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Iznajmljivanje;
import domain.PoslovniPartner;
import domain.Stan;
import domain.Zaposleni;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author Ari
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_IZNAJMLJIVANJE:
                    ServerController.getInstance().addIznajmljivanje((Iznajmljivanje) request.getData());
                    break;
                case Operation.ADD_POSLOVNI_PARTNER:
                    ServerController.getInstance().addPoslovniPartner((PoslovniPartner) request.getData());
                    break;
                case Operation.ADD_STAN:
                    ServerController.getInstance().addStan((Stan) request.getData());
                    break;
                case Operation.DELETE_POSLOVNI_PARTNER:
                    ServerController.getInstance().deletePoslovniPartner((PoslovniPartner) request.getData());
                    break;
                case Operation.DELETE_IZNAJMLJIVANJE:
                    ServerController.getInstance().deleteIznajmljivanje((Iznajmljivanje) request.getData());
                    break;
                case Operation.DELETE_STAN:
                    ServerController.getInstance().deleteStan((Stan) request.getData());
                    break;
                case Operation.UPDATE_IZNAJMLJIVANJE:
                    ServerController.getInstance().updateIznajmljivanje((Iznajmljivanje) request.getData());
                    break;
                case Operation.UPDATE_POSLOVNI_PARTNER:
                    ServerController.getInstance().updatePoslovniPartner((PoslovniPartner) request.getData());
                    break;
                case Operation.UPDATE_STAN:
                    ServerController.getInstance().updateStan((Stan) request.getData());
                    break;
                case Operation.GET_ALL_GRAD:
                    response.setData(ServerController.getInstance().getAllGrad());
                    break;
                case Operation.GET_ALL_IZNAJMLJIVANJE:
                    response.setData(ServerController.getInstance().getAllIznajmljivanje((PoslovniPartner) request.getData()));
                    break;
                case Operation.GET_ALL_POSLOVNI_PARTNER:
                    response.setData(ServerController.getInstance().getAllPoslovniPartner());
                    break;
                case Operation.GET_ALL_STAVKA_IZNAJMLJIVANJA:
                    response.setData(ServerController.getInstance().getAllStavkaIznajmljivanja((Stan) request.getData()));
                    break;
                case Operation.GET_ALL_STAN:
                    response.setData(ServerController.getInstance().getAllStan());
                    break;
                case Operation.LOGIN:
                    Zaposleni zaposleni = (Zaposleni) request.getData();
                    Zaposleni zap = ServerController.getInstance().login(zaposleni);
                    response.setData(zap);
                    break;
                case Operation.LOGOUT:
                    Zaposleni ulogovani = (Zaposleni) request.getData();
                    ServerController.getInstance().logout(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception ex) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(ex);
        }
        return response;
    }

}
