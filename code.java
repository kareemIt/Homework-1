import java.io.File;
import java.io.IOException;
import java.util.*;
//I tried using a linked list but I had a hard time so I used a arraylist so I hope that this is okay.
public class topStreamers {
    public static void main (String [] args){
        File inputFile = new File("regional-global-weekly-2020-01-17--2020-01-24.csv");
        Artist[] artistArr = new Artist[200];
        try {
            Scanner reader = new Scanner(inputFile);
            reader.nextLine();
            reader.nextLine();
            int size = 0;
            while(reader.hasNext()) {
                String line = reader.nextLine();
                String[] track = line.split(",");
                int index = findArtist(artistArr, size, track[2].replace("\"", ""));
                if (index != -1) {
                    artistArr[index].incrementTracks();
                } else if (index == -1){
                    artistArr[size] = new Artist(track[2].replace("\"", ""));
                    size++;
                }
            }

            Artist[] newArr = Arrays.copyOf(artistArr, size);
            Arrays.sort(newArr);
            printArr(newArr, size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int findArtist(Artist[] arr, int size, String artistName) {
        for(int x = 0; x < size; x++) {
            if(arr[x].equals(artistName)) {
                return x;
            }
        }
        return -1;
    }

    public static void printArr(Artist[] arr, int size) {
        for(int x = 0; x < size; x++) {
            System.out.println(arr[x]);
        }
    }

}

class Artist implements Comparable {
    private String name;
    private int numOfTracks;

    public Artist(String name) {
        this.name = name;
        this.numOfTracks = 1;
    }

    public String getName() {
        return name;
    }

    public int getNumOfTracks() {
        return numOfTracks;
    }

    public void incrementTracks() {
        numOfTracks++;
    }

    public boolean equals(String name) {
        return this.name.equals(name);
    }

    public String toString() {
        return name + " " + numOfTracks;
    }

    @Override
    public int compareTo(Object o) {

        return this.name.toLowerCase().compareTo(((Artist) (o)).getName().toLowerCase());
    }
}
