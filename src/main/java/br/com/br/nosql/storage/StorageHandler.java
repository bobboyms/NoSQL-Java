package br.com.br.nosql.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class StorageHandler {

	public static void readFileNioWay2(String fileStrPath) {
		Path path = Paths.get(fileStrPath);
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))) {

			String currentLine = null;
			while ((currentLine = reader.readLine()) != null) {// while there is content on the current line
				// System.out.println(currentLine); // print the current line

			}
		} catch (IOException ex) {
			ex.printStackTrace(); // handle an exception here
		}
	}

	public static void readFileNioWay(String fileStrPath) {

		FileChannel inChannel = null;
		ByteBuffer buffer = null;
		RandomAccessFile aFile = null;

		try {

			aFile = new RandomAccessFile(fileStrPath, "r");
			inChannel = aFile.getChannel();
			buffer = ByteBuffer.allocate(1024);
			while (inChannel.read(buffer) > 0) {
				buffer.flip();
				for (int i = 0; i < buffer.limit(); i++) {
					// System.out.print((char) buffer.get());
				}
				buffer.clear(); // do something with the data and clear/compact it.
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inChannel.close();
				aFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static void writeToFileNIOWay(String file, String message) throws IOException {

		Path path = Paths.get(file);
		try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"), StandardOpenOption.APPEND)) {
			writer.write(message);
			writer.newLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		

//		try (final BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));
//				final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));) {
//			for (int i = 0; i < 10; i++) {
//				writer.write(message);
//				writer.newLine();
//			}
//		}

	}

//	public static void main(String[] args) throws IOException {
//		long startTime = System.currentTimeMillis();
//		//StorageHandler.writeToFileNIOWay(new File("C:\\teste\\x.bin"), "Caitano veloso boiola");
//		StorageHandler.readFileNioWay("C:\\teste\\database.txt");
//		long stopTime = System.currentTimeMillis();
//		long elapsedTime = stopTime - startTime;
//		System.out.println(elapsedTime);
//	}

}
