package exp2;

public class SalesRecord {
	private String information;
	private long recordID = 0;//商品销售编号
	private int amount = 1;//商品数量，盒装商品为1，散装商品为实际数量
	private static long number = 0;//产生的编号数量
	Goods goods = null;//所销售的商品类 
	private static double allPrice = 0.0;//总销售金额
	private static double allBoxPackedPrice = 0.0;//盒装商品总销售金额
	private static double allBulkPrice = 0.0;//散装商品总销售金额
	private static int[] allGoodsprice = new int[100];//每个商品当天的总销售额
	
	//盒装销售记录构造方法
	public SalesRecord(Goods goods, String barCode, int goodsNumber) {
		number++;
		recordID = number;
		this.goods = goods;
		information = recordID + " 商品名称：" + goods.getGoodsName() + " 购买方式：盒装 " + " 购买数量： 1" + " 金额：" + goods.getPrice();
		allPrice += goods.getPrice();
		allBoxPackedPrice += goods.getPrice();
		allGoodsprice[goodsNumber] += goods.getPrice();
		//System.out.println(information);
	}
	
	//散装销售记录构造方法
	public SalesRecord(Goods goods, int goodsNumber) {
		number++;
		recordID = number;
		this.goods = goods;
		amount = (int)(Math.random()*10 + 1);
		information = recordID + " 商品名称：" + goods.getGoodsName() + " 购买方式：散装 " + " 购买数量： " + amount + " 金额：" + goods.getPrice() * amount;
		allPrice += goods.getPrice() * amount;
		allBulkPrice += goods.getPrice() * amount;
		allGoodsprice[goodsNumber] += goods.getPrice() * amount;
		//System.out.println(information);
	}
	
	//获得当天销售的总信息
	public static String getAllInformationOfToday(Goods[] goods, int goodsNumber) {
		String allInformationOfToday = "当天销售总额：" + allPrice + "\n盒装商品销售总额：" + allBoxPackedPrice + "\n散装商品销售总额：" + allBulkPrice + "\n";
		for (int i = 0; i < goodsNumber; i++) {
			allInformationOfToday += goods[i].getGoodsName() + "的销售总额：" + allGoodsprice[i] + "\r\n";
		}
		return allInformationOfToday;
	}
	
	//获得每一条信息
	public String getEveryInformation() {
		return information;
	}
}
