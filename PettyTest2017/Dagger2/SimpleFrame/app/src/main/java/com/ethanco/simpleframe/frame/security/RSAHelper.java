package com.ethanco.simpleframe.frame.security;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Description  私钥使用PCKS8编码，公钥使用X509编码
 * Created by chenqiao on 2015/9/18.
 */
public class RSAHelper {

    /**
     * Key algorithm to be used in this class.
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * Default key length. The value shall be in the range of 512-65536.
     */
    private static final int KEY_LENGTH = 1024;

    /**
     * Identifier of pubic key in the Map.
     */
    public static final String PUBLIC_KEY = "PublicKey";

    /**
     * Identifier of private key in the Map.
     */
    public static final String PRIVATE_KEY = "PrivateKey";

    /**
     * Algorithm to be used for signature and verification.
     */
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * Generate a RSA key pair with public key and private key.
     *
     * @param keyMap to save the key pair, the public key is indicated by
     *               "PublicKey" and private key is indicated by "PrivateKey".
     * @return
     */
    public static boolean generateKeyPair(Map<String, Object> keyMap) {
        //Get one instance of KeyPairGenerator with RSA.
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RSAHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        //Get a random seed.
        SecureRandom secureRandom = new SecureRandom();

        //Use current datetime as random seed.
        String currentDateTime = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault()).format(new Date());
        secureRandom.setSeed(currentDateTime.getBytes());

        //Initialze the key generator with the random number provider. We need to call initialze everytime to get a NEW key pair.
        keyPairGenerator.initialize(KEY_LENGTH, secureRandom);

        //Ready to generate a key pair.
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        //Get public and private key.
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        keyMap.put(PUBLIC_KEY, publicKey.getEncoded());
        keyMap.put(PRIVATE_KEY, privateKey.getEncoded());
        return true;
    }

    /**
     * Save the keys into the key file.
     *
     * @param keyPair            key pair to be saved into the file.
     * @param publicKeyFileName  file to save public key.
     * @param privateKeyFileName file to save private key.
     */
    public static void saveKeyPair(Map<String, Object> keyPair, String publicKeyFileName, String privateKeyFileName) {
        //Write public key into the key file.
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(publicKeyFileName);
            byte[] publicKey = (byte[]) keyPair.get(PUBLIC_KEY);
            fileOutputStream.write(publicKey);
            fileOutputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RSAHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RSAHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Write private key into the key file.
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(privateKeyFileName);
            byte[] privateKey = (byte[]) keyPair.get(PRIVATE_KEY);
            fileOutputStream.write(privateKey);
            fileOutputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RSAHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RSAHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get the key from local file.
     *
     * @param keyFileName file containing the key.
     * @return byte array containing the key.
     */
    public static byte[] getKey(String keyFileName) {
        byte[] keyBytes = null;
        //Get key from file.
        try {
            File file = new File(keyFileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);

            //Allocate the buffer.
            keyBytes = new byte[(int) file.length()];

            //Read them all.
            dataInputStream.readFully(keyBytes);

            dataInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RSAHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RSAHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return keyBytes;
    }

    /**
     * Encrypt the data with the public key.
     *
     * @param data           data to be encrypted.
     * @param offset         offset of data to be encrypted.
     * @param length         length of data to be encrypted.
     * @param publicKeyBytes public key in binary format.
     * @return encrypted data.
     */
    public static byte[] encryptWithPublicKey(byte[] data, int offset, int length, byte[] publicKeyBytes) {

        byte[] encryptedData = null;

        try {
            //Create a new X509EncodedKeySpec with the given encoded key.
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);

            //RSA key factory.
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

            //Get the public key from the provided key specification.
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            //Init the ciper.
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

            //Encrypt mode!
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            //Do the encryption now !!!!
            encryptedData = cipher.doFinal(data, offset, length);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(RSAHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return encryptedData;
    }

    /**
     * Encrypt the data with private key.
     *
     * @param data            data to be encrypted.
     * @param offset          offset of data to be encrypted.
     * @param length          length of data to be encrypted.
     * @param privateKeyBytes private key in binary format.
     * @return encrypted data.
     */
    public static byte[] encryptWithPrivateKey(byte[] data, int offset, int length, byte[] privateKeyBytes) {
        byte[] encryptedData = null;

        try {
            // Create a new PKCS8EncodedKeySpec with the given encoded key.
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

            //RSA key factory.
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

            //Get the private key from the provided key specification.
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            //Init the ciper.
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

            //Encrypt mode!
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            //Do the encryption now !!!!
            encryptedData = cipher.doFinal(data, offset, length);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(RSAHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return encryptedData;
    }

    /**
     * Decrypt data with public key.
     *
     * @param data           data to be decrypted.
     * @param offset         offset of data to be decrypted.
     * @param length         length of data to be decrypted.
     * @param publicKeyBytes public key in binary format.
     * @return decrypted data.
     */
    public static byte[] decryptWithPublicKey(byte[] data, int offset, int length, byte[] publicKeyBytes) {

        byte[] encryptedData = null;

        try {
            //Create a new X509EncodedKeySpec with the given encoded key.
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);

            //RSA key factory.
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

            //Get the public key from the provided key specification.
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            //Init the ciper.
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

            //Decrypt mode!
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            //Do the decryption now !!!!
            encryptedData = cipher.doFinal(data, offset, length);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(RSAHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return encryptedData;
    }

    /**
     * Decrypt the data with private key bytes.
     *
     * @param data            data to be decrypted.
     * @param offset          offset of data to be decrypted.
     * @param length          length of data to be decrypted.
     * @param privateKeyBytes private key in binary format.
     * @return decrypted data.
     */
    public static byte[] decryptWithPrivateKey(byte[] data, int offset, int length, byte[] privateKeyBytes) {
        byte[] encryptedData = null;

        try {
            // Create a new PKCS8EncodedKeySpec with the given encoded key.
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

            //RSA key factory.
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

            //Get the private key from the provided key specification.
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            //Init the ciper.
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

            //Decrypt mode!
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            //Do the decryption now !!!!
            encryptedData = cipher.doFinal(data, offset, length);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(RSAHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return encryptedData;
    }

    /**
     * Decrypt the data with private key.
     *
     * @param data       data to be decrypted.
     * @param offset     offset of data to be decrypted.
     * @param length     length of data to be decrypted.
     * @param privateKey private key.
     * @return decrypted data.
     */
    public static byte[] decryptWithPrivateKey(byte[] data, int offset, int length, PrivateKey privateKey) {
        byte[] encryptedData = null;

        try {
            //RSA key factory.
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);


            //Init the ciper.
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

            //Decrypt mode!
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            //Do the decryption now !!!!
            encryptedData = cipher.doFinal(data, offset, length);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(RSAHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return encryptedData;
    }

    /**
     * Sign the data with the private key.
     *
     * @param data            data to be signed.
     * @param offset          offset of data to be signed.
     * @param length          length of data to be signed.
     * @param privateKeyBytes private key in binary format.
     * @return signed data.
     */
    public static byte[] sign(byte[] data, int offset, int length, byte[] privateKeyBytes) {
        byte[] signedData = null;
        try {
            // Create a new PKCS8EncodedKeySpec with the given encoded key.
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

            //RSA key factory.
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

            //Get the private key from the provided key specification.
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            //Create the Signature instance with RSA MD5.
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);

            //Use private key to do the signature.
            signature.initSign(privateKey);

            //Updates the data to be signed or verified, using the specified array of bytes.
            signature.update(data, offset, length);

            //Sign it now.
            signedData = signature.sign();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | SignatureException ex) {
            Logger.getLogger(RSAHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return signedData;
    }

    /**
     * Verify the signature.
     *
     * @param data           data signed.
     * @param offset         offset of data to be verified.
     * @param length         length of data to be verified.
     * @param publicKeyBytes public key in binary format.
     * @param dataSignature  signature for the data.
     * @return Whether the signature is fine.
     */
    public static boolean verify(byte[] data, int offset, int length, byte[] publicKeyBytes, byte[] dataSignature) {
        boolean result = false;
        try {
            //Create a new X509EncodedKeySpec with the given encoded key.
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);

            //RSA key factory.
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

            //Get the public key from the provided key specification.
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            //Create the Signature instance with RSA MD5.
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);

            //Use pubic key to verify the signature.
            signature.initVerify(publicKey);

            //Updates the data to be signed or verified, using the specified array of bytes.
            signature.update(data, offset, length);

            //Verifies the passed-in signature.
            result = signature.verify(dataSignature);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | SignatureException | InvalidKeyException ex) {
            Logger.getLogger(RSAHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}