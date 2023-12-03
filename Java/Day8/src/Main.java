import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File f = new File("src\\day8.txt");
        String line;
        Scanner sn = new Scanner(f);
        int[][] trees = new int[100][100];

        int j = 0;
        int rowSize = 0;
        while(sn.hasNextLine()) {
            line = sn.nextLine();
            rowSize = line.length();
            for (int i = 0; i < line.length(); i++) {
                trees[j][i] = Integer.parseInt(String.valueOf(line.charAt(i)));
            }
            j++;
        }

        int columnSize = j;

        int totalVisibleTrees = ((columnSize - 2) * 2) + ((rowSize - 2) * 2) + countVisibleTrees(trees, columnSize, rowSize);
        System.out.println("SOLUTION 1: " +totalVisibleTrees);

        System.out.println("SOLUTION 2: "+findHighestScenicScore(trees, columnSize, rowSize));
    }

    private static int findHighestScenicScore(int[][] trees, int columnSize, int rowSize) {
        int highestScore = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 1; i < columnSize - 1; i++) {
            for (int j = 1; j < rowSize - 1; j++) {
                int temp = findTreeScore(trees, i, j, columnSize, rowSize);
                if(max < temp) {
                    max = temp;
                }
            }
        }

        return max;
    }

    private static int findTreeScore(int[][] trees, int i, int j, int columnSize, int rowSize) {
        int treeValue = trees[i][j];

        return UpScenic(trees, treeValue, i, j) * DownScenic(trees, treeValue, i, j, columnSize) * LeftScenic(trees, treeValue, i, j) * RightScenic(trees, treeValue, i, j, rowSize);

    }

    private static int RightScenic(int[][] trees, int treeValue, int columnPosition, int rowPosition, int rowSize) {
        int count = 0;
        for (int i = (columnPosition + 1); i < rowSize; i++) {
            if(treeValue > trees[i][rowPosition]){
                count++;
            }
            else{
                count++;
                break;
            }
        }
        return count;
    }

    private static int LeftScenic(int[][] trees, int treeValue, int columnPosition, int rowPosition) {
        int count = 0;
        for (int i = (columnPosition - 1); i >= 0 ; i--) {
            if(treeValue > trees[i][rowPosition]) {
                count++;
            } else{
                count++;
                break;
            }
        }
        return count;
    }

    private static int DownScenic(int[][] trees, int treeValue, int columnPosition, int rowPosition, int columnSize) {
        int count = 0;
        for (int i = (rowPosition + 1); i < columnSize; i++) {
            if(treeValue > trees[columnPosition][i]) {
                count++;
            }
            else{
                count++;
                break;
            }
        }
        return count;
    }

    private static int UpScenic(int[][] trees, int treeValue, int columnPosition, int rowPosition) {
        int count = 0;
        for (int i = (rowPosition - 1); i >= 0 ; i--) {
            if(treeValue > trees[columnPosition][i]) {
                count++;
            }
            else{
                count++;
                break;
            }
        }
        return count;
    }

    private static int countVisibleTrees(int[][] trees, int columnSize, int rowSize) {
        int totalVisibleTrees = 0;

        for (int i = 1; i < columnSize - 1; i++) {
            for (int j = 1; j < rowSize - 1; j++) {
                if(visibleTree(trees, i, j, columnSize, rowSize)) {
                    totalVisibleTrees++;
                    System.out.println(totalVisibleTrees);
                }
            }
        }
        System.out.println();
        return totalVisibleTrees;
    }

    private static boolean visibleTree(int[][] trees, int columnPosition , int rowPosition, int columnSize, int rowSize) {
        int treeValue = trees[columnPosition][rowPosition];

        return visibleToUp(treeValue, trees, columnPosition, rowPosition)
                || visibleToDown(treeValue, trees, columnPosition, rowPosition, rowSize)
                || visibleToLeft(treeValue, trees, columnPosition, rowPosition)
                || visibleToRight(treeValue, trees, columnPosition, rowPosition, columnSize);
    }

    private static boolean visibleToUp(int treeValue, int[][] trees, int columnPosition, int rowPosition) {
        for (int i = (rowPosition - 1); i >= 0; i--) {
            if(treeValue <= trees[i][columnPosition]) {
                return false;
            }
        }
        return true;
    }

    private static boolean visibleToDown(int treeValue, int[][] trees, int columnPosition, int rowPosition, int rowSize) {
        for (int i = (rowPosition + 1); i < rowSize; i++) {
            if(treeValue <= trees[i][columnPosition]) {
                return false;
            }
        }
        return true;
    }

    private static boolean visibleToLeft(int treeValue, int[][] trees, int columnPosition, int rowPosition) {
        for (int i = (columnPosition - 1); i >= 0; i--) {
            if(treeValue <= trees[rowPosition][i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean visibleToRight(int treeValue, int[][] trees, int columnPosition, int rowPosition, int columnSize) {
        for (int i = (columnPosition + 1); i < columnSize; i++) {
            if(treeValue <= trees[rowPosition][i]) {
                return false;
            }
        }
        return true;
    }

}