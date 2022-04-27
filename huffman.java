import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Comparator;

import java.io.*;
import java.util.*;

public class huffman {
	
	public static void main(String[] args) throws Exception
    {
		List<Node> letterfrequencies = new ArrayList<>();
		
		try {
            File file = new File(args[0]);
			FileReader fr = new FileReader(file);
			String text = "";
			
			int i;
			while ((i = fr.read()) != -1) {
				text += (char)i;
				boolean hasChar = false;
				
				for (int j = 0; j < letterfrequencies.size(); j++) {
					if(letterfrequencies.get(j).getLetter() == (char)i) {
						letterfrequencies.get(j).incrementFreq();
						hasChar = true;
					}
				}
				
				if(!hasChar) {
					letterfrequencies.add(new Leaf((char)i, 1));
				}
			}
			
			// Sort by frequency
			Collections.sort(letterfrequencies);
			
			// Print file text
			System.out.println(text);
			
			Node[] leafNodes = new Node[letterfrequencies.size()];
			for (int j = 0; j < leafNodes.length; j++) {
				leafNodes[j] = letterfrequencies.get(j);
			}
			
			while (letterfrequencies.size()>1) {
				if(letterfrequencies.get(0).getFrequency()<letterfrequencies.get(1).getFrequency()) {
					letterfrequencies.add(new Branch(
						letterfrequencies.get(1),
						letterfrequencies.get(0)
					));
				} else {
					letterfrequencies.add(new Branch(
						letterfrequencies.get(0),
						letterfrequencies.get(1)
					));
				}
				 
				letterfrequencies.remove(0);
				letterfrequencies.remove(0);
				Collections.sort(letterfrequencies);
			}
			
			letterfrequencies.get(0).setCodes();
			
			System.out.println("Char\tASCII\tFreq\tCode\tLength");
			
			for (int j=leafNodes.length-1; j >= 0; j--) {
				System.out.println(
					leafNodes[j].getLetter() + "\t" +
					(int)leafNodes[j].getLetter() + "\t" +
					leafNodes[j].getFrequency() + "\t" +
					leafNodes[j].getCode() + "\t" +
					leafNodes[j].getCode().length()
				);
			}
			
			String compressedText = "";
			
			for(int j=0; j<text.length(); j++) {
				for(int l=0; l<leafNodes.length; l++) {
					if(text.charAt(j)==leafNodes[l].getLetter()) {
						compressedText += leafNodes[l].getCode();
						break;
					}
				}
			}
			
			System.out.println("Length of original message      ="+ text.length()+ " characters");
			System.out.println("Length of coded message         ="+ compressedText.length()+ " bits");
			System.out.println("Compression rate vs 7-bit ASCII ="+ 100*compressedText.length()/text.length()/7.0+ "%");
			System.out.println("Compression rate vs 8-bit       ="+ 100*compressedText.length()/text.length()/8.0+ "%");
			
			System.out.println("\nEncoded message:");
			String formattedBits = compressedText.replaceAll("(.{64})", "$1\n");
			System.out.println(formattedBits);
			
        } catch (IOException ioException) {
            System.err.println("Cannot open file.");
            System.exit(1);
        }
 
        
    }
	
}