package chess.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import chess.models.pieces.Piece;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class Cell {
    // y is col in matrix
    private final Integer x;
    // x is row in matrix
    private final Integer y;
    @Setter
    private Piece piece;

    public boolean isFree(){
        return Objects.isNull(this.piece);
    }

}
