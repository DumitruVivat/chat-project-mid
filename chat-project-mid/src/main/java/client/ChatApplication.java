package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Scanner;
import lib.Action;
import lib.Connection;
import lib.Operation;
import lib.User;

public class ChatApplication {
	
	

	public static void main(String[] args) throws UnknownHostException, ClassNotFoundException, IOException {
		
		Scanner scanner = new Scanner(System.in);
		Connection conn = new Connection("localhost",7778);
		
		System.out.print("Write your username : ");
		User user = new User(scanner.nextLine());
	
		Action action = new Action(Operation.SIGNIN,user);
		conn.send(action);
		
		action = (Action) conn.fetch();
		if(action.getType() == Operation.SUCCESS) {
			System.out.println("Signed in!!");
		}
		conn.send(new Action(Operation.USERLIST));
		
		action = (Action) conn.fetch();
		if(action.getType() == Operation.SUCCESS) {
			HashMap<Socket,Object> users =  (HashMap<Socket, Object>) action.getTarget();
			System.out.println(users);
		}
	}	
}
