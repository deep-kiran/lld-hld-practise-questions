package chess;

import chess.enums.Color;
import chess.enums.PieceType;
import chess.factory.PieceFactory;
import lombok.val;
import chess.models.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChessTest {

    @Test
    public void setup(){
        // initialize a board
        val board = Board.createBoard(8);
        val cell_1_0 = board.getCell(1, 0);
        val cell_2_0 = board.getCell(2, 0);
        val piece = cell_1_0.getPiece();
        System.out.println(piece.toString());
        val result = piece.canMove(cell_1_0,cell_2_0);
        Assertions.assertTrue(result);
    }
}
