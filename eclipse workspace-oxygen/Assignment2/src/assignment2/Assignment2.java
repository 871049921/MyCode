package assignment2;

public class Assignment2 {

	public static void main(String[] args) throws Exception {
		File_IO file = new File_IO();
		String requests[] = file.outputInformation();
		RequestQueue requestQueue = new RequestQueue();
		for (int i = 0; requests[i] != null; i++) {
			Request request = new Request(requests[i]);
			requestQueue.enQueue(requests[i]);
		}
		Dispatcher dispatcher = new Dispatcher(requestQueue);
		dispatcher.dealWithRequests();
		file.inputInformation(dispatcher.getOutputArr());
	}
}
