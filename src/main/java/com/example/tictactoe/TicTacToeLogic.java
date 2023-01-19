package com.example.tictactoe;

import java.util.Random;

public class TicTacToeLogic {

    private char move;
    private boolean xTurn;
    private boolean oTurn;
    private boolean endGame;
    private int currentRound;

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public char getMove() {
        return move;
    }

    public int getCurrentRound() {
        return this.currentRound;
    }


    public void randomStart() {
        Random rnd = new Random();
        int randomStart = rnd.nextInt(1, 3);
        if (randomStart == 1) {
            xTurn = true;
        }
        if (randomStart == 2) {
            oTurn = true;
        }
    }

    public char currentMove() {
        if (xTurn) {
            move = 'X';
            xTurn = false;
            oTurn = true;

            return 'X';

        } else if (oTurn) {
            move = 'O';
            oTurn = false;
            xTurn = true;
            return 'O';
        }
        return move;
    }


    public void playMove(int x, int y, char move, char[][] board) {
        board[x][y] = move;
        setCurrentRound(getCurrentRound() + 1);
    }

    public boolean isFieldTaken(int x, int y, char[][] board) {
        boolean isFieldTaken;
        if (board[x][y] == 'X' || board[x][y] == 'O') {
            System.out.println("field taken");
            isFieldTaken = true;
        } else {
            isFieldTaken = false;
        }
        return isFieldTaken;
    }

    public boolean checkForDraw(int boardSize, int currentRound) {
        boolean result = false;
        if (boardSize == 3) {
            if (currentRound == 9) {
                endGame = true;
                System.out.println("draw");
                result = true;
            }
        }
        if (boardSize == 10) {
            if (currentRound == 100) {
                endGame = true;
                System.out.println("draw");
                result = true;
            }
        }
        return result;
    }

    public boolean checkForWinInRows(char[][] board, int boardSize, char move, int x, int y) {
        int count = 0;
        for (int i = 0; i <= board.length - 1; i++) {
            if (board[x][i] == move) {
                count++;
            }
            if (board[x][i] != move) {
                count = 0;
            }
            if (count == 3 && boardSize == 3) {
                endGame = true;
                return true;

            } else if (count == 5 && boardSize == 10) {
                endGame = true;
                return true;
            }
        }
        return false;
    }

    public boolean checkForWinInColumns(char[][] board, int boardSize, char move, int x, int y) {
        int count = 0;
        for (int i = 0; i <= board.length - 1; i++) {
            if (board[i][y] == move) {
                count++;
            }
            if (board[i][y] != move) {
                count = 0;
            }
            if (count == 3 && boardSize == 3) {
                return true;
            } else if (count == 5 && boardSize == 10) {
                return true;
            }
        }
        return false;
    }

    public boolean checkForWinInFirstCross(char[][] board, int boardSize, char move, int x, int y) {
        int count = 0;
        for (int i = 0; i <= boardSize - 1; i++) {
            if (board[i][i] == move) {
                count++;
            }
            if (board[i][i] != move) {
                count = 0;
            }
            if (count == 3 && boardSize == 3) {
                return true;
            } else if (count == 5 && boardSize == 10) {
                return true;
            }
        }

        return false;
    }

    public boolean checkForWinInSecondCross(char[][] board, int boardSize, char move, int x, int y) {
        int count = 0;
        for (int i = 0; i <= board.length - 1; i++) {
            if (board[i][(board.length - 1) - i] == move) {
                count++;
            }
            if (board[i][(board.length - 1) - i] != move) {
                count = 0;
            }
            if (count == 3 && boardSize == 3) {
                return true;
            } else if (count == 5 && boardSize == 10) {
                return true;
            }
        }
        return false;
    }


    public boolean checkIfSomeOneWon(TicTacToeGameState ticTacToeGameState) {
        if (ticTacToeGameState.getMoveOnX() == ticTacToeGameState.getMoveOnY()) {
            if (checkForWinInFirstCross(ticTacToeGameState.getBoard(),
                    ticTacToeGameState.getBoardSize(),
                    move,
                    ticTacToeGameState.getMoveOnX(),
                    ticTacToeGameState.getMoveOnY())) {
                endGame = true;
                return true;
            }
        }
        if (ticTacToeGameState.getMoveOnX() + ticTacToeGameState.getMoveOnY() == ticTacToeGameState.getBoardSize() - 1) {
            if (checkForWinInSecondCross(ticTacToeGameState.getBoard(),
                    ticTacToeGameState.getBoardSize(),
                    move,
                    ticTacToeGameState.getMoveOnX(),
                    ticTacToeGameState.getMoveOnY())) {
                endGame = true;
                return true;
            }
        }
        if (checkForWinInRows(ticTacToeGameState.getBoard(),
                ticTacToeGameState.getBoardSize(),
                move,
                ticTacToeGameState.getMoveOnX(),
                ticTacToeGameState.getMoveOnY()) ||
                checkForWinInColumns(ticTacToeGameState.getBoard(),
                        ticTacToeGameState.getBoardSize(),
                        move,
                        ticTacToeGameState.getMoveOnX(),
                        ticTacToeGameState.getMoveOnY())) {
            endGame = true;
            return true;
        }
        return false;
    }
}