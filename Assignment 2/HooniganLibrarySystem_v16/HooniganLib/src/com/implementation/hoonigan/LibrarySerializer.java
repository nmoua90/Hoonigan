package com.implementation.hoonigan;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;

public class LibrarySerializer {

    public static class ItemSerializer implements JsonSerializer<Item> {
        public JsonElement serialize(final Item item, final Type type, final JsonSerializationContext context) {
        	//Create a new Json object
            JsonObject result = new JsonObject();
            
            //Add fields to the Json object
            result.add("item_name", new JsonPrimitive(item.getItem_name()));
            result.add("item_type", new JsonPrimitive(item.getItem_type()));
            result.add("item_id", new JsonPrimitive(item.getItem_id()));
            result.add("library_id", new JsonPrimitive(item.getLibraryID()));
            result.add("checkedOut", new JsonPrimitive(item.isCheckedOut()));
            result.add("returnDate", new JsonPrimitive(item.returnDate()));
            
            //Add Item specific fields
            //If a book, add item_author when writing out
            if (item instanceof BookItem) {
            	result.add("item_author", new JsonPrimitive(((BookItem) item).getItem_author()));
            }
            //If a CD, add artist when writing
            else if (item instanceof CD) {
            	result.add("item_artist", new JsonPrimitive(((CD) item).getItem_artist()));
            }
            //If a Magazine, add volume when writing
            else if (item instanceof Magazine) {
            	result.add("item_volume", new JsonPrimitive(((Magazine) item).getVolume()));
            }
            //Return a Json object
            return result;
        }
    }
    
    /**
     * Takes in a Multi-Map and saves everything
     * @param libraryMap
     */
    public static void SerializeLibraryCatalog(Multimap<String, Item> libraryMap) {
    	//Create an array that holds Strings
        ArrayList<String> items = new ArrayList<String>();
        
        //Create a GSON Builder, which will use info from Item
        com.google.gson.Gson gson = new GsonBuilder().registerTypeAdapter(Item.class, new ItemSerializer()).create();
        
        //For all the Items from the passed in Multi-Map
		for (Item item : libraryMap.values()){
			//Set a string to equal the JSON object of an Item
			String jo = gson.toJson(item);
			//Add this String to your ArrayList
			items.add(jo);
		}
		
		//Create a StringBuilder, which will store all Items with the JSON file into an array called library_items
		StringBuilder sb = new StringBuilder("{\n  \"library_items\":\n  [\n");
		
		//For everything in your ArrayList
		for (int i = 0; i < items.size() - 1; i++){
			sb.append("    {\n");
			
			//Get the i'th element
			String string = "      " + items.get(i);
			sb.append(string);
			
			sb.append(",\n");
			
		}
		
		//Your String builder is complete, close the syntax of the JSON array
		sb.append(items.get(items.size() - 1) + "\n    }\n  ]\n}");
		
		//System.out.println(sb);
		
		//Attempt to serialize to JSON
		try {
			//Name your save file
			File file = new File("libcat.json");
			
			//If the file does not exist, create it
			if (!file.exists()){
				file.createNewFile();
			}
			
			//Get a Printstream ready to write to your previously declared file 
			PrintStream out = new PrintStream(new FileOutputStream("libcat.json"));
			
			//Print all elements you put in the Stringbuilder, close when done
			out.print(sb);
			out.close();			
		} catch (Exception ex) {
			System.out.println("Error writing file");
		}
    }
    
    public static void main(final String[] args) {
    	JsonParser parser;
    	
    	//You have two items
        DVD dvd = new DVD("Shut Up, Little Man",  "DVD", "shutu01", 0, 1);
        Magazine mag = new Magazine("FATE",  "Magazine", "fatem01", "Volume 1", 0, 1);
        
        try {
        	//You access a file off of HD
            File file = new File("hello.json");
            //Get a buffer of that file
    		BufferedReader br = new BufferedReader(new FileReader(file));
    		
    		//JsonParser(bufferedReader, libraryID)
            parser = new JsonParser(br, 0);
            parser.parse();
            
            //Get a Map of all Items
            Multimap<String, Item> libraryMap = ArrayListMultimap.create();
            
            //Create a new Library, put the Map of all Items in that Library's Map
            //Library lib = new Library(0, libraryMap);
            
            //Add your two items to Library's Map
            //lib.addToLibraryCatalog(dvd);
            //lib.addToLibraryCatalog(mag);
            
            libraryMap.put(dvd.getItem_id(), dvd);
            libraryMap.put(mag.getItem_id(), mag);
            
            //Save this Multi-Map to a file
            LibrarySerializer.SerializeLibraryCatalog(libraryMap);
        }
        catch (FileNotFoundException ex) {
        	
        }
    }


}
