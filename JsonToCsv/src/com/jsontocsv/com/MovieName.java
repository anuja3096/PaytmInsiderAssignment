package com.jsontocsv.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Integer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class MovieName {
	public static void main(String[] args) throws IOException {
		String fileName="C:\\Users\\neebal\\Music\\priyanka\\Test\\JsonToCsv\\src\\jsondata.json";
		String fileName2="C:\\Users\\neebal\\Music\\priyanka\\Test\\JsonToCsv\\src\\MovieNames.csv";
		String contents=readContentsOfFile(fileName);
		ObjectMapper objectMapper=new ObjectMapper();
		Map<String,Object> objects = objectMapper.readValue(contents, Map.class);
		List<Map<String,Object>> filteredList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> upcomingMovies = (List<Map<String, Object>>) objects.get("upcomingMovieData");
		for (int i=0;i<upcomingMovies.size();i++){
			Map<String,Object> currentMovie = upcomingMovies.get(i);
			if(((Integer) (currentMovie.get("isContentAvailable"))).intValue() == 1) {
				String movieName = (String) currentMovie.get("movie_name");
				String releaseDate = (String) currentMovie.get("releaseDate");
				
				Map<String,Object> tempObject = new HashMap<String, Object>();
				tempObject.put("movieName", movieName);
				tempObject.put("releaseDate",releaseDate);
				filteredList.add(tempObject);
			}
		}
		System.out.println(filteredList);
		writeContentsToFile(fileName2,filteredList);
	}
	
	public static String readContentsOfFile(String fileName) throws IOException {
		File file = new File(fileName);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		String content = stringBuilder.toString();
		return content;
	}
	public static void writeContentsToFile(String fileName2,List<Map<String,Object>> filteredList) throws IOException {	
		
		Writer writer = new FileWriter(fileName2, true);
		BufferedWriter writer1 = new BufferedWriter(writer);
		 csvWriter(filteredList, writer);
//		csvWriter(filteredList, writer);

		
	}
	
	public static  void csvWriter(Collection collection, Writer writer) throws IOException {
        if (collection != null && collection.size() > 0) {
            CsvMapper mapper = new CsvMapper();
            Object[] objects = collection.toArray();
            CsvSchema schema = new CsvSchema.Builder().addArrayColumn("movieName").build();
            mapper.writer(schema).writeValues(writer).write(objects);
        } else {
            writer.write("No Data");
        }
        writer.flush();
    }

}
