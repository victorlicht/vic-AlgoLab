package com.victorlicht.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MMMCCCVI {
    HashSet<Character> vowelsSet = new HashSet<>();
    // Main function to count valid substrings
    public long countOfSubstrings(String s, int k) {
        vowelsSet.add('a');
        vowelsSet.add('e');
        vowelsSet.add('i');
        vowelsSet.add('o');
        vowelsSet.add('u');
        long countWithK = get(s, k);
        long countWithKPlus1 = get(s, k + 1);
        return countWithK - countWithKPlus1;
    }

    // Helper function to compute substrings with at least k consonants
    public long get(String s, int k) {
        int i = 0, j = 0;
        long ans = 0;
        HashMap<Character, Integer> vowelCount = new HashMap<>();
        int consonantCount = 0;

        while (i < s.length()) {
            char rightChar = s.charAt(i);

            // If it's a vowel, update the count in HashMap
            if (vowelsSet.contains(rightChar)) {
                vowelCount.put(rightChar, vowelCount.getOrDefault(rightChar, 0) + 1);
            } else {
                consonantCount++;
            }

            // Adjust the left pointer when all vowels exist and consonant count >= k
            while (vowelCount.size() == 5 && consonantCount >= k) {
                ans += (s.length() - i); // Count all valid substrings

                char leftChar = s.charAt(j);
                if (vowelsSet.contains(leftChar)) {
                    if (vowelCount.get(leftChar) == 1) {
                        vowelCount.remove(leftChar);
                    } else {
                        vowelCount.put(leftChar, vowelCount.get(leftChar) - 1);
                    }
                } else {
                    consonantCount--;
                }
                j++; // Move left pointer
            }
            i++; // Expand right pointer
        }
        return ans;
    }
}
