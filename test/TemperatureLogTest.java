/*
 * TemperatureLogTest.java -- unit tests for the Lesson 4 exercise
 * ICS 4U0 - Lesson 4 Exercise: Temperature Log
 *
 * These tests run TemperatureLog.main() exactly as it will be graded: they
 * feed it the input lines it expects on System.in and check the lines it
 * prints to System.out. You don't need to change this file -- just run the
 * tests (see README.md) and fix TemperatureLog.java until they all pass.
 */
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TemperatureLogTest {

    private InputStream originalIn;
    private PrintStream originalOut;
    private Locale originalLocale;

    @BeforeEach
    public void redirectIoAndPinLocale() {
        originalIn = System.in;
        originalOut = System.out;
        originalLocale = Locale.getDefault();
        // printf("%.2f") follows the default locale -- on a machine that uses
        // a comma for decimals, output like "21,76" would fail the exercise
        // for reasons that have nothing to do with your code, so every test
        // pins the locale before calling main().
        Locale.setDefault(Locale.CANADA);
    }

    @AfterEach
    public void restoreIoAndLocale() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        Locale.setDefault(originalLocale);
    }

    /**
     * Feeds {@code input} to TemperatureLog.main() on System.in and returns
     * everything it printed to System.out.
     */
    private String run(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured, true, StandardCharsets.UTF_8));

        TemperatureLog.main(new String[0]);

        return captured.toString(StandardCharsets.UTF_8);
    }

    /**
     * Compares output line by line, trimming trailing whitespace on each
     * line and ignoring one blank line at the very end (println's final
     * newline). Everything else -- including the space after each colon --
     * is compared exactly.
     */
    private void assertOutputEquals(String expected, String actual) {
        String[] expectedLines = expected.stripTrailing().split("\n", -1);
        String[] actualLines = actual.stripTrailing().split("\n", -1);

        assertEquals(expectedLines.length, actualLines.length,
                "Expected " + expectedLines.length + " line(s) of output, got " + actualLines.length
                        + ".\n--- expected ---\n" + expected + "--- actual ---\n" + actual);

        for (int i = 0; i < expectedLines.length; i++) {
            assertEquals(expectedLines[i].stripTrailing(), actualLines[i].stripTrailing(),
                    "Line " + (i + 1) + " didn't match.\n--- expected ---\n" + expected
                            + "--- actual ---\n" + actual);
        }
    }

    @Test
    public void exampleFromReadme() {
        String input = "3\n20.5\n18.0\n25.3\n15.0\n30.0\n-1000\n";
        String expected = "Readings recorded: 5\n"
                + "Sum: 108.80\n"
                + "Average: 21.76\n"
                + "Minimum: 15.00\n"
                + "Maximum: 30.00\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void minAndMaxAreNotInitializedToZero() {
        // Every reading is above 0. If min/max were initialized to 0.0
        // instead of the first reading, this would silently report a
        // minimum of 0.00 -- a bug that's invisible unless you test with
        // all-positive values on purpose.
        String input = "3\n5.0\n8.0\n12.0\n-1000\n";
        String expected = "Readings recorded: 3\n"
                + "Sum: 25.00\n"
                + "Average: 8.33\n"
                + "Minimum: 5.00\n"
                + "Maximum: 12.00\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void whileLoopReadingsAreIncludedAndSentinelIsNot() {
        // One scheduled reading, then three more read by the while loop.
        // If the sentinel (-1000) were counted as a reading, count would be
        // 4 and the sum/average would be thrown off by -1000.
        String input = "1\n10.0\n20.0\n30.0\n-1000\n";
        String expected = "Readings recorded: 3\n"
                + "Sum: 60.00\n"
                + "Average: 20.00\n"
                + "Minimum: 10.00\n"
                + "Maximum: 30.00\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void negativeReadingsAreHandled() {
        String input = "2\n-5.0\n10.0\n-20.0\n-1000\n";
        String expected = "Readings recorded: 3\n"
                + "Sum: -15.00\n"
                + "Average: -5.00\n"
                + "Minimum: -20.00\n"
                + "Maximum: 10.00\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void noAdditionalReadingsAfterScheduledOnes() {
        // The while loop's priming read immediately hits the sentinel, so
        // the loop body should run zero times -- this checks that the
        // priming read happens before the loop, not inside it.
        String input = "2\n1.0\n2.0\n-1000\n";
        String expected = "Readings recorded: 2\n"
                + "Sum: 3.00\n"
                + "Average: 1.50\n"
                + "Minimum: 1.00\n"
                + "Maximum: 2.00\n";
        assertOutputEquals(expected, run(input));
    }
}
