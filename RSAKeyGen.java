//RSAKeyGen：主要在金鑰產生、將金鑰存檔
import java.math.BigInteger;
import java.io.*;

/*
*處理throw第一招
*較大範圍的 (?) → 有問題
*/

//public class RSAKeyGen() throws ArithmeticException{
public class RSAKeyGen {

	private BigInteger p,q,n,e,d;
	private int strength;
	private java.util.Random rnd = new java.util.Random(); //亂數產生器
	
	//** 告知要產生多大的質數 ( 金鑰長度 ) **\\
	
	// ↓ 重構
	public RSAKeyGen(int strength){
		this.strength = strength;
	}
	
	public RSAKeyGen(){
		this.strength = 1024;
	}
	//↑ 重構
	
	
	//** 將private的值拿出來給其他人用 **\\
	public BigInteger getp(){ return p;	}
	public BigInteger getq(){ return q;	}
	public BigInteger getn(){ return n;	}
	public BigInteger gete(){ return e;	}
	public BigInteger getd(){ return d;	}
	
	public int getStrength(){ return strength; }

	
	/*
	 * 使得KeyGen執行	，有兩個方法可執行
	 * 1.放在RSAKeyGen內執行
	 * 2.外部執行 ( 本次採用 → 放在main中 ) 
	 */

	public void KeyGen() {
		
		//** 產生所需元素 **\\
		//產生質數
		p = new BigInteger(strength/2, 100, rnd);
		q = new BigInteger(strength/2, 100, rnd);
		n = p.multiply(q);
		e = new BigInteger("65537");
		BigInteger phi_n = n.subtract(p).subtract(q).subtract(BigInteger.ONE);
		//原本 :d = e.modInverse(phi_n);
		
		//**處理d的例外狀況 ( 沒有反元素情況 )**\\
		
		//處理throw的第二招 → try&catch
		//處理較小範圍的throw 
		try{
			
			d = e.modInverse(phi_n);
			
		}
		catch(ArithmeticException e ){
			System.out.println("No Inverse Element !");
			d = new BigInteger ("-1");
		}
		
	}
	
	//** 寫入檔案 **\\
	
	//將公鑰寫進檔案裏面
	public void exportPublicKey (File f){
		try{
			
			FileWriter fw = new FileWriter(f);
								//FileWriter(      f		, 可決定是否複寫前一檔案);
								//FileWriter(String fileName, boolean append)
			fw.write(n.toString()+"\n");
			fw.write(e.toString());
			fw.close();
	
		}
		catch(Exception e){
			
			System.out.println("File Write Error !");
			
		}
		
	}
	
	//將私鑰寫入檔案
	public void exportPriateKey (File f){
		try{
			
			FileWriter fw = new FileWriter(f);
			fw.write(n.toString()+"\n");
			fw.write(d.toString());
			fw.close();
	
		}
		catch(Exception e){
			
			System.out.println("File Write Error !");
			
		}
		
	}
		
}

/*
 * 想法I:RSA相關運算
 * Generate 2 prime p,q
 * Evaluate n = p*q
 * Set e = 65537
 * Evaluate d=e^-1 mod \phi(n)
 * 
 * 想法二：
 * Public  key：ne
 * private key：nd
 * 抓檔問題:要放在workspace下此計畫的資料夾內
 * 
 */