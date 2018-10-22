/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charles
 */
public class Client {
    public static void main(String[] args){
        System.out.println("Cliente iniciado!");
        try {
            Socket socket = new Socket("localhost",9806);
            
            //Os streamings são usados parar ler(inpunt)
            //e para escrever(output) dados
            //O System.in signfica o ImputStream 
            //que pode ler um byte de dado de cada vez
            //O ImputStream pega esses bytes e transforma em um caracter
            //O BufferReader é capaz de ler uma String inteira por vez
            //Entar o BufferReader recebe o ImputStream com todos os seus caracteres
            //E transforma em uma String.
            //O BufferReader Lê uma linha inteira do usuario e retorna como uma String
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Digite sua mensagem:");
            String message = reader.readLine();
            
            //O PrintWriter é usado para Enviar os dados
            //true como parametro serve para limpar os dados do print writer
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            writer.println(message);
            
            
            //Lendo a mensagem que o socket enviou
            BufferedReader readerServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = readerServer.readLine();
            System.out.println("Resposta do servidor: "+serverResponse);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
