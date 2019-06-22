package br.com.br.nosql.storage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageHandler {
	public StorageHandler() {
	}

	public static void readFileNioWay(String fileStrPath) throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("test.txt", "r");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (inChannel.read(buffer) > 0) {
			buffer.flip();
			for (int i = 0; i < buffer.limit(); i++) {
				System.out.print((char) buffer.get());
			}
			buffer.clear(); // do something with the data and clear/compact it.
		}
		inChannel.close();
		aFile.close();
	}

	public static void readFileNioWay2(String fileStrPath) {
		Path path = Paths.get("src/main/resources/shakespeare.txt");
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))) {
			String currentLine = null;
			while ((currentLine = reader.readLine()) != null) {// while there is content on the current line
				System.out.println(currentLine); // print the current line
			}
		} catch (IOException ex) {
			ex.printStackTrace(); // handle an exception here
		}
	}

	public static void writeToFileNIOWay(String file, String message) throws IOException {
	}
}
