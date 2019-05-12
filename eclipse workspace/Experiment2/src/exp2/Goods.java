package exp2;

public class Goods {
	private String goodsName = null;//商品名称
	private boolean boxPacked = true;//是否为合装商品，true代表盒装，false代表散装
	private String barCode = null;//商品条形码，只有盒装商品才有
	private float unitPrice = 0.0f;//单价
	
	//盒装商品
	public Goods(String goodsName,String barCode,float unitPrice){
		this.boxPacked = true;
		this.goodsName = goodsName;
		this.barCode = barCode;
		this.unitPrice = unitPrice;
	}
	
	//散装商品
	public Goods(String goodsName,float unitPrice){
		this.boxPacked = false;
		this.goodsName = goodsName;
		this.unitPrice = unitPrice;
	}
	
	//获得商品单价
	 public float getPrice() {
		 return unitPrice;
	 }
	 
	 //获得该商品是否为盒装
	 public boolean isBoxPacked() {
		 return boxPacked;
	 }
	 
	 //获得商品的所有信息
	 public String getGoodInfomation() {
		 String box = "";
		 if (boxPacked == true) {
			 box = "是";
		 }
		 else {
			 box = "否";
		 }
		 String index ="商品名：" + goodsName + " 是否盒装 ：" + box + " 单价：" + unitPrice + "元";
		 return index;
	 }
	 
	 //获得商品的条形码
	 public String getBarCode() {
		 if (barCode != null) {
			 return barCode;
		 }
		 else {
			 return "No barcode";
		 }
	 }
	 
	 //获得商品名字
	 public String getGoodsName() {
		 return goodsName;
	 }
	 
}
