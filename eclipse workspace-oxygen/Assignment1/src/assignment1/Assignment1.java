/*
 * Date:2018/10/18
 * Description:A simple calculator
 */

package assignment1;

import java.lang.*;
import java.util.*;
import java.io.*;

public class Assignment1 {

	public static void main(String[] args) throws Exception{
		File_io a = new File_io();
		Deal deal = new Deal(a.outputInformation());
		deal.divideArr();
	}
}
