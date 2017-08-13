/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.braco;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 *
 * @author Ismael
 */
public class SendQueue implements Runnable{
    private static Map<Integer, String> messages = Collections.synchronizedMap(new HashMap<Integer, String>());
    
    public static void addMessage(String message){
        int pos;
        int i = 1;
        do{
            pos = messages.size()+i;
            i++;
        }while(messages.containsKey(pos));
        messages.put(pos, message);
    }
    
    Arduino arduino;
    public SendQueue(Arduino arduino) {
        this.arduino = arduino;
    }    
    
    @Override
    public void run() {
        if(arduino != null && !messages.isEmpty()){
            messages.entrySet().stream().findFirst().ifPresent((e)->{
                arduino.envia(e.getValue());
                messages.remove(e.getKey());
            });
        }
    }
    
}
