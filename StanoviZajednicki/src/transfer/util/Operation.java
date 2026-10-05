/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author Ari
 */
public interface Operation {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int ADD_POSLOVNI_PARTNER = 2;
    public static final int DELETE_POSLOVNI_PARTNER = 3;
    public static final int UPDATE_POSLOVNI_PARTNER = 4;
    public static final int GET_ALL_POSLOVNI_PARTNER = 5;

    public static final int ADD_STAN = 6;
    public static final int DELETE_STAN = 7;
    public static final int UPDATE_STAN = 8;
    public static final int GET_ALL_STAN = 9;

    public static final int ADD_IZNAJMLJIVANJE = 10;
    public static final int DELETE_IZNAJMLJIVANJE = 11;
    public static final int UPDATE_IZNAJMLJIVANJE = 12;
    public static final int GET_ALL_IZNAJMLJIVANJE = 13;

    public static final int GET_ALL_STAVKA_IZNAJMLJIVANJA = 14;

    public static final int GET_ALL_GRAD = 15;

}
