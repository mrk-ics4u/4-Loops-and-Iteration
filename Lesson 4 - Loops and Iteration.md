# Lesson 4 - Loops and Iteration

Sequencing and selection are two of the three building blocks of every algorithm. This lesson adds the third: **iteration**, repeating a block of code while a condition holds.

---

## 1. From Python's `while` to Java's `while`

The idea is identical to Python's. The syntax carries over the same differences from `if` (Lesson 3): parentheses instead of a colon, braces instead of indentation, and the condition must be a `boolean`.

```python
# Python
n = 5
while n > 0:
    print(n)
    n -= 1
```

```java
// Java
int n = 5;
while (n > 0) {
    System.out.println(n);
    n--;
}
```

A `while` loop checks its condition before every iteration, including the first. If the condition is `false` the first time it's checked, the body never runs at all; zero iterations is a valid outcome, not an error.

```java
int n = 0;
while (n > 0) {
    System.out.println("never prints");
}
System.out.println("n started at 0, so the loop body ran zero times");
```

---

## 2. The `for` Loop

Python's `for i in range(...)` and Java's `for` loop both repeat a block a fixed number of times, but Java's `for` spells out the bookkeeping explicitly in its header.

```python
# Python
for i in range(5):
    print(i)
```

```java
// Java
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}
```

A `for` header has three parts, separated by semicolons.

| Part | Runs when | Example |
|---|---|---|
| initialization | once, before anything else | `int i = 0` |
| Boolean expression | before every iteration, including the first | `i < 5` |
| update | after the loop body, before the condition is checked again | `i++` |

`range()` calls translate directly.

| Python | Java |
|---|---|
| `range(5)` | `for (int i = 0; i < 5; i++)` |
| `range(1, 6)` | `for (int i = 1; i <= 5; i++)` |
| `range(10, 0, -1)` | `for (int i = 10; i > 0; i--)` |

The variable declared in the initialization (`i` above) is the loop control variable. It is scoped to the loop: it does not exist before the `for` and does not exist after it.

---

## 3. Choosing Between `while` and `for`


Use a `for` loop when the number of iterations is known (or countable) before the loop starts: a fixed range, N repetitions. Use a `while` loop when the stopping condition depends on something that happens inside the loop and cannot be counted in advance, such as reading input until a particular value appears.

---

## 4. Common Loop Bugs

### Infinite loops

A loop whose condition never becomes `false` never stops.

```java
int n = 5;
while (n > 0) {
    System.out.println(n);
    // n-- was forgotten; n is never 0, so this never stops
}
```

Every `while` loop needs something in its body that moves the condition toward `false`. Before trusting a new loop, check what changes each iteration and confirm that change eventually makes the condition false.

### Off-by-one errors

A loop that runs one time too many or too few, most often from confusing `<` and `<=`.

```java
int[] scores = {85, 90, 78};

for (int i = 0; i <= scores.length; i++) {   // BUG: should be <
    System.out.println(scores[i]);
}
```

`scores.length` is `3`; valid indices are `0`, `1`, and `2`. `i <= 3` lets `i` reach `3`, one past the last valid index. (Arrays are coming up; the shape of the bug is the point here.)

---

## 5. The `do-while` Loop

A `do-while` runs its body once, then checks the condition, then continues like a normal `while`. Python has no equivalent: there is no way to say "run this at least once" without duplicating code or reaching for a different structure.

```java
int attempts = 0;
String response;
do {
    System.out.println("Enter your name:");
    response = console.nextLine();
    attempts++;
} while (response.isEmpty());
```

The body runs first and the condition is checked after, which is useful for input validation: you need one attempt to read a value before you can check whether it was valid.

---

## 6. Nested Loops

A loop written inside the body of another loop. The inner loop runs all of its iterations for every single iteration of the outer loop.

```java
for (int row = 1; row <= 3; row++) {
    for (int col = 1; col <= 3; col++) {
        System.out.print(row * col + " ");
    }
    System.out.println();
}
```

For `row = 1`, the inner loop runs completely (`col` = 1, 2, 3) before `row` becomes `2`. Total iterations of the inner body: outer count times inner count, here `3 * 3 = 9`.

Nested loops return in force once arrays, especially 2D arrays, are on the table. This is the mechanics in isolation, before that context is added.

---

## Try It Yourself

Compile and run the companion file in this folder:

```bash
javac IterationDemo.java
java IterationDemo
```

Then work on the exercise in `TemperatureLog.java`.

---

## Course Expectations

### ICS 4U

Nothing is assessed.

### AP Expectations

The following are expectations of the AP exam and will show up on the final exam:

- Iteration as the third building block of every algorithm, repeating a segment of code while a Boolean expression is `true` (Topic 2.7, while Loops, 2.7.A.1)
- Infinite loops, where the controlling Boolean expression always evaluates to `true` (Topic 2.7, 2.7.A.2)
- A `while` loop's body does not execute at all if the condition is initially `false` (Topic 2.7, 2.7.A.3)
- Off-by-one errors, where a loop runs one time too many or too few (Topic 2.7, 2.7.A.4)
- The `while` loop: the Boolean expression is evaluated before every iteration, including the first, and the loop ends the first time it evaluates to `false` (Topic 2.7, while Loops, 2.7.B.1)
- The `for` loop's three-part header (initialization, Boolean expression, update) and its execution order (Topic 2.8, for Loops, 2.8.A.1, 2.8.A.2)
- Rewriting a `for` loop as an equivalent `while` loop, and vice versa (Topic 2.8, 2.8.A.3)
- Standard iterative algorithms without data structures: computing a sum or average, counting occurrences that meet a criterion, and finding a minimum or maximum (Topic 2.9, Implementing Selection and Iteration Algorithms, 2.9.A.1)
- Nested iteration statements, where the inner loop completes all of its iterations before the outer loop continues (Topic 2.11, Nested Iteration, 2.11.A.1)
