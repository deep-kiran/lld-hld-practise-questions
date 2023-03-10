package chess.models.pieces;

import chess.enums.Color;
import chess.enums.Direction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import chess.models.Cell;

@Getter
public abstract class Piece {
    @Setter
    private Color color;
    @Setter
    private Boolean isKilled = false;
    @Setter
    private Direction direction = Direction.BOTH;
    @Setter
    private int moves = 0;
    public Boolean canMove(Cell from, Cell to){return false;}

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", isKilled=" + isKilled +
                ", direction=" + direction +
                ", moves=" + moves +
                '}';
    }
}
