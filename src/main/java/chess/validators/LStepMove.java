package chess.validators;

import lombok.val;
import chess.models.Board;
import chess.models.ValidationProps;

public class LStepMove implements IMoveValidator {
    @Override
    public Boolean validate(ValidationProps props) {
        val from = props.getFrom();
        val to = props.getTo();
        val xDiff = Math.abs(from.getX() - to.getX());
        val yDiff = Math.abs(from.getY() - to.getY());
        return xDiff * yDiff == 2;
    }
}
