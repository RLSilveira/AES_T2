package br.com.pucrs.segurancasistemas.t2;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class AES {

    String _mode;
    SecretKeySpec _secretKey;
    byte[] _key;

    public AES(String mode, String key) {

        // Choose mode/algorithm
        this._mode = mode.equals("CBC") ? "AES/CBC/PKCS5Padding" : "AES/CTR/NoPadding";

        // Secret key
        byte[] keyb = DatatypeConverter.parseHexBinary(key);
        _secretKey = new SecretKeySpec(keyb, 0, keyb.length, "AES");

    }

    public String encrypt(String strToEncrypt) {
        try {

            // Create IvParameterSpec - IV nos primeiros 16 bytes
            byte[] bIV = DatatypeConverter.parseHexBinary(strToEncrypt.substring(0, 32));
            IvParameterSpec ivSpec = new IvParameterSpec(bIV);

            Cipher cipher = Cipher.getInstance(_mode);
            cipher.init(Cipher.ENCRYPT_MODE, _secretKey, ivSpec);

            byte[] bArrayToEncrypt = DatatypeConverter.parseHexBinary(strToEncrypt);
            byte[] result = cipher.doFinal(bArrayToEncrypt);

            return DatatypeConverter.printHexBinary(result);

        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public String decrypt(String strToDecrypt) {
        try {

            // Create IvParameterSpec - IV nos primeiros 16 bytes
            byte[] bIV = DatatypeConverter.parseHexBinary(strToDecrypt.substring(0, 32));
            IvParameterSpec ivSpec = new IvParameterSpec(bIV);

            Cipher cipher = Cipher.getInstance(_mode);
            cipher.init(Cipher.DECRYPT_MODE, _secretKey, ivSpec);

            // Remove IV do inicio do texto cifrado
            byte[] bArrayToDecrypt = DatatypeConverter.parseHexBinary(strToDecrypt.substring(32));
            byte[] result = cipher.doFinal(bArrayToDecrypt);

            return new String(result);
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

}