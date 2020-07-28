package burst.kit.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import javax.net.SocketFactory;

import org.berndpruenster.netlayer.tor.NativeTor;
import org.berndpruenster.netlayer.tor.Tor;
import org.berndpruenster.netlayer.tor.TorCtlException;
import org.berndpruenster.netlayer.tor.TorSocket;

import okhttp3.Dns;

public class TorSocketFactory extends SocketFactory {
	
	public static final String ONION_EXTENSION = ".onion";
	
	private String host;
	private int port;
	
	public TorSocketFactory(String host, int port) {
		this.host = host;
		this.port = port;
		
		if(Tor.getDefault() == null) {
			try {
				Tor.setDefault(new NativeTor(new File("burstkit-tor")));
			} catch (TorCtlException e) {
				e.printStackTrace();
			}
		}
	}
	
	class TorSocketProxy extends Socket {
		TorSocket socket;
		
		@Override
		public void connect(SocketAddress endpoint, int timeout) throws IOException {
			socket = new TorSocket(host, port, null);
		}
		
		@Override
		public InputStream getInputStream() throws IOException {
			return socket.getInputStream();
		}
		
		@Override
		public OutputStream getOutputStream() throws IOException {
			return socket.getOutputStream();
		}
	}
	
	public Dns getDns() {
		return new Dns() {
			@Override
			public List<InetAddress> lookup(String hostname) throws UnknownHostException {
				if(hostname.contains(ONION_EXTENSION))
					return Arrays.asList(InetAddress.getLocalHost()); // localhost for onion addresses
				return Arrays.asList(InetAddress.getAllByName(hostname));
			}			
		};
	}
	
	@Override
	public Socket createSocket() throws IOException {
		return new TorSocketProxy();
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
		// not used
		return null;
	}

	@Override
	public Socket createSocket(InetAddress host, int port) throws IOException {
		// not used
		return null;
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localHost, int localPort)
			throws IOException, UnknownHostException {
		// not used
		return null;
	}

	@Override
	public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort)
			throws IOException {
		// not used
		return null;
	}

}
