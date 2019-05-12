package t11_8;

public class T11_8 {
	public static void main(String[] args) {
		NewAccount account = new NewAccount("George", 1122, 1000.0);
		account.setAnnualInterestRate(1.5);
		account.deposit(30);
		account.deposit(40);
		account.deposit(50);
		account.withDraw(2);
		account.withDraw(4);
		account.withDraw(5);
		for (int i = 0; i< account.getArrList().size(); i++) {
			//这里要强制转换为Transaction类型，因为默认为Object类型
			Transaction transaction = (Transaction)account.getArrList().get(i);
			String description = transaction.getDescription();
			System.out.println(description);
		}
	}
}
