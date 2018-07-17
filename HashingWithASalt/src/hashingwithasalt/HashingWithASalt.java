
package hashingwithasalt;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
/**
 *
 * @author Tiffany holden
 */
public class HashingWithASalt {

    static Scanner scannerIn = new Scanner(System.in);
    static String password;
    static char[] password1;
    private static final Random RANDOM = new SecureRandom();
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    static byte[] salt1;
    static byte[] keyEncoded;
     public static void main(String[] args) {
        try{
          
        System.out.println("Enter password: ");
        password = scannerIn.nextLine();
        password1 = password.toCharArray();
        getSalt();
        hashPassword();
        System.out.println("Hash with Salt Successful: " + Arrays.toString(keyEncoded));
        }
        catch(Exception e) {
            System.out.println("Attempt unsuccessful: " + e);
        }
        finally{
            password = null;
            password1 = null;
            salt1=null;
        }
    }
    
    public static byte[] hashPassword(){
        
        try{
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password1, salt1, ITERATIONS, KEY_LENGTH);
            SecretKey key = skf.generateSecret(spec);
            keyEncoded = key.getEncoded();
            return keyEncoded;
        }
        catch(NoSuchAlgorithmException | InvalidKeySpecException e){
            throw new RuntimeException(e);
    }
}
  public static byte[] getSalt(){
      salt1 = new byte[16];
      RANDOM.nextBytes(salt1);
      return salt1;
  }  
}

