package org.carrot2.text.util;

import java.util.Comparator;

/**
 * A set of specific comparators for <code>char []</code> arrays.
 */
public class CharArrayComparators
{
    /**
     * A case-insensitive lexicographic comparator.
     */
    public static final Comparator<char []> CASE_INSENSITIVE_CHAR_ARRAY_COMPARATOR = new Comparator<char []>()
    {
        public final int compare(char [] a1, char [] a2)
        {
            if (a1 == null)
            {
                return 1;
            }

            if (a2 == null)
            {
                return -1;
            }

            final int l1 = a1.length;
            final int l2 = a2.length;
            final int n = l1 < l2 ? l1 : l2;

            for (int i = 0; i < n; i++)
            {
                char c = Character.toLowerCase(a1[i]);
                char d = Character.toLowerCase(a2[i]);
                if (c != d)
                {
                    return c - d;
                }
            }

            return l1 - l2;
        }
    };

    /**
     * A case-sensitive lexicographic comparator.
     */
    public static final Comparator<char []> FAST_CHAR_ARRAY_COMPARATOR = new Comparator<char []>()
    {
        public final int compare(char [] a1, char [] a2)
        {
            if (a1 == null)
            {
                return 1;
            }

            if (a2 == null)
            {
                return -1;
            }

            final int l1 = a1.length;
            final int l2 = a2.length;
            final int n = l1 < l2 ? l1 : l2;

            for (int i = 0; i < n; i++)
            {
                char a1I = a1[i];
                char a2I = a2[i];

                if (a1I != a2I)
                {
                    return a1I - a2I;
                }
            }

            return l1 - l2;
        }
    };

    /**
     * A comparator that applies the following rules in the following order:
     * <ol>
     * <li>A <code>null</code> string is greater than a non-<code>null</code> one</li>
     * <li>A longer string is greater</li>
     * <li>Strings are first compared in case-insensitive mode</li>
     * <li>Finally, strings are compared in case-sensitive mode</li>
     * </ol>
     * This comparator does not provide a lexicographic order, it's main aim is to group
     * different (case-insensitive) strings into different buckets and group strings
     * within the buckets (case-sensitive). Not sticking to lexicographic order makes the
     * comparator much faster.
     */
    public static final Comparator<char []> NORMALIZING_CHAR_ARRAY_COMPARATOR = new Comparator<char []>()
    {
        public final int compare(char [] a1, char [] a2)
        {
            if (a1 == null)
            {
                return 1;
            }

            if (a2 == null)
            {
                return -1;
            }

            final int l1 = a1.length;
            final int l2 = a2.length;

            // For those who might wonder: the condition below is perfectly ok here. It is
            // used to calculate word occurrence statistics, which is essentially a "count
            // unique strings by sorting" problem. Therefore, the semantic meaning of the
            // order produced by this comparator doesn't matter at all as long as it: a)
            // groups equal (case sensitive) strings together, b) groups equal (case
            // insensitive) strings into one block, c) null string is always
            // greater than a non-null string. See tests for this comparator
            // for examples.
            //
            // In comparison-based sorting algorithms crucial is the speed of
            // comparisons, so declaring that e.g. shorter strings are always
            // smaller (regardless of contents) saves us calls to Character.toLowerCase(),
            // which are very costly. For CaseNormalizer it doesn't matter at all,
            // and makes sorting way faster.

            // Not crucial, but speeds things up
            if (l1 != l2)
            {
                return l1 - l2;
            }

            // Compare whole strings in case insensitive mode first
            for (int i = 0; i < l1; i++)
            {
                final char c = Character.toLowerCase(a1[i]);
                final char d = Character.toLowerCase(a2[i]);
                if (c != d)
                {
                    return c - d;
                }
            }

            // Only if strings are case-insensitive equal, go case sensitive
            for (int i = 0; i < l1; i++)
            {
                char a1I = a1[i];
                char a2I = a2[i];

                if (a1I != a2I)
                {
                    // Put lower case first
                    return a2I - a1I;
                }
            }

            return 0;
        }
    };

    /**
     * No instantiation.
     */
    private CharArrayComparators()
    {
    }
}
