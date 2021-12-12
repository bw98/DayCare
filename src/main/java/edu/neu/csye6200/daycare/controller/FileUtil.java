package edu.neu.csye6200.daycare.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil implements AutoCloseable {
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public FileUtil(FileReader reader) {
		this.reader = new BufferedReader(reader);
	}
	
	public FileUtil(FileWriter writer) {
		this.writer = new BufferedWriter(writer);
	}
	
	public boolean write(String words) {
		try {
			this.writer.write(words);
			this.writer.newLine();
			this.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String readLine() throws IOException {
		return this.reader.readLine();
	}

	@Override
	public void close() throws Exception {
		if (this.reader != null) {
			this.reader.close();
		}
		if (this.writer != null) {
			this.writer.close();
		}
	}
}
