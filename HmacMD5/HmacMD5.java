package cn.creditease.fso.cupid.controller.normandy.util;

import java.security.Security;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
/**
 * MAC消息摘要组件
 * @author kongqz
 * */
public class HmacMD5 {
///////////////////////////HmacMD5///////////////////////////////
	public static byte[] initHmacMD5Key() throws Exception{  
        //加入BouncyCastleProvider的支持  
        Security.addProvider(new BouncyCastleProvider());  
        //初始化KeyGenerator  
        KeyGenerator keyGenerator=KeyGenerator.getInstance("HmacMD5");  
        //产生密钥  
        SecretKey secretKey=keyGenerator.generateKey();  
        //获取密钥  
        return secretKey.getEncoded();  
    }  
	
    /** 
     * HmacMD5消息摘要 
     * @param data 待做摘要处理的数据 
     * @param key 密钥 
     * @return  byte[] 消息摘要 
     * */  
    public static byte[] encodeHmacMD5(byte[] data,byte[] key) throws Exception{  
        //加入BouncyCastleProvider的支持  
        Security.addProvider(new BouncyCastleProvider());  
        //还原密钥，因为密钥是以byte形式为消息传递算法所拥有  
        SecretKey secretKey=new SecretKeySpec(key,"HmacMD5");  
        //实例化Mac  
        Mac mac=Mac.getInstance(secretKey.getAlgorithm());  
        //初始化Mac  
        mac.init(secretKey);  
        //执行消息摘要处理  
        return mac.doFinal(data);  
    }  
    /** 
     * HmacMD2Hex消息摘要 
     * @param data 待做消息摘要处理的数据 
     * @param String 密钥 
     * @return byte[] 消息摘要 
     * */  
    public static String encodeHmacMD5Hex(byte[] data,byte[] key) throws Exception{  
        //执行消息摘要处理  
        byte[] b=encodeHmacMD5(data,key);  
        //做十六进制转换  
        return new String(Hex.encode(b));  
    }  
    
    public static String encodeHmacMD5Hex(String data,String key) throws Exception{  
        //执行消息摘要处理  
        byte[] b=encodeHmacMD5(data.getBytes(),key.getBytes());  
        //做十六进制转换  
        return new String(Hex.encode(b));  
    }  
    
    public static void main(String args[]) throws Exception {
    	String a = "最好现在用post。要不都调完了我们还得改一次";
    	
    	String key = "1234567890123456";
    	System.out.println(key);
    	
    	System.out.println(encodeHmacMD5Hex(a, key));
    }
}
