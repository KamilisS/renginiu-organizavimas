/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renginiu_organizavimas;

import common.Common;
import gui.LoginMain;

/**
 *
 * @author gvt48
 */
public class Renginiu_organizavimas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        showLoginWindow();
    }
    
    private static void showLoginWindow() {
        LoginMain loginMain = new LoginMain();
        Common.center(loginMain);
        loginMain.setTitle("Renginių organizavimo sistema");
        loginMain.setResizable(false);
        loginMain.setVisible(true);
    }
    
}
