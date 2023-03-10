package chess.models.pieces;


import chess.conditions.Conditions;
import chess.enums.Color;
import chess.helpers.Checker;
import lombok.val;
import chess.models.Cell;
import chess.models.ValidationProps;


public class Pawn extends Piece {


    @Override
    public Boolean canMove(Cell from, Cell to) {
        val validationProps = new ValidationProps(this, from, to);
        val checker = new Checker(validationProps);
        // here command pattern is utilized to check for different move conditions on a piece
        return checker.and(Conditions.BASE_MOVE_VALIDATIONS) &&
                (checker.or(Conditions.PAWN_VERTICAL_MOVE_VALIDATIONS) && checker.is(Conditions.CELL_FREE_VALIDATION)) ||
                (checker.is(Conditions.PAWN_DIAGONAL_MOVE_VALIDATION) && checker.is(Conditions.OPPONENT_CELL_VALIDATION));
    }
}
