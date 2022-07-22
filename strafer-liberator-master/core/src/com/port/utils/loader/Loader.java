package com.port.utils.loader;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.files.FileHandle;
import com.port.world.WorldData;

/*
 * incarca matricele pentru pathfind in WorldData
 */

public abstract class Loader {

	public static boolean[][] loadMatrix(FileHandle fileHandle) {

		boolean[][] mat = new boolean[WorldData.maxLengthWorld + 2][WorldData.maxWidthWorld + 2];
		if (Gdx.app.getType().equals(ApplicationType.Android)) {
			String fs = fileHandle.readString();
			int i = 1, j = 1;
			String[] lines = fs.split("\n");
			for (String line : lines) {
				String[] columns = line.split(" ");
				for (String col : columns) {
					if (col.equals("-1")) {
						mat[i][j++] = true;
					} else {
						mat[i][j++] = false;
					}
				}
				j = 1;
				i++;
			}
		} else {

			try {
				Scanner scan = new Scanner(fileHandle.file());

				for (int i = 0; i < WorldData.maxLengthWorld; i++) {

					for (int j = 0; j < WorldData.maxWidthWorld; j++) {
						// System.out.format("%d ", mat[i][j]=scan.nextInt());
						int val = scan.nextInt();
						if (val == -1) {
							mat[i][j] = true;
						} else {
							mat[i][j] = false;
						}
					}

				}

				scan.close();

			} catch (InputMismatchException e) {
				System.out.print(e.getMessage()); // try to find out specific reason.
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
				return null;
			}

		}
		return mat;
	}

}
