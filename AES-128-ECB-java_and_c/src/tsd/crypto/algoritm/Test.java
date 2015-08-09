package tsd.crypto.algoritm;

public class Test {

	public static void printHexString(byte[] b) {
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			if(i%16 == 0&& i!=0) {
				System.out.println();
			}
			System.out.print(hex.toLowerCase());
			
		}
		System.out.println();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		byte[] key16 = { (byte) 0x2b, (byte) 0x7e, (byte) 0x15, (byte) 0x16,
				(byte) 0x28, (byte) 0xae, (byte) 0xd2, (byte) 0xa6,
				(byte) 0xab, (byte) 0xf7, (byte) 0x15, (byte) 0x88,
				(byte) 0x09, (byte) 0xcf, (byte) 0x4f, (byte) 0x3c };

		byte[] in16 = { (byte) 0x6b, (byte) 0xc1, (byte) 0xbe, (byte) 0xe2,
				(byte) 0x2e, (byte) 0x40, (byte) 0x9f, (byte) 0x96,
				(byte) 0xe9, (byte) 0x3d, (byte) 0x7e, (byte) 0x11,
				(byte) 0x73, (byte) 0x93, (byte) 0x17, (byte) 0x2a,
				
				(byte) 0xae, (byte) 0x2d, (byte) 0x8a, (byte) 0x57,
				(byte) 0x1e, (byte) 0x03, (byte) 0xac, (byte) 0x9c,
				(byte) 0x9e, (byte) 0xb7, (byte) 0x6f, (byte) 0xac,
				(byte) 0x45, (byte) 0xaf, (byte) 0x8e, (byte) 0x51,
				
				(byte) 0x30, (byte) 0xc8, (byte) 0x1c, (byte) 0x46,
				(byte) 0xa3, (byte) 0x5c, (byte) 0xe4, (byte) 0x11,
				(byte) 0xe5, (byte) 0xfb, (byte) 0xc1, (byte) 0x19,
				(byte) 0x1a, (byte) 0x0a, (byte) 0x52, (byte) 0xef,
				
				(byte) 0xf6, (byte) 0x9f, (byte) 0x24, (byte) 0x45,
				(byte) 0xdf, (byte) 0x4f, (byte) 0x9b, (byte) 0x17,
				(byte) 0xad, (byte) 0x2b, (byte) 0x41, (byte) 0x7b,
				(byte) 0xe6, (byte) 0x6c, (byte) 0x37, (byte) 0x10 };
		
		
		byte[] encodebytes = AES.encrypt(in16, key16);

		System.out.println("plain text:");
		printHexString(in16);
		System.out.println();
		System.out.println("key:");
		printHexString(key16);
		System.out.println();
		System.out.println("ciphertext:");
		printHexString(encodebytes);
	}

}
