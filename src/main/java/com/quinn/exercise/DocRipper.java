package com.quinn.exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.Setter;

/**
 * @author quinn <br>
 *         Apr 29, 2022 9:08:54 AM
 * 
 */
@Getter
@Setter
public class DocRipper {
	boolean usePool;
	Map<String, Long> resultMap = new HashMap<>();

	public DocRipper() {

	}

	void countWord(String line, String taget) {
		long count = resultMap.get(taget) == null ? 0 : resultMap.get(taget);
		int index = 0;
		while (index >= 0) {
			index = line.indexOf(taget, index);
			if (index >= 0) {
				count++;
				index++;
			}
		}
		resultMap.put(taget, count);
	}

	public static void main(String[] args) throws IOException {

		// 1. 判斷單一執行緒或使用執行緒池
		DocRipper dr = new DocRipper();

		// 2. 讀取 doc

		String fileName = "/mnt/Temp/workspace/war_and_peace.txt";

		long start = System.currentTimeMillis();
		dr.findWord(fileName, "A");

		long end = System.currentTimeMillis();
		System.out.println(end - start);

		// 3. 指定搜尋的文字, 預設是 [a-z], [A-Z], [0-9]

		// 4. 吐出結果 hashMap
		dr.resultMap.forEach((k, v) -> System.out.println(k + " : " + v));

	}

	public void findWord(String fileName, String taget) throws IOException {
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.parallel().forEach(line -> countWord(line, taget));
		}
	}

	public void findMultiWord(String fileName, String[] taget) throws IOException {
		for (int i = 0; i < taget.length; i++) {
			findWord(fileName, taget[i]);
		}
	}
}
