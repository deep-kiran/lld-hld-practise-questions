package chess.validators;

import lombok.val;
import chess.models.Board;
import chess.models.ValidationProps;

public class HorizontalBeamMove implements IMoveValidator {

    @Override
    public Boolean validate(ValidationProps props) {
        val piece = props.getPiece();
        val from = props.getFrom();
        val to = props.getTo();
        val board = Board.getBoard();
        if (!from.getX().equals(to.getX())) return false;
        for(int y = Math.min(from.getY(), to.getY())+1; y<Math.max(from.getY(), to.getY()); y++){
            val cell = board.getCell(from.getX(), y);
            if(!cell.isFree()) return false;
        }
        return true;
    }
}
