## CS 51 - RSA Encryption in Java

This project demonstrates an advanced RSA encryption algorithm implemented in Java. The code includes secure random key generation, exception handling, and encryption with decryption capabilities.

### Getting Started

#### Prerequisites

- Java Development Kit (JDK) 8 or higher

#### Running the Code

1. **Compile the Code:**
   Open a terminal or command prompt and navigate to the directory containing the `EnhancedRSA.java` file. Run the following command to compile the code:

   ```sh
   javac EnhancedRSA.java

### Code Explanation

- **EnhancedRSA Class:**
    - `n`, `e`, and `d` are the RSA keys where `n` is the modulus, `e` is the public exponent, and `d` is the private exponent.
    - The constructor `EnhancedRSA(int bitLength)` initializes the keys with secure random prime numbers.
    - `convertToAscii(String input)` method converts the input string into its binary representation.
    - `calculate(String msg)` method performs the following steps:
        - Converts the message into a binary string.
        - Converts the binary string into a BigInteger.
        - Encrypts the integer message using the RSA algorithm and prints the ciphertext.
        - Decrypts the ciphertext back to the original message and prints it.

### Example Output

Running the provided code with the message "Give me an A" will produce an output similar to the following:

```
Binary String: 010001110110100101110110011001010010000001101101011001010010000001100001011011100010000001000001
Integer Message: 8937694091556108062443830530
Ciphertext: <cipher text>
Decrypted Binary String: 010001110110100101110110011001010010000001101101011001010010000001100001011011100010000001000001
Decrypted Text: Give me an A
```

### Modifying the Code

- **Changing the Bit Length:**
  You can increase the bit length for more security by modifying the `bitLength` variable in the `main` method:

  ```java
  int bitLength = 1024; // Example for increased security
  ```

- **Changing the Message:**
  Modify the string passed to the `calculate` method in the `main` function:

  ```java
  rsa.calculate("Your new message");
  ```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.