package controle.braco;

public class Arduino {
  private ControlePorta arduino;
  
  private final String portaCOM;
  private final int taxa;
  public Arduino(String portaCOM, int taxa){
      this.portaCOM = portaCOM;
      this.taxa = taxa;
      this.open();
  }
  
  public final void open(){
      arduino = new ControlePorta(this.portaCOM, this.taxa);
  }
  
  public void envia(String value){
      arduino.enviaDados(value);
  }
  
  public String recebe(){
      return arduino.recebeDados();
  }
  
  public void close(){
      arduino.close();
  }
  
  public void addListener(ArduinoMessageListener listener){
      arduino.addListener(listener);
  }  
}
