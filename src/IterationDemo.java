/*
 * Name:        Lesson 4: Loops and Iteration (IterationDemo.java)
 * Description: A runnable tour of while, for, the accumulator pattern,
 *              sentinel-controlled input, do-while, and nested loops.
 * Created by:  Mr Kowalczewski
 * Last edited: 2026-09-11
 */

public class IterationDemo {

    public static void main(String[] args) {
        // below we are calling methods (functions) that are defined later in this file.
        whileLoopBasics();
        forLoopBasics();
        choosingAndConverting();
        commonLoopBugs();
        accumulatorPattern();
        doWhileLoop();
        nestedLoops();
    }

    // method to demonstrate the while loop
    public static void whileLoopBasics() {
        System.out.println();
        System.out.println("=== 1. while loop basics ===");

        int n = 5;
        while (n > 0) {
            System.out.println(n);
            n--;
        }

        // A while loop can run zero times, if the condition starts false.
        int m = 0;
        while (m > 0) {
            System.out.println("never prints");
        }
        System.out.println("m started at 0, so the loop body ran zero times");
    }

    // method to demonstrate the for loop and translating range()
    public static void forLoopBasics() {
        System.out.println();
        System.out.println("=== 2. for loop basics ===");

        // range(5)
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // range(1, 6)
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // range(10, 0, -1)
        for (int i = 10; i > 0; i--) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // method to demonstrate that for and while can do each other's job
    public static void choosingAndConverting() {
        System.out.println();
        System.out.println("=== 3. choosing between while and for ===");

        System.out.print("for:   ");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.print("while: ");
        int i = 0;
        while (i < 5) {
            System.out.print(i + " ");
            i++;
        }
        System.out.println();
    }

    // method to demonstrate infinite loops and off-by-one errors
    public static void commonLoopBugs() {
        System.out.println();
        System.out.println("=== 4. common loop bugs ===");

        // Off-by-one: <= would reach one past the last valid index.
        int[] scores = {85, 90, 78};
        for (int i = 0; i < scores.length; i++) {   // correct: <
            System.out.println("scores[" + i + "] = " + scores[i]);
        }
        // for (int i = 0; i <= scores.length; i++) { ... }  <-- would crash on i == 3

        // An infinite loop is commented out below, on purpose -- it would hang the demo.
        // int broken = 5;
        // while (broken > 0) {
        //     System.out.println(broken);
        //     // broken-- was forgotten -- broken is never 0, this never stops
        // }
        System.out.println("(infinite loop example is commented out above, on purpose)");
    }

    // method to demonstrate the accumulator pattern: sum, count, average, min, max, frequency, and sentinel input
    public static void accumulatorPattern() {
        System.out.println();
        System.out.println("=== 5. the accumulator pattern ===");

        double[] values = {12.5, 7.0, 20.0, 3.5, 15.0};

        double sum = 0;
        int count = 0;
        double min = values[0];
        double max = values[0];
        int passingCount = 0;

        for (int i = 0; i < values.length; i++) {
            double value = values[i];
            sum += value;
            count++;
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
            if (value >= 10.0) {
                passingCount++;
            }
        }

        System.out.println("sum = " + sum + ", count = " + count + ", average = " + (sum / count));
        System.out.println("min = " + min + ", max = " + max);
        System.out.println("values >= 10.0: " + passingCount);

        // Sentinel-controlled input needs a priming read: one read before the loop,
        // then another at the end of the loop body. Simulated here with an array
        // standing in for a stream of input values, ending in a sentinel.
        double[] stream = {6.0, 9.0, 2.0, -1000.0};
        int idx = 0;
        double value = stream[idx++];   // priming read
        double streamSum = 0;
        int streamCount = 0;
        while (value != -1000.0) {
            streamSum += value;
            streamCount++;
            value = stream[idx++];      // read again, at the end of the loop
        }
        System.out.println("sentinel stream: sum = " + streamSum + ", count = " + streamCount);
    }

    // method to demonstrate do-while
    public static void doWhileLoop() {
        System.out.println();
        System.out.println("=== 6. do-while ===");

        // Simulates a user typing two empty responses before a valid one --
        // the do-while has to attempt a read before it can check anything.
        String[] typed = {"", "", "Ada"};
        int i = 0;
        int attempts = 0;
        String response;
        do {
            response = typed[i];
            i++;
            attempts++;
            System.out.println("attempt " + attempts + ": \"" + response + "\"");
        } while (response.isEmpty());

        System.out.println("the body always runs at least once, even though the condition is checked after");
    }

    // method to demonstrate nested loops
    public static void nestedLoops() {
        System.out.println();
        System.out.println("=== 7. nested loops ===");

        for (int row = 1; row <= 3; row++) {
            for (int col = 1; col <= 3; col++) {
                System.out.print(row * col + " ");
            }
            System.out.println();
        }
        System.out.println("the inner loop runs completely for every single iteration of the outer loop");
    }
}
