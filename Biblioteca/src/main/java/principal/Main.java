package principal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {
	
	public static ArrayList<Book> carti = new ArrayList<Book>();
	static ObjectMapper objectMapper = new ObjectMapper();
	static File file = new File("./jsonfiles/books.json");

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		
		carti = readJson();
		new MyFrame();
		
		
		
	}
	
	public static ArrayList<Book> readJson() throws StreamReadException, DatabindException, IOException {
		ArrayList<Book> carti = objectMapper.readValue(file, new TypeReference<ArrayList<Book>>(){});
		return carti;
	}
	
	public static void writeJson(ArrayList<Book> carti) throws StreamWriteException, DatabindException, IOException {
		objectMapper.writeValue(file, carti);
		return;
	}

}
