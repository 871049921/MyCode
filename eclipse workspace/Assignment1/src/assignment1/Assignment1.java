/*
 * Date:2018/10/18
 * Description:A simple calculator
 */

package assignment1;

public class Assignment1 {

	public static void main(String[] args) throws Exception{
		File_io a = new File_io();
		String[] b = a.outputInformation();
		Deal deal = new Deal(b);
		String result[] = deal.divideArr();
		a.inputInformation(result);
	}
}
