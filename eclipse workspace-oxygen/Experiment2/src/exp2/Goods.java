package exp2;

public class Goods {
	private String goodsName = null;//��Ʒ����
	private boolean boxPacked = true;//�Ƿ�Ϊ��װ��Ʒ��true�����װ��false����ɢװ
	private String barCode = null;//��Ʒ�����룬ֻ�к�װ��Ʒ����
	private float unitPrice = 0.0f;//����
	
	//��װ��Ʒ
	public Goods(String goodsName,String barCode,float unitPrice){
		this.boxPacked = true;
		this.goodsName = goodsName;
		this.barCode = barCode;
		this.unitPrice = unitPrice;
	}
	
	//ɢװ��Ʒ
	public Goods(String goodsName,float unitPrice){
		this.boxPacked = false;
		this.goodsName = goodsName;
		this.unitPrice = unitPrice;
	}
	
	//�����Ʒ����
	 public float getPrice() {
		 return unitPrice;
	 }
	 
	 //��ø���Ʒ�Ƿ�Ϊ��װ
	 public boolean isBoxPacked() {
		 return boxPacked;
	 }
	 
	 //�����Ʒ��������Ϣ
	 public String getGoodInfomation() {
		 String box = "";
		 if (boxPacked == true) {
			 box = "��";
		 }
		 else {
			 box = "��";
		 }
		 String index ="��Ʒ����" + goodsName + " �Ƿ��װ ��" + box + " ���ۣ�" + unitPrice + "Ԫ";
		 return index;
	 }
	 
	 //�����Ʒ��������
	 public String getBarCode() {
		 if (barCode != null) {
			 return barCode;
		 }
		 else {
			 return "No barcode";
		 }
	 }
	 
	 //�����Ʒ����
	 public String getGoodsName() {
		 return goodsName;
	 }
	 
}
