package exp2;

import java.io.*;

public class Experiment2 {
	public static void main(String[] args) throws Exception{
		String allInformation = "";//所有信息
		int i = 0;
		Goods[] goods = new Goods[10];
		String randomGoodsName[] = {"pen", "pencil", "backpack", "ruler", "apple", "pear", 
				"banana", "strawberry", "watermelon", "water"};
		SalesRecord[] allRecords = new SalesRecord[1000];
		//创建盒装商品类
		for (; i < 5; i++) {
			goods[i] = new Goods(randomGoodsName[i], "" + i, i * 10 + 10);
			//System.out.println(goods[i].getGoodInfomation());
		}
		//创建散装商品类
		for (; i < 10; i++) {
			goods[i] = new Goods(randomGoodsName[i], i * 10 + 10);
			//System.out.println(goods[i].getGoodInfomation());
		}
		for (int j = 0; j < 1000; j++) {
			int goodsNumber = (int)(Math.random()*10);
			if (goods[goodsNumber].isBoxPacked() == true) {//盒装
				allRecords[j] = new SalesRecord(goods[goodsNumber], goods[goodsNumber].getBarCode(), goodsNumber);
			}
			else {
				allRecords[j] = new SalesRecord(goods[goodsNumber], goodsNumber);
			}
			allInformation += allRecords[j].getEveryInformation() + "\n";
		}
		PrintWriter output = new PrintWriter(new File("fuck.txt"));
		output.println(allInformation + SalesRecord.getAllInformationOfToday(goods, 10));
		System.out.println(allInformation + SalesRecord.getAllInformationOfToday(goods, 10));
		output.close();
	}
}
