package chess.validators;

import lombok.val;
import chess.models.Board;
import chess.models.ValidationProps;

public class VerticalBeamMove implements IMoveValidator{
    @Override
    public Boolean validate(ValidationProps props) {
        val piece = props.getPiece();
        val from = props.getFrom();
        val to = props.getTo();
        val board = Board.getBoard();
        if (!from.getY().equals(to.getY())) return false;
        for(int x = Math.min(from.getX(), to.getX())+1; x<Math.max(from.getX(), to.getX()); x++){
            val cell = board.getCell(x, from.getY());
            if(!cell.isFree()) return false;
        }
        return true;
    }
}
