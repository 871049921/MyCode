import java.util.ArrayList;

public class Assignment4 {

	public static void main(String[] args) throws Exception {
		File_IO file = new File_IO();
		String requests[] = file.outputInformation();
		// requests = dealWithAllRequestsValid(requests);
		LinkList linklist = new LinkList();
		for (int i = 0; requests[i] != null; i++) {
			linklist.inList(requests[i]);
		}
		Dispatcher newDispatcher = new NewDispatcher(linklist);
		newDispatcher.dealWithRequests();
		file.inputInformation(newDispatcher.getOutputArr(), linklist.getInValid());
		String averangeTime = "乘客的平均等待时间是" + newDispatcher.getAllWaitsTime() / newDispatcher.getFRNumber() + "秒";
		ArrayList<String> waitsTime = newDispatcher.getWaitsTime();
		waitsTime.add(averangeTime);
		file.inputInformation(waitsTime);
		// System.out.println(newDispatcher.getOutputArr()[4]);
	}

	// 处理所有请求的正确性
	public static String[] dealWithAllRequestsValid(String requests[]) {
		String[] rightRequests = new String[10000];
		Dispatcher dispatcherTest = new DispatcherTest();
		String lastExpression = "";
		int j = 0;
		for (int i = 0; requests[i] != null; i++) {
			if (requests[i].equals("RUN")) {
				return rightRequests;
			}
			String origin = requests[i];
			requests[i] = dispatcherTest.deleteAllBlank(requests[i]);
			if (dispatcherTest.isValid(requests[i], lastExpression)) { // 正确
				rightRequests[j] = requests[i];
				j++;
				lastExpression = origin;
			}
		}
		return rightRequests;
	}
}
