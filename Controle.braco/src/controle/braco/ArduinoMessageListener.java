/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.braco;

/**
 *
 * @author ntbra
 */
public interface ArduinoMessageListener {
    public void messageReceived(String message);
    public void onError(Throwable ex);
}
