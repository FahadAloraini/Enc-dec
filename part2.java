import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class part2 {

    public static void main(String[] args) throws IOException {

        char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
                , 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
                , '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '.', '?', '!', ',', ';', ':', '-', '(', ')', '[', ']', '{', '}', '\'', '\"', ' ', '\n'};

        String FilePath2 = "/Users/bkash/Desktop/testing Path/p2.rsa";
        File myObj = new File(FilePath2);
        Scanner Fkb = new Scanner(myObj);


        String OutFilePath = FilePath2;
        int typeLocation = FilePath2.lastIndexOf('.');
        OutFilePath = OutFilePath.substring(0, typeLocation);
        OutFilePath = OutFilePath + ".dec";
        System.out.println(FilePath2 + "  " + OutFilePath);
        File myObjOut = new File(OutFilePath);
        if (myObjOut.createNewFile()) {
            System.out.println("File created: " + myObjOut.getName());
        } else {
            System.out.println("File already exists.");
        }
        FileWriter Wkb = new FileWriter(OutFilePath);








        String nText = "";
        while (Fkb.hasNext()) {
            nText = nText + Fkb.nextLine();      //get the Text
        }
        Long[] Prime1 = new Long[1229];
        Long[] Prime2 = new Long[1229];

       Prime1 = prime_N(10000);
        Prime2 = prime_N(10000);

        for (int o = 0; o < Prime1.length; o++) {
            for (int z = o; z < Prime2.length; z++) {
                long tempN = Prime1[o]*Prime2[z];
                System.out.println("N before" +tempN);
                if (tempN>7878 &&tempN<10000){

                    long fi = (Prime1[o]-1)*(Prime2[z]-1);
                    long n = Prime1[o]*Prime2[z];
                    System.out.println("n after = "+n);



                    for (int xx = 2; xx < fi; xx++) {
                        try {
                            BigInteger e = BigInteger.valueOf(xx);
                            Long d = (e.modInverse(BigInteger.valueOf(n))).longValue(); //Long.valueOf(xx);
                            int length = 4;




                            // System.out.println(nText);
                            int numberOfBlocks = (int) Math.ceil((double) nText.length() / (double) length);
//                int numberOfBlocks = (int) Math.ceil((double) nText.length() /(double) length);
                            String[] intArray = new String[numberOfBlocks];
                            for (int i = 0; i < intArray.length; i++) {
                                for (int j = 0; j < length; j++) {
                                    int y = i * length;                                         // divide to blocks
                                    if (intArray[i] == null) {
                                        intArray[i] = String.valueOf(nText.charAt(y));
                                    } else {
                                        intArray[i] = intArray[i] + nText.charAt(y + j);
                                    }
                                }
                            }
                            BigInteger b1, b2;
                           // System.out.println(Arrays.toString(intArray));
                            for (int i = 0; i < intArray.length; i++) {

//
//
                                b1 = new BigInteger(intArray[i]);
                                b2 = exponentMod(b1, d, n);
//                    b1 = b1.mod(BigInteger.valueOf(n));
//                    b2 = new BigInteger(intArray[i]);
//                    for (int j = 1; j < d; j++) {
//                        b2 = b2.mod(BigInteger.valueOf(n));
//                        b2 = b2.multiply(b1);
//                    }
//                    b2 = b2.mod(BigInteger.valueOf(n));


                                // b1 = new BigInteger(intArray[i]);
                                // BigInteger result1 = b1.pow(d);
                                BigInteger result2 = b2;  //result1.mod(BigInteger.valueOf(n));
                                String TempLength = result2.toString();
                                while (TempLength.length() < length) {
                                    TempLength = "0" + TempLength;
                                }
                                intArray[i] = TempLength;
                            }
                            //System.out.println(Arrays.toString(intArray));
                            String nTextAfter = "";
                            for (int i = 0; intArray.length > i; i++) {
                                nTextAfter = nTextAfter + intArray[i];
                            }
                            String finalText = "";
                            for (int i = 0; nTextAfter.length() > i; i = i + 2) {
                                String temp = nTextAfter.substring(i, i + 2);
                                finalText = finalText + characters[Integer.parseInt(temp)];
                            }

                            //System.out.println(finalText);
                            System.out.println("n = "+n+"-----------------------------------------");
                            Wkb.write("d = " + d + " n = "+n+" \n");
                            Wkb.write(finalText);
                            Wkb.write("\n \n \n \n \n \n");



                        } catch (ArrayIndexOutOfBoundsException error) {

                        }catch (ArithmeticException error2){

                        }
                    }
                }






                }

            }
        Wkb.close();

    }







        {
            {

            }

        }







       /* for (int i = 2; i < n; i++) {

           d = i;







            BigInteger b1,b2;
            System.out.println(Arrays.toString(intArray));
            for (int x = 0; x < intArray.length; x++) {

                b1 = new BigInteger(intArray[x]);
                b2 = exponentMod(b1,d,n);


                BigInteger result2 = b2;
                String TempLength = result2.toString();  //  encrept the blocks
                while (TempLength.length() < 6) {
                    TempLength = "0" + TempLength;
                }
                intArray[x] = TempLength;
            }
            System.out.println(Arrays.toString(intArray));

            nText = "";
            for (int y = 0;intArray.length>y;y++){
                nText = nText + intArray[y];
            }
            String finalText = "";
            for(int y = 0;nText.length()>y;y=y+2){
                String temp = nText.substring(y, y+2);
                finalText = finalText + characters[Integer.parseInt(temp)];
            }


            Wkb.write("d = "+ d + " \n");
            Wkb.write(finalText);
            Wkb.write("\n \n \n \n \n \n");

            Wkb.close();


        }*/





    static Long[] prime_N(int N)
    {
        // Declaring the variables
        int x, y, flg,w=0;
Long[] temp = new Long[1229];
        // Printing display message


        // Using for loop for traversing all
        // the numbers from 1 to N
        for (x = 1; x <= N; x++) {

            // Omit 0 and 1 as they are
            // neither prime nor composite
            if (x == 1 || x == 0)
                continue;

            // Using flag variable to check
            // if x is prime or not
            flg = 1;

            for (y = 2; y <= x / 2; ++y) {
                if (x % y == 0) {
                    flg = 0;
                    break;
                }
            }

            // If flag is 1 then x is prime but
            // if flag is 0 then x is not prime
            if (flg == 1) {
                temp[w] = Long.valueOf(x);
                w++;
            }
        }
        return temp;
    }

    static void SieveOfEratosthenes(int n,
                                    boolean isPrime[])
    {
        // Initialize all entries of boolean array
        // as true. A value in isPrime[i] will finally
        // be false if i is Not a prime, else true
        // bool isPrime[n+1];
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i <= n; i++)
            isPrime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            // If isPrime[p] is not changed, then it is
            // a prime
            if (isPrime[p] == true) {
                // Update all multiples of p
                for (int i = p * 2; i <= n; i += p)
                    isPrime[i] = false;
            }
        }
    }

    // Function to print a prime pair
    // with given product
    static void findPrimePair(int n)
    {
        int flag = 0;

        // Generating primes using Sieve
        boolean[] isPrime = new boolean[n + 1];
        SieveOfEratosthenes(n, isPrime);

        // Traversing all numbers to find first
        // pair
        for (int i = 2; i < n; i++) {
            int x = n / i;

            if (isPrime[i] && isPrime[x] && x != i
                    && x * i == n) {
                System.out.println(i + " " + x);
                flag = 1;
                return;
            }
        }

        if (flag == 0)
            System.out.println("No such pair found");
    }


    static BigInteger exponentMod(BigInteger A, long B, long C)
    {

        // Base cases
        if (A.equals(0))
            return BigInteger.valueOf(0);
        if (B == 0)
            return BigInteger.valueOf(1);

        // If B is even
        BigInteger y;
        if (B % 2 == 0)
        {
            y = exponentMod(A, B / 2, C);
            y = (y.multiply(y)).mod(BigInteger.valueOf(C));
        }

        // If B is odd
        else
        {
            y = A.mod(BigInteger.valueOf(C));
            y = (y.multiply(exponentMod(A, B - 1,  C).mod(BigInteger.valueOf(C))).mod(BigInteger.valueOf(C)));
        }

        return ((y.add(BigInteger.valueOf(C))).mod(BigInteger.valueOf(C)));
    }


    static int modInverse(Long a, Long m)
    {

        for (int x = 1; x < m; x++)
            if (((a%m) * (x%m)) % m == 1)
                return x;
        return 1;
    }



}

