package com.condensr.url;

import java.util.HashMap;
import java.util.Map;

//import jdk.internal.org.jline.terminal.TerminalBuilder.SystemOutput;

public class Radix64 {

	public static void main(String[] args) throws Radix64Exception {
		// TODO Auto-generated method stub
		
		System.out.println(encode(1000008760));

		System.out.println(decode("xckmu"));
		
	}

	
	
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-_";
	private static final Map<Character, Integer> ALPHABET_INDEX = new HashMap<>();
	private static final int MAX_SAFE_STRING_LENGTH = 10;
	private static final long MAX_SAFE_NUMBER = 1152921504606846976L; // 64^10

	static {
		// Initialize the ALPHABET_INDEX map
		for (int i = 0; i < ALPHABET.length(); i++) {
			ALPHABET_INDEX.put(ALPHABET.charAt(i), i);
		}
	}

	public static class Radix64Exception extends Exception {
		public Radix64Exception(String message) {
			super(message);
		}
	}

	/**
	 * Encodes a number into a Radix64 string.
	 *
	 * @param number The number to encode.
	 * @return The Radix64 encoded string.
	 * @throws Radix64Exception If the number exceeds the maximum safe number.
	 */
	public static String encode(long number) throws Radix64Exception {
		if (number > MAX_SAFE_NUMBER) {
			throw new Radix64Exception("Number greater than " + MAX_SAFE_NUMBER);
		}
		if (number == 0) {
			return "0"; // Special case for 0
		}

		StringBuilder result = new StringBuilder();
		while (number > 0) {
			int remainder = (int) (number % 64);
			result.append(ALPHABET.charAt(remainder));
			number /= 64;
		}

		return result.reverse().toString(); // Reverse the result to get the correct order
	}

	/**
	 * Decodes a Radix64 string into a number.
	 *
	 * @param str The Radix64 string to decode.
	 * @return The decoded number.
	 * @throws Radix64Exception If the string length exceeds the maximum safe
	 *                          length.
	 */
	public static long decode(String str) throws Radix64Exception {
		if (str.length() > MAX_SAFE_STRING_LENGTH) {
			throw new Radix64Exception("String longer than " + MAX_SAFE_STRING_LENGTH + " characters");
		}

		long result = 0;
		for (char c : str.toCharArray()) {
			if (!ALPHABET_INDEX.containsKey(c)) {
				throw new Radix64Exception("Invalid character in Radix64 string: " + c);
			}
			result = result * 64 + ALPHABET_INDEX.get(c);
		}

		return result;
	}
}
