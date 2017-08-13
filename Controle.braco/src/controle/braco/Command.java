/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.braco;

/**
 *
 * @author Ismael
 */
public class Command {
    String text;
    String command;

    public Command(String text, String command) {
        this.text = text;
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return text;
    }
    
    
}
