package psimj;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

class SerializedClass implements Serializable {
	private static final long serialVersionUID = -5882189879957417026L;
	private byte[] data;
	private String name;
	
	public SerializedClass (String name, byte[] data){
		this.name = name;
		this.data = data;
	}
	
	public String getName() {
		return name;
	}
	
	public byte[] getData() {
		return data;
	}
	
	public static SerializedClass fromClass(Class type) throws IOException {
		InputStream is = type.getResourceAsStream(type.getName() + ".class");
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		while (true) {
		    int r = is.read(buffer);
		    if (r == -1) break;
		    out.write(buffer, 0, r);
		}
		byte[] data = out.toByteArray();
		return new SerializedClass(type.getName(),data);
	}
}
