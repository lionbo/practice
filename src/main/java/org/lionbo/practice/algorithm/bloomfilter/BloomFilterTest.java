package org.lionbo.practice.algorithm.bloomfilter;

import java.nio.charset.Charset;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterTest {

    public static void main(String[] args) {
        int expectedInsertions = 10000 * 10000;
        BloomFilter<CharSequence> filter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")),
                expectedInsertions, 0.001);
        filter.put("lionbo");
        System.out.println(filter.mightContain("test"));

    }
}
