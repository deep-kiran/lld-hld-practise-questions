package chess.models.pieces;

import chess.conditions.Conditions;
import chess.enums.Color;
import chess.helpers.Checker;
import lombok.val;
import chess.models.Cell;
import chess.models.ValidationProps;

public class Knight extends Piece{

    @Override
    public Boolean canMove(Cell from, Cell to) {
        val props = new ValidationProps(this, from, to);
        val checker = new Checker(props);
        return checker.or(Conditions.KNIGHT_MOVE_VALIDATIONS) && checker.or(Conditions.CELL_SAFE_VALIDATIONS);
    }
}
