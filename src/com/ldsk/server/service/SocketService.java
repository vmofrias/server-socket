package com.ldsk.server.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ldsk.server.controller.ControllerLeilao;
import com.ldsk.server.protocol.Mensagem;

public class SocketService {
	private ServerSocket serverSocket;
	
	public SocketService() {
	}
	
	public void criarServerSocket(int porta) throws IOException{
		serverSocket = new ServerSocket(porta);
	}
	
	public Socket esperaConexao() throws IOException{
		Socket socket = serverSocket.accept();
		return socket;
	}
	
	public void fechaSocket(Socket socket) throws IOException {
		socket.close();
	}
	
	public void trataConexao(Socket socket) throws IOException, ClassNotFoundException{
		try {
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			ControllerLeilao controller = new ControllerLeilao();
			
			System.out.println("Tratando...");
			Mensagem m = (Mensagem) input.readObject();
			String operacao = m.getOperacao();
			
			Mensagem reply = controller.operate(operacao, m);
			
			output.writeObject(reply);
			output.flush();
			
			output.close();
			input.close();
		} catch (IOException exception) {
			System.out.println("Problema no tratamento da conexao com o cliente: " + socket.getInetAddress());
			System.out.println("Erro: " + exception.getMessage());
		}finally {
			fechaSocket(socket);
		}
		
		
	}
}
