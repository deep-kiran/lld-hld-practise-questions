package chess.validators;

import lombok.val;
import chess.models.ValidationProps;


public class CellUnoccupied implements IMoveValidator {
    @Override
    public Boolean validate(ValidationProps props) {
        val to = props.getTo();
        return to.isFree();
    }
}
