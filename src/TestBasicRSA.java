//TestBasicRSA�G�D�{���A����class

import java.io.*;
import java.util.Scanner;

public class TestBasicRSA {

	public static void main(String[] args){
		
		//** RSA���_���;� **\\
		//RSAKeyGen rsa1= new RSAKeyGen(512);
		RSAKeyGen rsa1= new RSAKeyGen(64);
		rsa1.KeyGen();
		System.out.println("Strength = "+rsa1.getStrength());
		
		//�ˬd���_�һݪ��F��O�_�[�]����
		System.out.println("p = "+rsa1.getp().toString());
		System.out.println("q = "+rsa1.getq().toString());
		System.out.println("n = "+rsa1.getn().toString());
		System.out.println("e = "+rsa1.gete().toString());
		System.out.println("d = "+rsa1.getd().toString());
		
		//**�ɮרt��**\\
		try{
		//����
		File f1 = new File("test.txt");
		Scanner s = new Scanner(f1);
		System.out.printf(s.nextLine());
		
		//���_�g�J�ɮ�
		File f2 = new File("PublicKey01.txt");
		rsa1.exportPublicKey(f2);
		
		//�p�_�g�J�ɮ�
		File f3 = new File("PriateKey01.txt");
		rsa1.exportPriateKey(f3);
		
		}
		catch(Exception e){
			
			System.out.printf("File Error ! ");

		}
		
	}
	
}


//Q1�G����BigInteger�n�নString�L�X?
//Q2�G�d��Throw�ҥ~���p