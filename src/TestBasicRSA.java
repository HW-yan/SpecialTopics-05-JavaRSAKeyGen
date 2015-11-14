//TestBasicRSA：主程式，執行class

import java.io.*;
import java.util.Scanner;

public class TestBasicRSA {

	public static void main(String[] args){
		
		//** RSA金鑰產生器 **\\
		//RSAKeyGen rsa1= new RSAKeyGen(512);
		RSAKeyGen rsa1= new RSAKeyGen(64);
		rsa1.KeyGen();
		System.out.println("Strength = "+rsa1.getStrength());
		
		//檢查金鑰所需的東西是否架設完整
		System.out.println("p = "+rsa1.getp().toString());
		System.out.println("q = "+rsa1.getq().toString());
		System.out.println("n = "+rsa1.getn().toString());
		System.out.println("e = "+rsa1.gete().toString());
		System.out.println("d = "+rsa1.getd().toString());
		
		//**檔案系統**\\
		try{
		//測試
		File f1 = new File("test.txt");
		Scanner s = new Scanner(f1);
		System.out.printf(s.nextLine());
		
		//公鑰寫入檔案
		File f2 = new File("PublicKey01.txt");
		rsa1.exportPublicKey(f2);
		
		//私鑰寫入檔案
		File f3 = new File("PriateKey01.txt");
		rsa1.exportPriateKey(f3);
		
		}
		catch(Exception e){
			
			System.out.printf("File Error ! ");

		}
		
	}
	
}


//Q1：為何BigInteger要轉成String印出?
//Q2：查詢Throw例外狀況