1. Architecture Overview

This project implements and benchmarks four classical divide-and-conquer algorithms:
•	MergeSort – stable O(n log n) sorting algorithm with a reusable buffer and insertion sort cutoff for small subarrays.
•	QuickSort – uses randomized pivot selection, recurses only on the smaller partition, and processes the larger one iteratively to keep recursion depth small.
•	Deterministic Select (Median-of-Medians) – linear O(n) selection algorithm using median-of-5 pivot selection and recursive descent only into the relevant partition.
•	Closest Pair of Points – O(n log n) algorithm based on sorting by x-coordinate, dividing the points, and scanning a narrow strip sorted by y.

A Metrics class tracks key statistics:
•	number of comparisons and swaps;
•	execution time (nanoseconds, converted to milliseconds for better performance);
•	maximum recursion depth via enter()/exit() calls.

Memory allocations are minimized: MergeSort reuses its buffer, QuickSort and Select operate in-place, and no unnecessary array copies are created.

2. Recurrence Analysis

MergeSort

Recurrence: T(n) = 2T(n/2) + Θ(n)
The array is split into two halves and merged in linear time, leading by Master Theorem to a solution of Θ(n log n). Recursion depth is O(log n) due to halving at each level, and buffer reuse reduces allocation overhead.

QuickSort

Recurrence: T(n) = T(k) + T(n - k - 1) + Θ(n)
Random pivot selection keeps partitions balanced on average, leading by Master Theorem to an expected solution of Θ(n log n). Recursing on the smaller side ensures logarithmic depth and improves memory efficiency.

Deterministic Select (Median-of-Medians)

Recurrence: T(n) = T(n/5) + T(7n/10) + Θ(n)
The algorithm removes a constant fraction of elements each step by choosing a good pivot, and by the Akra–Bazzi theorem the solution is Θ(n). Recursion depth decreases as the problem size shrinks, guaranteeing linear time complexity.

Closest Pair of Points

Recurrence: T(n) = 2T(n/2) + Θ(n)
The points are divided in half, and a linear-time “strip” check limits the number of comparisons, giving a Θ(n log n) solution by Master Theorem. Each level reduces the problem size, resulting in logarithmic recursion depth.

3. Experimental Results

All algorithms were tested on random input data of various sizes. Metrics (execution time, recursion depth, comparisons, swaps) were logged into results.csv.

Plots (time vs n, depth vs n):
•	Time vs n: MergeSort, QuickSort, and Closest Pair show n log n growth, while Deterministic Select scales linearly.
•	Depth vs n: Logarithmic for sorting and closest pair, and slightly higher but sublinear for deterministic select.

Constant-factor effects:
•	MergeSort benefits from cache locality but incurs some overhead due to merging.
•	QuickSort often outperforms MergeSort in practice due to better cache utilization.
•	Deterministic Select is slower than QuickSelect or sorting-based selection due to large constant factors but scales linearly.
•	Closest Pair overhead mainly comes from initial sorting and strip construction.

4. Summary and Conclusions

The experimental results align with theoretical expectations:
•	MergeSort and QuickSort achieve Θ(n log n) time with logarithmic recursion depth, and QuickSort often performs faster in practice.
•	Deterministic Select confirms its linear complexity, although constant factors make it slower.
•	Closest Pair also matches the Θ(n log n) time bound and exhibits expected recursion behavior.

Minor deviations from theory are caused by implementation details, but overall the measured results confirm the theoretical analysis.