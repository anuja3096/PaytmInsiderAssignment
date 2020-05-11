package com.demo.com;

public class PrimeNumbers{
public static void main(String[] args) {
	
    boolean isPrime = true;
    int total=0;

   
    String primeNumbersFound = "";
    int i1=2;
    
    while(total<100) {
    	
        isPrime = PrimeNoCheck(i1);
        if (isPrime) {
            primeNumbersFound = primeNumbersFound + i1 + " ";
            ++total;
        }
        i1++;
    }
    System.out.println("1st 100 prime no's are:");
    System.out.println(primeNumbersFound);
}
public static boolean PrimeNoCheck(int numberToCheck) {
    int remainder;
    for (int i = 2; i <= numberToCheck / 2; i++) {
        remainder = numberToCheck % i;
      
        if (remainder == 0) {
            return false;
        }
    }
    return true;

}

}
