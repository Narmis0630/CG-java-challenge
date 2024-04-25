import java.util.*;

public class MemoryGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input N - total number of numbers shown to the team
        int N = scanner.nextInt();

        // Input the N numbers
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = scanner.nextInt();
        }

        // Input Q - total number of queries
        int Q = scanner.nextInt();

        // Input the queries and count occurrences
        for (int i = 0; i < Q; i++) {
            int query = scanner.nextInt();
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (numbers[j] == query) {
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("NOT PRESENT");
            } else {
                System.out.println(count);
            }
        }

        scanner.close();
    }
}