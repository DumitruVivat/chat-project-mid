package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import lib.Action;
import lib.Connection;
import lib.Operation;
import lib.User;

public class ChatApplication {
	
	static HashMap<Socket, User> users = new HashMap<>();
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		System.out.println("Server Starting");
		
		ServerSocket serverSocket = new ServerSocket(7778);
		Connection conn = new Connection(serverSocket.accept());
		
		Action action = (Action) conn.fetch();
		
		if(action.getType() == Operation.SIGNIN) {
			users.put(conn.getSocket(),(User)action.getTarget());
		}
		conn.send(new Action(Operation.SUCCESS));
		
		action = (Action) conn.fetch();
		System.out.println(users);

		if(action.getType() == Operation.USERLIST) {
			conn.send( new Action(Operation.SUCCESS,users));
		}
	    System.out.println("Serrver Ending");
		
	}
	
}
