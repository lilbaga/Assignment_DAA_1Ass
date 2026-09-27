# Assignment 1: Divide and Conquer Algorithms

## 1. Project Overview
This project implements and analyzes classic Divide and Conquer algorithms in Java, including:
* **Merge Sort** (with optimizations like insertion sort threshold and auxiliary buffer reuse).
* **Quick Sort** (using randomized pivot selection).
* **Deterministic Selector** (Median-of-Medians algorithm for $O(n)$ worst-case selection).
* **Closest Pair of Points** (efficient $O(n \log n)$ geometric algorithm).

## 2. Project Structure
* `src/` — Source code for algorithms, experiments, and main execution.
* `tests/` — Unit and integration tests (`AllTests.java`).
* `results/` — Generated benchmark data (`results.csv`).
* `docs/` — Documentation, plots, and screenshots.

## 3. Experimental Results
The benchmarks were executed for various input sizes, measuring execution time (nanoseconds), comparison counts, and maximum recursion depth. The data is successfully recorded in `results/results.csv`.

## 4. Verification and Testing
All algorithms were thoroughly tested against reference implementations (`Arrays.sort()`) and brute-force methods.

![Tests Screenshot](docs/screenshots/tests_success.png)

## 5. Conclusion
The experimental results align with theoretical expectations. Merge Sort and Quick Sort demonstrate efficient $O(n \log n)$ performance, while the Closest Pair and Median-of-Medians algorithms solve complex computational geometry and selection problems efficiently within expected bounds.