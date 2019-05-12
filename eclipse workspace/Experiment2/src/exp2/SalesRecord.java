package exp2;

public class SalesRecord {
	private String information;
	private long recordID = 0;//��Ʒ���۱��
	private int amount = 1;//��Ʒ��������װ��ƷΪ1��ɢװ��ƷΪʵ������
	private static long number = 0;//�����ı������
	Goods goods = null;//�����۵���Ʒ�� 
	private static double allPrice = 0.0;//�����۽��
	private static double allBoxPackedPrice = 0.0;//��װ��Ʒ�����۽��
	private static double allBulkPrice = 0.0;//ɢװ��Ʒ�����۽��
	private static int[] allGoodsprice = new int[100];//ÿ����Ʒ����������۶�
	
	//��װ���ۼ�¼���췽��
	public SalesRecord(Goods goods, String barCode, int goodsNumber) {
		number++;
		recordID = number;
		this.goods = goods;
		information = recordID + " ��Ʒ���ƣ�" + goods.getGoodsName() + " ����ʽ����װ " + " ���������� 1" + " ��" + goods.getPrice();
		allPrice += goods.getPrice();
		allBoxPackedPrice += goods.getPrice();
		allGoodsprice[goodsNumber] += goods.getPrice();
		//System.out.println(information);
	}
	
	//ɢװ���ۼ�¼���췽��
	public SalesRecord(Goods goods, int goodsNumber) {
		number++;
		recordID = number;
		this.goods = goods;
		amount = (int)(Math.random()*10 + 1);
		information = recordID + " ��Ʒ���ƣ�" + goods.getGoodsName() + " ����ʽ��ɢװ " + " ���������� " + amount + " ��" + goods.getPrice() * amount;
		allPrice += goods.getPrice() * amount;
		allBulkPrice += goods.getPrice() * amount;
		allGoodsprice[goodsNumber] += goods.getPrice() * amount;
		//System.out.println(information);
	}
	
	//��õ������۵�����Ϣ
	public static String getAllInformationOfToday(Goods[] goods, int goodsNumber) {
		String allInformationOfToday = "���������ܶ" + allPrice + "\n��װ��Ʒ�����ܶ" + allBoxPackedPrice + "\nɢװ��Ʒ�����ܶ" + allBulkPrice + "\n";
		for (int i = 0; i < goodsNumber; i++) {
			allInformationOfToday += goods[i].getGoodsName() + "�������ܶ" + allGoodsprice[i] + "\r\n";
		}
		return allInformationOfToday;
	}
	
	//���ÿһ����Ϣ
	public String getEveryInformation() {
		return information;
	}
}
