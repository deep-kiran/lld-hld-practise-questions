package chess.validators;

import lombok.val;
import chess.models.Board;
import chess.models.Cell;
import chess.models.ValidationProps;

public class DiagonalBeamMove implements IMoveValidator {
    @Override
    public Boolean validate(ValidationProps props) {
        val from = props.getFrom();
        val to = props.getTo();
        val board = Board.getBoard();
        val xDiff = Math.abs(from.getX() - to.getX());
        val yDiff = Math.abs(from.getY() - to.getY());
        val signs = compare(from, to);
        if (xDiff != yDiff) return false;
        for (int d = 1; d < xDiff; d++) {
            val x = from.getX() + signs[0] * d;
            val y = from.getY() + signs[1] * d;
            val cell = board.getCell(x, y);
            if (!cell.isFree()) return false;
        }
        return true;
    }

    private int[] compare(Cell from, Cell to) {
        val x1 = from.getX();
        val y1 = from.getY();
        val x2 = to.getX();
        val y2 = to.getY();
        var left = (x1 < x2) ? 1: -1;
        var right = y1 < y2 ? 1 : -1;
        return new int[]{left, right};
    }
}
