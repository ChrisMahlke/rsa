import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private final BigInteger n; // RSA modulus
    private BigInteger e; // RSA public exponent
    private final BigInteger d; // RSA private exponent

    /**
     * Constructor to generate RSA keys.
     * @param bitLength The bit length for the keys.
     */
    public RSA(int bitLength) {
        // Generate two distinct large prime numbers p and q
        // Secure random number generator
        SecureRandom secureRandom = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength / 2, secureRandom);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, secureRandom);
        n = p.multiply(q); // Compute n as the product of p and q

        // Compute Euler's totient function phi(n)
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Generate a random public exponent e that is coprime with phi
        do {
            e = BigInteger.probablePrime(bitLength / 2, secureRandom);
        } while (!phi.gcd(e).equals(BigInteger.ONE));

        // Compute the private exponent d
        d = e.modInverse(phi);
    }

    /**
     * Converts the input string into its binary representation.
     * @param input The input string.
     * @return The binary representation of the input string.
     */
    public String convertToAscii(String input) {
        StringBuilder binaryStr = new StringBuilder();
        for (char ch : input.toCharArray()) {
            String binary = String.format("%8s", Integer.toBinaryString(ch)).replace(' ', '0');
            binaryStr.append(binary);
        }
        return binaryStr.toString();
    }

    /**
     * Encrypts and decrypts a given message.
     * @param msg The message to be encrypted and decrypted.
     */
    public void calculate(String msg) {
        try {
            // Convert message to binary string
            String binaryStr = convertToAscii(msg);
            System.out.println("Binary String: " + binaryStr);

            // Convert binary string to BigInteger
            BigInteger intMsg = new BigInteger(binaryStr, 2);
            System.out.println("Integer Message: " + intMsg);

            // Encrypt the integer message
            BigInteger cipherText = intMsg.modPow(e, n);
            System.out.println("Ciphertext: " + cipherText);

            // Decrypt the ciphertext
            BigInteger decryptedMsg = cipherText.modPow(d, n);
            String decryptedBinaryStr = decryptedMsg.toString(2);

            // Ensure the binary string has a length that's a multiple of 8
            while (decryptedBinaryStr.length() % 8 != 0) {
                decryptedBinaryStr = "0" + decryptedBinaryStr;
            }

            System.out.println("Decrypted Binary String: " + decryptedBinaryStr);

            // Convert binary string back to text
            StringBuilder decryptedText = new StringBuilder();
            for (int i = 0; i < decryptedBinaryStr.length(); i += 8) {
                String byteStr = decryptedBinaryStr.substring(i, i + 8);
                char ch = (char) Integer.parseInt(byteStr, 2);
                decryptedText.append(ch);
            }
            System.out.println("Decrypted Text: " + decryptedText.toString());
        } catch (Exception ex) {
            System.err.println("An error occurred during encryption/decryption: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int bitLength = 512; // You can change the bit length for more security
        RSA rsa = new RSA(bitLength);
        rsa.calculate("Give me an A");
    }
}
