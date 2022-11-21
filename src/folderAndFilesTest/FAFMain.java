package folderAndFilesTest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class FAFMain {

	public static void main(String[] args) {
		// Needed later
		int choice;
		Path pathToArtist;
		File fileToArtist;
		String[] artistList;
		Path pathToAlbum;
		File fileToAlbum;
		String[] albumList;
		Path pathToTrack;
		File fileToTrack;
		String[] trackList;
		ArrayList<String> fullList = new ArrayList<String>();

		// Get location of folder and type of shuffle
		System.out.println("Enter path to top-level folder, leave empty if default (E:/Music)");
		Scanner scanner = new Scanner(System.in);
		String uesrPath = scanner.nextLine();
		if (uesrPath.equals("")) {
			uesrPath = "E:/Music";
		}
		System.out.println("Choose: \n(1) Artist Shuffle\n(2) Album Shuffle\n(3) Individual Tracks");
		while (true) {
			choice = scanner.nextInt();
			if (choice > 3 || choice < 1) {
				System.out.println("Invalid choice");
			} else {
				break;
			}
		}
		scanner.close();

		// Make into a file and output choices
		Path pathToGenre = Paths.get(uesrPath);
		File fileToGenre = pathToGenre.toFile();
		String[] genreList = fileToGenre.list();
		System.out.println("Readback: " + uesrPath);
		switch (choice) {
		case 1:
			System.out.println("Readback: Searching for Artists");
			break;
		case 2:
			System.out.println("Readback: Searching for Albums");
			break;
		case 3:
			System.out.println("Readback: Searching for Individual Tracks");
			break;
		default:
			break;
		}

		// Searching
		for (int i = 0; i < genreList.length; i++) {
			if(Paths.get(uesrPath, genreList[i]).toFile().isFile()) {
			System.out.println("Ignored " + genreList[i]);
			} 
			else if (genreList[i].contains("!")) {
				System.out.println("Ignored " + genreList[i]);
			} else {
					System.out.println("Reading " + genreList[i]);
					pathToArtist = Paths.get(uesrPath, genreList[i]);
					fileToArtist = pathToArtist.toFile();
					artistList = fileToArtist.list();
					for (int o = 0; o < artistList.length; o++) {
						if(Paths.get(uesrPath, genreList[i], artistList[o]).toFile().isFile()) {
							System.out.println("Ignored " + artistList[o]);
							} 
						else if (choice == 1) {
							System.out.println("Found " + artistList[o]);
							fullList.add(artistList[o]);
						} else {
							System.out.println("Reading " + artistList[o]);
							pathToAlbum = Paths.get(uesrPath, genreList[i], artistList[o]);
							fileToAlbum = pathToAlbum.toFile();
							albumList = fileToAlbum.list();
							for (int z = 0; z < albumList.length; z++) {
								if(Paths.get(uesrPath, genreList[i], artistList[o], albumList[z]).toFile().isFile()) {
									System.out.println("Ignored " + albumList[z]);
									} 
								
								else if(choice == 2) {
									System.out.println("Found " + albumList[z]);
									fullList.add(artistList[o] + " - " + albumList[z]);
								} else {
									System.out.println("Reading " + albumList[z]);
									pathToTrack = Paths.get(uesrPath, genreList[i], artistList[o], albumList[z]);
									fileToTrack = pathToTrack.toFile();
									trackList = fileToTrack.list();
									for (int y = 0; y < trackList.length; y++) {
										if (!trackList[y].contains(".jpg")) {
											if (!trackList[y].contains(".png")) {
												System.out.println("Found " + trackList[y]);
												fullList.add(
														artistList[o] + " - " + albumList[z] + " - " + trackList[y]);
											} else {
												System.out.println("Ignored " + trackList[y]);
											}
										} else {
											System.out.println("Ignored " + trackList[y]);
										}
									}
								}
							}
						}
					}
				}
		}

		// Find the requested item
		Random r = new Random();
		int rr = r.nextInt(fullList.size());
		String s = fullList.get(rr);
		if (choice == 1) {
			System.out.println("Randomised Artist: " + s);
		} else if (choice == 2) {
			System.out.println("Randomised Album: " + s);
		} else {
			System.out.println("Randomised Track: " + s);
		}
	}
}