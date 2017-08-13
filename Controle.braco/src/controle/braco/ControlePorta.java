/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controle.braco;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControlePorta {
  private OutputStream serialOut;
  private InputStream serialIn;
  private int taxa;
  private String portaCOM;
  private BufferedReader reader;
  public ArduinoListener listener;
  private ScheduledExecutorService execListener;
 
  /**
   * Construtor da classe ControlePorta
   * @param portaCOM - Porta COM que será utilizada para enviar os dados para o arduino
   * @param taxa - Taxa de transferência da porta serial geralmente é 9600
   */
  public ControlePorta(String portaCOM, int taxa) {
    this.portaCOM = portaCOM;
    this.taxa = taxa;
    listener = new ArduinoListener();
    this.initialize();
    execListener = Executors.newSingleThreadScheduledExecutor();
    execListener.scheduleAtFixedRate(listener, 0, 500, TimeUnit.MILLISECONDS);
  }     
 
  /**
   * Médoto que verifica se a comunicação com a porta serial está ok
   */
  private void initialize() {
    try {
      //Define uma variável portId do tipo CommPortIdentifier para realizar a comunicação serial
      CommPortIdentifier portId = null;
      try {
        //Tenta verificar se a porta COM informada existe
        portId = CommPortIdentifier.getPortIdentifier(this.portaCOM);
      }catch (NoSuchPortException npe) {
        //Caso a porta COM não exista será exibido um erro 
        JOptionPane.showMessageDialog(null, "Porta COM não encontrada.",
                  "Porta COM", JOptionPane.ERROR_MESSAGE);
      }
      //Abre a porta COM 
        SerialPort port = (SerialPort) portId.open("Comunicação serial", this.taxa);
      serialOut = port.getOutputStream();
      serialIn = port.getInputStream();
      port.setSerialPortParams(this.taxa, //taxa de transferência da porta serial 
                               SerialPort.DATABITS_8, //taxa de 10 bits 8 (envio)
                               SerialPort.STOPBITS_1, //taxa de 10 bits 1 (recebimento)
                               SerialPort.PARITY_NONE); //receber e enviar dados
      reader = new BufferedReader(new InputStreamReader(serialIn));
    }catch (Exception e) {
      e.printStackTrace();
    }
}
 
  /**
   * Método que fecha a comunicação com a porta serial
   */
  public void close() {
    try {
        serialOut.close();
    }catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Não foi possível fechar porta COM.",
                "Fechar porta COM", JOptionPane.PLAIN_MESSAGE);
    }
  }
 
  public void enviaDados(String dado){
    try {
      serialOut.write((dado).getBytes());
      serialOut.flush();
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "Não foi possível enviar o dado. ",
                "Enviar dados", JOptionPane.PLAIN_MESSAGE);
    }
  } 
  
  public String recebeDados(){
      String total = "";
      try {
          String read;
          while((read = reader.readLine()) != null){
              total+=read;
          }
      } catch (IOException ex) {
          //System.err.println("Erro: "+ex.getLocalizedMessage());
      }
      return total.trim();
  }
  
  public int getAvailable(){
      try {
          return serialIn.available();
      } catch (IOException ex) {
          Logger.getLogger(ControlePorta.class.getName()).log(Level.SEVERE, null, ex);
      }
      return 0;
  }
  protected InputStream getInput(){
      return this.serialIn;
  }
  
  protected OutputStream getOut(){
      return this.serialOut;
  }
  
  public void addListener(ArduinoMessageListener listener){
      this.listener.listeners.add(listener);
  }
  
  public void removeListener(ArduinoMessageListener listener){
      this.listener.listeners.remove(listener);
  }
  
  private class ArduinoListener implements Runnable{
    List<ArduinoMessageListener> listeners = new ArrayList<>();
      
    @Override
    public void run() {
        try {
            if(reader.ready()){
                String r;
                while((r = reader.readLine().trim()) != null){
                    for(ArduinoMessageListener c: listeners)
                        c.messageReceived(r);
                }
            }
        } catch (IOException ex) {
            listeners.forEach((c)->{
                c.onError(ex);
            });
        }
    }
      
  }
  
}
