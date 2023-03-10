package chess.factory;

import chess.enums.Color;
import chess.enums.Direction;
import chess.enums.PieceType;
import lombok.val;
import chess.models.pieces.Piece;

public class PieceFactory {

    public static Piece getPiece(PieceType type, Color color){
        val piece = type.getConstructor().get();
        piece.setColor(color);
        if(PieceType.PAWN.equals(type))
            piece.setDirection(color.equals(Color.WHITE) ? Direction.DOWN: Direction.UP);
        return piece;
    }
}
