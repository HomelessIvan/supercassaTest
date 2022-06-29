package ru.bezdomniy.superkassa;

import java.util.*;

public class Solution {


    private final int sizeOfBitSet;
    private final List<BitSet> bitSets;
    private final List<List<String>> lines;

    private final Set<Set<Integer>> results;

    public Solution(List<List<String>> lines) {
        this.sizeOfBitSet = lines.get(0).size();
        this.lines = lines;

        this.bitSets = new ArrayList<>();
        lines.forEach(
                line -> {
                    BitSet bitSet = new BitSet(line.size());
                    for (int i = 0; i < line.size(); i++) {
                        if (!line.get(i).equals("null"))
                            bitSet.set(i);
                    }
                    bitSets.add(bitSet);
                }

        );
        results = new HashSet<>();
    }

    /**
     * Find valid candidates for combine;
     *
     * if bitsetA XOR bitsetB == (bitsetA XOR bitsetB) OR bitsetA
     * then bitsetB is valid for bitSetA
     *
     * @param bitSet - a bitset for which we find candidates
     *
     * @return list of number of bitset which is valid
     */
    private List<Integer> findCandidates(BitSet bitSet) {
        List<Integer> candidates = new ArrayList<>();
        for (int j = 0; j < bitSets.size(); j++) {

            BitSet clone = (BitSet) bitSet.clone();
            clone.xor(bitSets.get(j));

            BitSet clone2 = (BitSet) clone.clone();
            clone2.or(bitSet);
            if (clone2.equals(clone))
                candidates.add(j);

        }

        return candidates;
    }

    /**
     * recursive merging of bitsets
     * when numbers of true bits in bitset == sizeOfBitSet then we add this chain to results
     *
     * @param bitSet - a start of chain of merge
     *
     */
    protected void merge(BitSet bitSet, Set<Integer> result) {
        List<Integer> candidates = findCandidates(bitSet);
        if (candidates.size() > 0) {
            for (int i = 0; i < candidates.size(); i++) {
                int candidate = candidates.get(i);

                BitSet clone = (BitSet) bitSet.clone();
                Set<Integer> resultClone = new HashSet<>(result);
                resultClone.add(candidates.get(i));
                clone.xor(bitSets.get(candidate));


                if (clone.cardinality() == sizeOfBitSet)
                    results.add(resultClone);

                merge(clone, resultClone);
            }
        } else if (bitSet.cardinality() == sizeOfBitSet) {
            results.add(result);
        }


    }


    /**
     * this funstion calls recursively function to find all combinations
     * @return all combination in merged format
     */
    public List<List<String>> computeVariants() {

        for (int i = 0; i < bitSets.size(); i++) {
            Set<Integer> r = new HashSet<>();
            r.add(i);
            merge(bitSets.get(i), r);
        }

        List<List<String>> toList = results.stream().map(
                variant -> {
                    return variant.stream().flatMap(
                            elem -> {
                                return lines.get(elem).stream().filter(e -> {
                                    return !e.equals("null");
                                });
                            }
                    ).toList();
                }
        ).toList();

        return toList;
    }



}
