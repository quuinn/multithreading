/**   
* @Title ThroughputHttpServer.java 
* @Package com.quinn.performance.throughput 
* @Description TODO 
* @author pigmilk
* @date Apr 17, 2022 8:22:48 PM 
* @version 1.0.0   
*/
package com.quinn.performance.throughput;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

/**
 * @ClassName ThroughputHttpServer
 * @Description TODO(這裡用一句話描述這個類的作用)
 * @author pigmilk
 * @date Apr 17, 2022 8:22:48 PM
 */

public class ThroughputHttpServer {
	private static final String INPUT_FILE = "resources/throughput/war_and_peace.txt";

	public static void main(String[] args) throws IOException {
		String text = new String(Files.readAllBytes(Paths.get(INPUT_FILE)));
		startServer(text);
	}

	public static void startServer(String text) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
	}
}