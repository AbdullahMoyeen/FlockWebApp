package com.viiup.web.flock.helpers;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;


/*
* @author Mahabubul Alam
* Introduction to Information Security - Programming Project, Spring 2016
* EMSE, UT Dallas.
*
*
*/

public class CryptoUtils {
	
	public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA256";
	public static final String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
	public static final String HMAC_ALGORITHM = "HmacSHA256";
	public static final String AES_MODE = "AES/CBC/PKCS5Padding";
	
	public static final int SALT_BYTE_SIZE = 32;
	public static final int HMAC_BYTE_SIZE = 32;
	public static final int KEY_LENGTH = 32;
	public static final int BLOCK_SIZE = 16;
	public static final int PBKDF2_ITERATIONS = 100000;
	
	
	

//Key Derivation function, uses password, salt, iteration count and key length.
public byte[] deriveKey(String password, byte[] salt, int iterations, int keylength) throws NoSuchAlgorithmException, InvalidKeySpecException {
	PBEKeySpec ks = new PBEKeySpec(password.toCharArray(), salt, iterations, keylength);
	SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
	return skf.generateSecret(ks).getEncoded();
	
}


//Initialization Vector Generator
public byte[] generateIv() throws NoSuchAlgorithmException{
	SecureRandom random = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM);
	byte[] ivBytes = new byte[BLOCK_SIZE];
	random.nextBytes(ivBytes);
	return ivBytes;
}	



//Salt Generator
public byte[] generateSalt() throws NoSuchAlgorithmException{
	SecureRandom random = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM);
	byte[] saltBytes = new byte[SALT_BYTE_SIZE];
	random.nextBytes(saltBytes);
	return saltBytes;
}



//HMAC Key Generator
public byte [] generateHmacKey(String password, byte[] hmacsalt) throws NoSuchAlgorithmException, InvalidKeySpecException{
	byte[] hmacKeyBytes = deriveKey(password, hmacsalt, PBKDF2_ITERATIONS, KEY_LENGTH*8);
	return hmacKeyBytes;
}



//AES Key Generator
public byte[] generateAesKey(String password, byte[] aessalt) throws NoSuchAlgorithmException, InvalidKeySpecException{
	byte[] aesKeyBytes = deriveKey(password, aessalt, PBKDF2_ITERATIONS, KEY_LENGTH*8);
	return aesKeyBytes;
}



//Generate HMAC
public byte[] generateHmac(byte[] bytesToEncode, byte[] hmacKey) throws NoSuchAlgorithmException, InvalidKeyException{
	SecretKeySpec hks = new SecretKeySpec(hmacKey, HMAC_ALGORITHM);
	Mac m = Mac.getInstance(HMAC_ALGORITHM);
	m.init(hks);
	byte[] hmacBytes = m.doFinal(bytesToEncode);
	return hmacBytes;
}
	

//encrypt using AES 256
public byte[] encryptData(String plainText, byte[] encryptionKey, byte[] initVector) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
	SecretKeySpec eks = new SecretKeySpec(encryptionKey, "AES");
	IvParameterSpec ivspec = new IvParameterSpec(initVector);
	Cipher c = Cipher.getInstance(AES_MODE);
	c.init(Cipher.ENCRYPT_MODE, eks, ivspec);
	byte[] encryptedBytes = c.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
	return encryptedBytes;
	
}
	
//decrypt using AES 256	
public String decryptData(byte[] bytesToDecrypt, byte[] decryptionKey, byte[] initVector) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
	SecretKeySpec dks = new SecretKeySpec(decryptionKey, "AES");
	IvParameterSpec ivspec = new IvParameterSpec(initVector);
	Cipher c= Cipher.getInstance(AES_MODE);
	c.init(Cipher.DECRYPT_MODE, dks, ivspec);
	byte[] decryptedBytes = c.doFinal(bytesToDecrypt);
	return new String(decryptedBytes, StandardCharsets.UTF_8);
}
	
	
	
	
}
