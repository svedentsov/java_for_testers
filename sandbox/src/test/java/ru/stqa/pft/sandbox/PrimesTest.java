package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimesTest {

    @Test
    public void testIsPrime() {
        int n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n));
    }

    @Test(enabled = false)
    public void testIsPrimeLong() {
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n));
    }

    @Test
    public void testIsPrimeFast() {
        int n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrimeFast(n));
    }

    @Test
    public void testNonPrime() {
        int n = Integer.MAX_VALUE - 2;
        Assert.assertFalse(Primes.isPrime(n));
    }
}