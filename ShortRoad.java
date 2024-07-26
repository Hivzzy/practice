import java.util.ArrayList;
import java.util.Scanner;

public class ShortRoad {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> inputLines = new ArrayList<>();

        System.out.println("Masukkan pola (akhiri dengan baris kosong):");

        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            inputLines.add(line);
        }

        int rows = inputLines.size();
        int cols = inputLines.get(0).length();

        char[][] arr = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String line = inputLines.get(i);
            for (int j = 0; j < cols; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        char targetChar1 = '*';
        char targetChar2 = '^';

        int[] posisiTujuan = findCharacterPosition(arr, targetChar1);
        int[] posisiAwal = findCharacterPosition(arr, targetChar2);

        if (posisiTujuan != null && posisiAwal != null) {
            checkRoad(arr, posisiTujuan, posisiAwal);
        } else {
            System.out.println("Tidak ditemukan posisi awal atau tujuan.");
        }

        scanner.close();
    }

    public static int[] findCharacterPosition(char[][] arr, char targetChar) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == targetChar) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void checkRoad(char[][] arr, int[] posisiTujuan, int[] posisiAwal) {
        int x = posisiAwal[0];
        int y = posisiAwal[1];

        int totalStepsDown = 0;
        int totalStepsUp = 0;
        int totalStepsRight = 0;
        int totalStepsLeft = 0;

        while (x != posisiTujuan[0] || y != posisiTujuan[1]) {
            boolean moved = false;

            if (x < posisiTujuan[0] && arr[x + 1][y] != '#') {
                x++;
                totalStepsDown++;
                moved = true;
                System.out.println("Langkah ke bawah: " + totalStepsDown);
            } else if (x > posisiTujuan[0] && arr[x - 1][y] != '#') {
                x--;
                totalStepsUp++;
                moved = true;
                System.out.println("Langkah ke atas: " + totalStepsUp);
            } else if (y < posisiTujuan[1] && arr[x][y + 1] != '#') {
                y++;
                totalStepsRight++;
                moved = true;
                System.out.println("Langkah ke kanan: " + totalStepsRight);
            } else if (y > posisiTujuan[1] && arr[x][y - 1] != '#') {
                y--;
                totalStepsLeft++;
                moved = true;
                System.out.println("Langkah ke kiri: " + totalStepsLeft);
            }

            if (!moved) {
                System.out.println("Tidak dapat menemukan jalur tanpa terhalang.");
                return;
            }
        }

        int totalSteps = totalStepsDown + totalStepsUp + totalStepsRight + totalStepsLeft;
        System.out.println("Total langkah: " + totalSteps);
        System.out.println("Langkah ke bawah: " + totalStepsDown);
        System.out.println("Langkah ke atas: " + totalStepsUp);
        System.out.println("Langkah ke kanan: " + totalStepsRight);
        System.out.println("Langkah ke kiri: " + totalStepsLeft);
    }
}
