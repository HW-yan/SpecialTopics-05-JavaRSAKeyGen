//RSAKeyGen�G�D�n�b���_���͡B�N���_�s��
import java.math.BigInteger;
import java.io.*;

/*
*�B�zthrow�Ĥ@��
*���j�d�� (?) �� �����D
*/

//public class RSAKeyGen() throws ArithmeticException{
public class RSAKeyGen {

	private BigInteger p,q,n,e,d;
	private int strength;
	private java.util.Random rnd = new java.util.Random(); //�üƲ��;�
	
	//** �i���n���ͦh�j����� ( ���_���� ) **\\
	
	// �� ���c
	public RSAKeyGen(int strength){
		this.strength = strength;
	}
	
	public RSAKeyGen(){
		this.strength = 1024;
	}
	//�� ���c
	
	
	//** �Nprivate���Ȯ��X�ӵ���L�H�� **\\
	public BigInteger getp(){ return p;	}
	public BigInteger getq(){ return q;	}
	public BigInteger getn(){ return n;	}
	public BigInteger gete(){ return e;	}
	public BigInteger getd(){ return d;	}
	
	public int getStrength(){ return strength; }

	
	/*
	 * �ϱoKeyGen����	�A����Ӥ�k�i����
	 * 1.��bRSAKeyGen������
	 * 2.�~������ ( �����ĥ� �� ��bmain�� ) 
	 */

	public void KeyGen() {
		
		//** ���ͩһݤ��� **\\
		//���ͽ��
		p = new BigInteger(strength/2, 100, rnd);
		q = new BigInteger(strength/2, 100, rnd);
		n = p.multiply(q);
		e = new BigInteger("65537");
		BigInteger phi_n = n.subtract(p).subtract(q).subtract(BigInteger.ONE);
		//�쥻 :d = e.modInverse(phi_n);
		
		//**�B�zd���ҥ~���p ( �S���Ϥ������p )**\\
		
		//�B�zthrow���ĤG�� �� try&catch
		//�B�z���p�d��throw 
		try{
			
			d = e.modInverse(phi_n);
			
		}
		catch(ArithmeticException e ){
			System.out.println("No Inverse Element !");
			d = new BigInteger ("-1");
		}
		
	}
	
	//** �g�J�ɮ� **\\
	
	//�N���_�g�i�ɮ��ح�
	public void exportPublicKey (File f){
		try{
			
			FileWriter fw = new FileWriter(f);
								//FileWriter(      f		, �i�M�w�O�_�Ƽg�e�@�ɮ�);
								//FileWriter(String fileName, boolean append)
			fw.write(n.toString()+"\n");
			fw.write(e.toString());
			fw.close();
	
		}
		catch(Exception e){
			
			System.out.println("File Write Error !");
			
		}
		
	}
	
	//�N�p�_�g�J�ɮ�
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
 * �Q�kI:RSA�����B��
 * Generate 2 prime p,q
 * Evaluate n = p*q
 * Set e = 65537
 * Evaluate d=e^-1 mod \phi(n)
 * 
 * �Q�k�G�G
 * Public  key�Gne
 * private key�Gnd
 * ���ɰ��D:�n��bworkspace�U���p�e����Ƨ���
 * 
 */