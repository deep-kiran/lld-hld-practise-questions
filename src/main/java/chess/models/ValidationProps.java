package chess.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import chess.models.pieces.Piece;

@RequiredArgsConstructor
@Getter
public class ValidationProps {
    private final Piece piece;
    private final Cell from;
    private final Cell to;
}
