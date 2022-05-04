package com.quinn.exercise;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.Timeout;

/**
 * @author quinn
 * @date May 1, 2022 5:34:52 PM
 */
@TestInstance(Lifecycle.PER_CLASS)
class DocRipperTest {

	/**
	 * Test method for
	 * {@link com.quinn.exercise.DocRipper#findWord(java.lang.String, java.lang.String)}.
	 * 
	 * @throws IOException
	 */
	 String fileName = "";
	 String word = "";
	 DocRipper dr = new DocRipper();

	@BeforeAll
	@Timeout(1)
	void setup() throws IOException, InterruptedException {
//		TimeUnit.SECONDS.sleep(10);
		fileName = "/home/pigmilk/sts4_workspace/multithreading/src/main/resources/static/findWordTest.txt";
		word = "A";
		dr.findWord(fileName, word);
		
	}

	@Test
	void testFindWordCheckCounting() throws IOException {
		long expected = 10;
		long actual = dr.getResultMap().get(word);
		assertEquals(expected, actual, "cnt 數量不正確");
	}

	/**
	 * Test method for
	 * {@link com.quinn.exercise.DocRipper#findMultiWord(java.lang.String, java.lang.String[])}.
	 */
	@Test
	void testFindMultiWord() {
		fail("Not yet implemented");
	}

}
