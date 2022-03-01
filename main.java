
import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {


        char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
                , 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
                , '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '.', '?', '!', ',', ';', ':', '-', '(', ')', '[', ']', '{', '}', '\'', '\"', ' ', '\n'};

        Scanner kb = new Scanner(System.in);
        System.out.println("encrypt enter 1 \n decrypt enter 2");
        int type = kb.nextInt();

        if(type == 1) {
            {                   //Start encrypt here
                System.out.println("Enter the path of the txt file");
                kb.nextLine();
                String FilePath =  kb.nextLine();
                File myObj = new File(FilePath);
                Scanner Fkb = new Scanner(myObj);

                String tempString = Fkb.nextLine();
                String[] parts = tempString.split(" ");
                int e = Integer.parseInt(parts[0]);
                long n = Long.parseLong((parts[1]));

                int length = 2;

                String tempLength1 = "78";
                String tempLength2 = "7878";


                for (int i = 0; ; i++) {
                    if (n > Long.parseLong(tempLength1) && n < Long.parseLong(tempLength2)) {
                        break;
                    } else {
                        tempLength1 = tempLength1 + "78";
                        tempLength2 = tempLength2 + "78";             // get the block size
                        length = length + 2;
                    }
                }


                String Text = "";
                while (Fkb.hasNext()) {
                    Text = Text + Fkb.nextLine() + "\n" ;     //get the Text
                }
//         Text = "STOP";
                //System.out.println(Text);
                String nText = "";
                char TempChar;
                for (int i = 0; i < Text.length(); i++) {
                    TempChar = Text.charAt(i);                  //get the text in numbers
                    if (findIndex(characters, TempChar) < 10)
                        nText = nText + 0 + findIndex(characters, TempChar);
                    else
                        nText = nText + findIndex(characters, TempChar);
                }


                int numberOfBlocks = (int) Math.ceil((double) nText.length() /(double) length);
                //System.out.println(numberOfBlocks);
               // System.out.println(length);
                String[] intArray = new String[numberOfBlocks];
                for (int i = 0; i < intArray.length; i++) {
                    for (int j = 0; j < length; j++) {
                        int y = i * length;
                        if (intArray[i] == null) {
                            if (nText.length() < y) {
                                intArray[i] = "23";        // divaide the numbered text to blocks
                            } else
                                intArray[i] = String.valueOf(nText.charAt(y));
                        } else {
                            if (nText.length() <= (y + j)) {
                                intArray[i] = intArray[i] + "23";
                                j++;

                            }
                            else
                                intArray[i] = intArray[i] + nText.charAt(y + j);
                        }
                    }
                }

                BigInteger b1,b2;
                System.out.println(Arrays.toString(intArray));
                for (int i = 0; i < intArray.length; i++) {

                         b1 = new BigInteger(intArray[i]);
                        b2 = exponentMod(b1,e,n);
//                    b1 = b1.mod(BigInteger.valueOf(n));
//                    b2 = new BigInteger(intArray[i]);
//                    for (int j = 1; j < e; j++) {
//                        b2 = b2.mod(BigInteger.valueOf(n));
//                        b2 = b2.multiply(b1);
//                    }
//                    b2 = b2.mod(BigInteger.valueOf(n));

                    //BigInteger result1 = b1.pow(e);
                    BigInteger result2 = b2;//result1.mod(BigInteger.valueOf(n));
                    String TempLength = result2.toString();  //  encrept the blocks
                    while (TempLength.length() < length) {
                        TempLength = "0" + TempLength;
                    }
                    intArray[i] = TempLength;
                }
                System.out.println(Arrays.toString(intArray));


                String OutFilePath = FilePath;
                int typeLocation = FilePath.lastIndexOf('.');
                OutFilePath = OutFilePath.substring(0, typeLocation);
                OutFilePath = OutFilePath + ".rsa";
                System.out.println(FilePath + "  " + OutFilePath);
                File myObjOut = new File(OutFilePath);
                if (myObjOut.createNewFile()) {
                    System.out.println("File created: " + myObjOut.getName());
                } else {
                    System.out.println("File already exists.");
                }

                FileWriter Wkb = new FileWriter(OutFilePath);
                for (int i = 0; i < intArray.length; i++) {
                    Wkb.write(intArray[i]);
                }
                Wkb.close();


//        System.out.println(e);
//        System.out.println(n);
//        System.out.println(length);
//        System.out.println(Text);
//        System.out.println(nText);
            }               //End encrypt here
        }else if(type == 2){
            {
                System.out.println("Enter the path of the rsa file");
                kb.nextLine();
                String FilePath2 = kb.nextLine();
                File myObj = new File(FilePath2);
                Scanner Fkb = new Scanner(myObj);

                System.out.println("enter d");
                long d = kb.nextLong();
                System.out.println("enter n");
                long n = kb.nextLong();

                int length = 2;

                String tempLength1 = "78";
                String tempLength2 = "7878";


                for (int i = 0; ; i++) {
                    if (n > Long.parseLong(tempLength1) && n < Long.parseLong(tempLength2)) {
                        break;
                    } else {
                        tempLength1 = tempLength1 + "78";
                        tempLength2 = tempLength2 + "78";             // get the block size
                        length = length + 2;
                    }
                }
                String nText = "";
                while (Fkb.hasNext()) {
                    nText = nText + Fkb.nextLine();      //get the Text
                }
               // System.out.println(nText);
                int numberOfBlocks = (int) Math.ceil((double) nText.length() /(double) length);
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
                BigInteger b1,b2;
                System.out.println(Arrays.toString(intArray));
                for (int i = 0; i < intArray.length; i++) {

//
//
                       b1 = new BigInteger(intArray[i]);
                       b2 = exponentMod(b1,d,n);
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
                System.out.println(Arrays.toString(intArray));
                nText = "";
                for (int i = 0;intArray.length>i;i++){
                    nText = nText + intArray[i];
                }
                String finalText = "";
                for(int i = 0;nText.length()>i;i=i+2){
                    String temp = nText.substring(i, i+2);
                    finalText = finalText + characters[Integer.parseInt(temp)];
                }

                //System.out.println(finalText);




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

                Wkb.write(finalText);

                Wkb.close();

            }

        }else{System.out.println("type is wrong");}
    }




    public static int findIndex(char arr[], char t) {


        // find length of array
        int len = arr.length;
        int i = 0;

        // traverse in the array
        while (i < len) {

            // if the i-th element is t
            // then return the index
            if (arr[i] == t) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
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

}