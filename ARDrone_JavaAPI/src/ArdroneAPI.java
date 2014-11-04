import java.io.*;
import java.net.*;

public class ArdroneAPI {
	private static int NAVDATA_PORT = 5556;
	private DatagramSocket connection;
    private InetAddress ipAddress;
 	private int sequenceNumber;
        
    public ArdroneAPI(String ip) {
    	try {
	    	ipAddress = InetAddress.getByName(ip);
    		System.out.println("Started");

    		connection = new DatagramSocket();
    	}catch(UnknownHostException e){
    		System.err.println(e);
    	}catch(SocketException e){
    		System.err.println(e);
    	}
	}	

	public void close(){
		write( "AT*REF","290717696");
		connection.close();

	}

	public void write(String command, String arguments){
		try{
			String message = command + "=" + sequenceNumber + "," + arguments + "\r";
			System.out.println(message);
			byte[] sendData = message.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ipAddress,NAVDATA_PORT);
	        connection.send(sendPacket);
	        sequenceNumber++;                      
    	}catch(IOException e){
    		System.err.println(e);
    	}    
	}

	public void connect(){
    	write("AT*CONFIG","\"control:altitude_max\",\"2000\"");
    	write("AT*REF","290718208");
	}

	public void left(){
		write("AT*PCMD","1,0,-1082130432,0,0");
	}

	public void right(){
		write("AT*PCMD","1,0,1065353216,0,0");
	}

	public void front(){
		write("AT*PCMD","1,-1082130432,0,0,0");
	}

	public void back(){
		write("AT*PCMD","1,1065353216,0,0,0");
	}

	public void up(){
		write("AT*PCMD","1,0,0,1065353216,0");
	}

	public void down(){
		write("AT*PCMD","1,0,0,-1082130432,0");
	}

	public void tright(){
		write("AT*PCMD","1,0,0,0,1065353216");
	}

	public void tleft(){
		write("AT*PCMD","1,0,0,0,-1082130432");
	}

}