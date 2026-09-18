# ICS 4U0 — Lesson 4: Loops and Iteration

## Setup (do this once, before your first lesson)

You need three things installed: a Java compiler (JDK), an editor (VS Code), and this code. If you already have all three, skip to [Get the code](#get-the-code).

### 1. Install a JDK

1. Go to **[adoptium.net](https://adoptium.net)**.
2. Click the big download button (it detects your OS automatically — Windows/macOS/Linux). Pick the **LTS** version it suggests (21 or newer).
3. Run the installer, keeping the default options.
   - **Windows:** on the "Setup" screen, make sure **"Set JAVA_HOME variable"** and **"Add to PATH"** are checked (they usually are by default).
4. Confirm it worked: open a terminal (Windows: search for **"Terminal"** in the Start menu; Mac: search for **"Terminal"** in Spotlight) and run:
   ```
   java -version
   ```
   You should see a version number (21 or higher), not a "command not found" error. If you get an error, restart your computer and try again — the PATH change sometimes needs a restart to take effect.

### 2. Install VS Code

1. Go to **[code.visualstudio.com](https://code.visualstudio.com)** and download it for your OS.
2. Run the installer, keeping the default options.
3. Open VS Code once it's installed, just to confirm it launches.

### 3. Get the code

1. On this repository's GitHub page, click the green **`<> Code`** button, then **Download ZIP**.
2. Find the downloaded ZIP file (usually in your **Downloads** folder) and **extract/unzip it**:
   - **Windows:** right-click the ZIP → **Extract All...** → Extract.
   - **Mac:** double-click the ZIP.
3. You now have a folder named something like `1.4---Loops-and-Iteration-main`. Put it somewhere you'll remember — e.g. a `School` folder in your Documents.

### 4. Open it in VS Code

1. Open VS Code.
2. **File → Open Folder...** and select the folder from step 3 (the one containing this `README.md`).
3. If VS Code asks **"Do you trust the authors of the files in this folder?"**, click **Yes, I trust the authors**.
4. VS Code will prompt to install extensions (a notification in the bottom right, or a popup when you open a `.java` file) — install the **Extension Pack for Java**. If it doesn't prompt automatically, click the **Extensions** icon in the left sidebar (four squares), search for **"Extension Pack for Java"**, and click **Install**.
5. Give it a minute after installing — VS Code shows "Java: Loading..." or similar in the bottom status bar while it sets up the project. Wait for that to finish before moving on.

You only need to do this setup once. From here on, open VS Code and use **File → Open Folder...** to reopen this project (or the next lesson's folder, once you have one).

---

Every lesson that we go through will have several files, here's how to navigate:

| File | What it is |
|------|------------|
| `README.md` | This file should open *first* and will contain information about where to find everything, and what your exercise will be. |
| `src/` folder | This folder will contain the actual code files.  `.java` files are the source code you can look at and run, `.class` files are the bytecode generated when the source code is compiled.  |
| `src/IterationDemo.java` | The example code that your teacher will go through. |
| `src/TemperatureLog.java` | Your exercise template will be here. |
| `test/TemperatureLogTest.java` | Automatic tests for the exercise. You don't need to edit this file. |
| `lib/` | The testing library (JUnit) the tests run on. You don't need to open this. |

---

## Exercise

You're logging temperature readings for a weather station. Some readings are scheduled ahead of time; others come in later as they're recorded.

Everything you need is from Lesson 4:

- a **for loop** to read a known number of scheduled readings,
- a **while loop** with an initial read to keep reading unscheduled readings until a certain value shows up, and
- the **accumulator pattern** to track a running count, sum, minimum, and maximum across both loops.

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

Test your code yourself first. Then run the automatic tests in `test/TemperatureLogTest.java`, which check your program against the example above plus a few tricky cases (like all-positive readings, and negative readings).

To run them in VS Code (you'll need the **Extension Pack for Java**, which prompts to install the first time you open a `.java` file):

- Open `test/TemperatureLogTest.java` and click the ▶ **Run Test** link that appears above the class name (or above any single test method) — or
- Open the **Testing** panel from the sidebar (flask icon) and click ▶ **Run Tests**.

A green check next to a test means it passed; a red X means it failed and will show you the expected vs. actual output.

**Don't see the "Run Test" link or the Testing panel?** Give VS Code a minute to finish loading the Java project (check the status bar at the bottom), then close and reopen `test/TemperatureLogTest.java`. If it still doesn't appear, make sure the **Extension Pack for Java** installed correctly (Extensions sidebar → search "Java" → it should say "Installed").

If you pass all tests, return to the **Assignments** page and submit your work.
