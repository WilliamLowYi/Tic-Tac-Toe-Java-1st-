import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();


    public static void main(String[] args) {
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };

        // printGameBoard(gameBoard());
        while (true)    {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9):");
            int playerPosi = scan.nextInt();
            while(playerPositions.contains(playerPosi) || cpuPositions.contains(playerPosi))    {
                System.out.println("Position Taken! Enter a correct position: ");
                playerPosi = scan.nextInt();
            }
            placePiece(gameBoard, playerPosi, "player");
            String result = checkWinner();
            if (result.length() > 0)    {
                System.out.println(result);
                printGameBoard(gameBoard);
                break;
            }

            Random rand = new Random();
            int cpuPosi = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPosi) || cpuPositions.contains(cpuPosi))  {
                System.out.println("Position Taken! CPU");
                cpuPosi = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPosi, "cpu");

            printGameBoard(gameBoard);
            if (result.length() > 0)    {
                System.out.println(result);
                break;
            }
        }
    }


    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char column : row) {
                System.out.print(column);
            }
            System.out.println();

        }
    }


    public static void placePiece(char[][] gameBoard, int posi, String user)  {
        char symBol = ' ';

        if(user.equals("player"))   {
            symBol = 'X';
            playerPositions.add(posi);
        } else if (user.equals("cpu"))  {
            symBol = 'O';
            cpuPositions.add(posi);
        }

        switch (posi) {
            case 1:
                gameBoard [0][0] = symBol;
                break;
            case 2:
                gameBoard [0][2] = symBol;
                break;
            case 3:
                gameBoard [0][4] = symBol;
                break;
            case 4:
                 gameBoard [2][0] = symBol;
                break;
            case 5:
                gameBoard [2][2] = symBol;
                break;
            case 6:
                gameBoard [2][4] = symBol;
                break;
            case 7:
                gameBoard [4][0] = symBol;
                break;
            case 8:
                gameBoard [4][2] = symBol;
                break;
            case 9:
                gameBoard [4][4] = symBol;
                break;
            default:
                break;

        }
    }

    public static String checkWinner()  {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List firColumn = Arrays.asList(1, 4, 7);
        List secColumn = Arrays.asList(2, 5, 8);
        List thrColumn = Arrays.asList(3, 6, 9);
        List botLeft = Arrays.asList(7, 5, 3);
        List botRight = Arrays.asList(1, 5, 9);

        /* List of list */
        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(firColumn);
        winningConditions.add(secColumn);
        winningConditions.add(thrColumn);
        winningConditions.add(botLeft);
        winningConditions.add(botRight);

        for(List l : winningConditions) {
            if (playerPositions.containsAll(l))  {
                return "Congratulations You Win !!";
            } else if (cpuPositions.containsAll(l))  {
                return "CPU Win! Sorry :(";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "DRAW";
            }
        }

        return "";
    }

}
