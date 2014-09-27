package questions.chapter19.q2;

import java.util.*;
import java.lang.*;

public class Q2
{
    public enum Player
    {
        NULL(0),
        NAUGHTS(-1),
        CROSSES(1);
        
        private final int value;

        private Player( int value )
        {
            this.value = value;
        }
    }

    class Game
    {
        private Player [][] board;

        public Game()
        {
            board = new Player[3][3];
        }

        public Player getPlayerAt(int x, int y)
        {
            return board[x][y];
        }
    }


    public static void main( String [] args )
    {
        Game game = new Q2().new Game(); 
    }

    // O(n)
    public Player hasWinner1( Game game )
    {
        // check rows
        for ( int j = 0; j < 3; j++ )
        {
            Player lastPlayer = null;
            boolean success = true;
            for ( int i = 0; i < 3; i++ )
            {
                Player currentPlayer = game.getPlayerAt(i,j);
                if ( lastPlayer == null )
                {
                    lastPlayer = currentPlayer;
                }
                else if ( lastPlayer != currentPlayer || currentPlayer == Player.NULL )
                {
                    success = false;
                    break;
                }
            }

            if ( success )
            {
                return lastPlayer;
            }
        }

        // check cols
        for ( int j = 0; j < 3; j++ )
        {
            Player lastPlayer = null;
            boolean success = true;
            for ( int i = 0; i < 3; i++ )
            {
                Player currentPlayer = game.getPlayerAt(j,i);
                if ( lastPlayer == null )
                {
                    lastPlayer = currentPlayer;
                }
                else if ( lastPlayer != currentPlayer || currentPlayer == Player.NULL )
                {
                    success = false;
                    break;
                }
            }

            if ( success )
            {
                return lastPlayer;
            }
        }

        Player lastPlayer = null;
        boolean success = true;
        for ( int i = 0; i < 3; i++ )
        {
            Player currentPlayer = game.getPlayerAt(i,i);

            if ( lastPlayer == null )
            {
                lastPlayer = currentPlayer;
            }
            else if ( lastPlayer != currentPlayer || currentPlayer == Player.NULL )
            {
                success = false;
                break;
            }
        }

        if ( success )
        {
            return lastPlayer;
        }

        lastPlayer = null;
        success = true;
        for ( int i = 0; i < 3; i++ )
        {
            Player currentPlayer = game.getPlayerAt(2-i,i);

            if ( lastPlayer == null )
            {
                lastPlayer = currentPlayer;
            }
            else if ( lastPlayer != currentPlayer || currentPlayer == Player.NULL )
            {
                success = false;
                break;
            }
        }

        if ( success )
        {
            return lastPlayer;
        }

        return Player.NULL;
    }
}
