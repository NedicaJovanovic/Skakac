package chess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Skakac {
	
	private static char[][] ucitaj(String putanjaDoFajla) throws IOException{
		try(BufferedReader r = new BufferedReader(new FileReader(putanjaDoFajla))){
			char matrix[][] = new char[8][8];
			
			for(int i = 0; i < matrix.length; i++) {
				String line = r.readLine();
				for(int j = 0; j < matrix[i].length; j++) {
					matrix[i][j] = line.charAt(j);
				}
			}
			
			return matrix;
		}
	}
	
	private static boolean legalniIndeksi(char[][] matrix, int i, int j) {
		if(i>=0 && i<matrix.length)
			if(j>=0 && j<matrix[i].length)
				return true;
		return false;
	}
	
	
	private static String jede(char[][] matrix, int i, int j) {
		int[][] pomeraji = { {-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}};
		String out = "";
		
		for (int k=0; k<pomeraji.length; k++) {
			int i2 = i + pomeraji[k][0];
			int j2 = j + pomeraji[k][1];
			
			if (legalniIndeksi(matrix, i2, j2) && matrix[i2][j2]!='O')
				out += String.format("%c(%c%d) ", matrix[i2][j2], 'A' + j2, 8 - i2); // koristimo svojstvo sabiranja charova i intova tako je: 'A' + 1 = 'B', 'A' + 2 = 'C' itd.  
		}
		return out;
	}
	
	public static void main(String[] args) {
		try {
			char[][] crni = ucitaj("crni.txt");
			char[][] beli = ucitaj("beli.txt");
			
			for(int i=0; i<crni.length; i++)
				for(int j=0; j<crni[i].length; j++)
					if(beli[i][j] == 'S' && !jede(crni,i,j).equals(""))
						System.out.println(String.format("S(%c%d): %s", 'A' + j, 8 - i, jede(crni,i,j)));
						
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}