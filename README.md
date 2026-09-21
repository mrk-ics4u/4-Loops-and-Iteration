# ICS 4U0 — Lesson 4: Loops and Iteration

## Exercise

You're logging temperature readings for a weather station. Some readings are scheduled ahead of time; others come in later as they're recorded.


- Read `n`, the number of scheduled readings.
- Using a **for loop** that runs `n` times, read `n` temperature readings (`double`). For the very first one, initialize your running count, sum, minimum, and maximum from it; for every one after, update them.
- Using a **while loop** with a priming read, keep reading additional temperature readings (`double`) until one equals `-1000`. Do not count that value. Update the same running count, sum, minimum, and maximum.
- Compute the average as `sum / count`.

---

## Input

The number of scheduled readings, then that many readings, then zero or more additional readings terminated by the sentinel:

| Line(s) | Value | Type |
|------|-------|------|
| 1 | Number of scheduled readings (`n`) | whole number |
| 2 to n+1 | Scheduled temperature readings, one per line | decimal |
| after that | Additional temperature readings, one per line, ending with `-1000` | decimal |

Example input:

```
3
20.5
18.0
25.3
15.0
30.0
-1000
```

---

## Output

Exactly five lines:

```
Readings recorded: <count>
Sum: <amount to 2 decimals>
Average: <amount to 2 decimals>
Minimum: <amount to 2 decimals>
Maximum: <amount to 2 decimals>
```

For the example input above, your program must print **exactly**:

```
Readings recorded: 5
Sum: 108.80
Average: 21.76
Minimum: 15.00
Maximum: 30.00
```

Every space and colon is compared. `Sum:108.80` and `Sum: 108.80` are not the same answer.

---

## Testing

- Test your code yourself first.
- Open the **Testing** panel from the sidebar (flask icon) and click ▶ **Run Tests**.

A green check next to a test means it passed; a red X means it failed and will show you the expected vs. actual output.

