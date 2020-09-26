import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

// https://www.hackerrank.com/challenges/maximum-palindromes/problem
class Result {
    static String operationalString;
    static int modulus = 1_00_00_00_007;
    static String h = Long.toBinaryString(modulus - 2);
    static int[] factorial = new int[100001];
    static int[][] mapped;

    /*
     * Complete the 'initialize' function below.
     *
     * The function accepts STRING s as parameter.
     */

    public static void initialize(String s) {
        long starting = System.nanoTime();
        operationalString = s;
        mapped = new int[s.length() + 1][26];
        for (int i = 0; i < s.length(); i++) {
            mapped[i][s.charAt(i) - 'a'] = 1;
        }
        for (int i = s.length() - 1; i >= 1; i--) {
            for (int j = 0; j < 26; j++) {
                mapped[i - 1][j] += mapped[i][j];
            }
        }

        long fact = 1;
        factorial[0] = 1;
        for (int i = 1; i <= 100000; i++) {
            fact = (fact * i) % modulus;
            factorial[i] = (int) fact;
        }
        inititalize += (System.nanoTime() - starting);
    }

    /*
     * Complete the 'answerQuery' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER l
     *  2. INTEGER r
     */

    static long mapping_time = 0;
    static long half_time = 0;
    static long factorialCalculation = 0;
    static long returnTime = 0;
    static long inititalize = 0;

    public static int answerQuery(int l, int r) {
        l -= 1;
        long starting = System.nanoTime();
        int[] frequencies = new int[26];
        for (int i = 0; i < 26; i++) {
            frequencies[i] = mapped[l][i] - mapped[r][i];
        }
        mapping_time += (System.nanoTime() - starting);

        starting = System.nanoTime();
        int odd = 0;
        int[] half = new int[26];
        for ( int i=0;i<26;i++){
            int frequency = frequencies[i];
            half[i] = (frequency / 2);
            if (frequency % 2 != 0)
                odd++;
        }
        half_time += (System.nanoTime() - starting);

        starting = System.nanoTime();
        int sum = 0;
        long fact = 1;
        for (int y : half) {
            sum += y;
            fact = (fact * factorial[y]) % modulus;
        }
        factorialCalculation += (System.nanoTime() - starting);

        starting = System.nanoTime();
        long factorialNumerator = factorial[sum];
        long modInverse = modInverse(fact);
        long result = (factorialNumerator * modInverse) % modulus;
        returnTime += (System.nanoTime() - starting);
        return (int) (result * Math.max(1, odd) % modulus);
    }

    public static long modInverse(long fact) {
        long y = 1;

        for (int i = 0; i < h.length(); i++) {
            y = (y * y) % modulus;
            char ch = h.charAt(i);
            if (ch == '1')
                y = (y * fact) % modulus;
        }
        return y;
    }

}

public class MaximumPalindromeCount {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String s = bufferedReader.readLine();

        Result.initialize(s);

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int l = Integer.parseInt(firstMultipleInput[0]);

                int r = Integer.parseInt(firstMultipleInput[1]);

                int result = Result.answerQuery(l, r);

                System.out.println(result);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

//        System.out.println(Result.mapping_time);
//        System.out.println(Result.half_time);
//        System.out.println(Result.factorialCalculation);
//        System.out.println(Result.returnTime);
        bufferedReader.close();
    }
}
