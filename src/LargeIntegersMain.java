import java.util.Scanner;
import java.util.stream.IntStream;

public class LargeIntegersMain {
    public static void main(String[] args) {
        Scanner keyType = new Scanner(System.in);
        String  num1 = null, num2 = null;
        System.out.println("First number:");

        do {
            try {
                num1 = receiveInput(keyType);
            } catch (NumberFormatException n) {
                System.out.println("Not a valid number. Again.");
            }
        } while (num1 == null);

        System.out.println("Second number:");

        do {
            try {
                num2 = receiveInput(keyType);
            } catch (NumberFormatException n) {
                System.out.println("Not a valid number. Again.");
            }
        } while (num2 == null);

        //convert @num1 and @num2 to int arrays
        int[] nums1 = toIntArray(num1, Math.max(num1.length(), num2.length()));
        int[] nums2 = toIntArray(num2, Math.max(num1.length(), num2.length()));

        //combine @nums1 and @nums2 into 1 array
        int[] result = IntStream.range(0, Math.max(num1.length(), num2.length()))
                                .map(i -> nums1[i] + nums2[i])
                                .toArray();
        //carry over places with double digit numbers
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] > 9) {
                int carryOver = Integer.parseInt(Character.toString(Integer.toString(result[i]).charAt(0)));

                result[i] = Integer.parseInt(Character.toString(Integer.toString(result[i]).charAt(1)));

                if (i != 0) {
                    result[i - 1] += carryOver;
                }
            }
        }

        System.out.println(toString(result));
    }

    //check if input is valid
    public static String receiveInput(Scanner scanner) throws NumberFormatException{
        String input = scanner.next();

        for (int i = 0; i < input.length(); i++) {
            Integer.parseInt(Character.toString(input.charAt(i)));
        }

        return input;
    }

    static int[] toIntArray(String num) {
        return toIntArray(num, num.length());
    }

    //convert string of numbers to int array
    static int[] toIntArray(String num, int length) {
        int[] digitArray = new int[length];

        int stringIndex = num.length() - 1;
        for (int i = length - 1; i >= 0; i--) {
            if (stringIndex < 0)
                break;
            digitArray[i] = Integer.parseInt(Character.toString(num.charAt(stringIndex)));
            stringIndex--;
        }

        return digitArray;
    }

    //convert int array to single string
    static String toString(int[] nums) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int num : nums) {
            stringBuilder.append(num)
                         .append(", ");
        }

        stringBuilder = new StringBuilder(stringBuilder.toString().trim()).deleteCharAt(stringBuilder.lastIndexOf(","));

        return stringBuilder.toString();
    }
}
