package ex5;

import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

class PortScan {

	private ArrayList<Integer> openPorts=new ArrayList<>();

	public void writeToFile(String path, String filename){
		try {
			FileWriter writer= new FileWriter(path+"\\"+filename);
			for (int i = 0; i < openPorts.size()-1; i++) {
				writer.write(openPorts.get(i) + " ,");	
				}
			writer.write(openPorts.get(openPorts.size()-1).toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void slowScanInOrder(String path){
		for (int port = 1; port <= 6553; port++) {
			//for (int port = 100; port <= 500; port++) {// ONLY FOR CHECK

			try {
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress("localhost", port), 150);//1000
				socket.close();
				openPorts.add(port);
			} catch (Exception ex) {
				/* should not be thrown */
			}
		}
		writeToFile(path, "slowScanning.txt");
	}

	public void randomScan(String path){
		String[] visited = new String[65535];
		for (int i = 1; i <= 6553; i++) {
			try {
				int port = (int)(Math.random()*65535);
				visited[port] = "visited";
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress("localhost", port), 150);//1000
				socket.close();				
				openPorts.add(port);
				System.out.println("Port " + port + " is open");
			} catch (Exception ex) {
				/* should not be thrown */
			}
		}

		for (int i = 1; i <= 6553; i++) {
			if(!visited[i].equals("visited")){
				try {
					int port = (int)(Math.random()*65535);
					visited[port] = "visited";
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress("localhost", port), 150);//1000
					socket.close();
					openPorts.add(port);
					System.out.println("Port " + port + " is open");
				} catch (Exception ex) {
					 /*should not be thrown */
				}
			}
		}	
		writeToFile(path, "randomScanning.txt");
	}

	public void smartScan(String path){
		/**
		 * We first check the commonly well known ports that usually open 
		 * and than the ports usually open on our computers (those we found in slow scanning).
		 * the ports opened on our computers: 
		 * 135, 445, 1536, 1537, 1538, 1539, 1541, 1542, 1544, 3648, 5357, 5939, 9990 
		 * 
		 */

		if(available(22)){
			openPorts.add(22);
			System.out.println("22");
		}
		if(available(80)){
			openPorts.add(80);
			System.out.println("80");
		}
		if(available(135)){
			openPorts.add(135);
			System.out.println("135");
		}
		if(available(443)){
			openPorts.add(443);
			System.out.println("443");
		}
		if(available(445)){
			openPorts.add(445);
			System.out.println("445");
		}
		if(available(5357)){
			openPorts.add(5357);
			System.out.println("5357");
		}
		if(available(5359)){
			openPorts.add(5359);
			System.out.println("5359");
		}
		if(available(9990)){
			openPorts.add(9990);
			System.out.println("9990");
		}
		
		for (int i = 1536; i < 1543; i++) {
			if(available(i)){
				openPorts.add(i);
			System.out.println("i= "+i);
		}
		}
		writeToFile(path, "efficientScanning.txt");
	}

	public boolean available(int port) {
		ServerSocket ss = null;
		DatagramSocket ds = null;
		try {
			ss = new ServerSocket(port);
			ss.setReuseAddress(true);
			ds = new DatagramSocket(port);
			ds.setReuseAddress(true);
			return true;
		} catch (IOException e) {
		} finally {
			if (ds != null) {
				ds.close();
			}

			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					/* should not be thrown */
				}
			}
		}

		return false;
	}
/*
	public static void main(String []args) {
		//randomScan("C:\\Users\\Paz Cheredman\\Desktop\\לימודים\\תקשורת\\ex5");
		//smartScan("C:\\Users\\Paz Cheredman\\Desktop\\לימודים\\תקשורת\\ex5");
		//slowScanInOrder("C:\\Users\\Paz Cheredman\\Desktop\\לימודים\\תקשורת\\ex5");

	}*/
}
