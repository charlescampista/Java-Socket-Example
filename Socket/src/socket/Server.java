/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charles
 */
public class Server {
    public static void main(String[] args){
        System.out.println("Esperando por clientes...");
        
        try {
            ServerSocket serverSocket = new ServerSocket(9806);
            //O metodo accept fica esperando a conexão de algum socket cliente
            //quando um cliente é encontrado o método accept() retorna uma instancia de objeto do tipo Socket
            //Esta instancia é capturada pela variavel socket.
            Socket socket = serverSocket.accept();
            System.out.println("Conexão estabelecida");
            
            //Neste caso o inputStream le os dados que estão no socket
            // ao contrario do cliente que le os dados que o usuario digita
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = reader.readLine();
            System.out.println("Mensagem Recebida: "+message);
            
            //Enviando a mesma String de volta para o cliente
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            writer.println("O Servidor diz: "+message);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
