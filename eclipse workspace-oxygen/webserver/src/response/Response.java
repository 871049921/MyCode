package response;

import java.io.IOException;
import java.io.OutputStream;

public interface Response {
	
	void successResponseHeader(String type);
	
	OutputStream getOutputStream() throws IOException;
}
