package chess.models;


import chess.enums.Color;
import chess.enums.PieceType;
import chess.factory.PieceFactory;
import chess.models.pieces.*;

import java.util.Objects;

public class Board {

    private final Integer size;
    private Cell[][] cells;
    private static Board board;

    private Board(Integer size){
        this.size = size;
        this.cells = new Cell[size][size];
        setBoard();
    }
    public static Board createBoard(Integer size){
        if(Objects.isNull(board)){
            board = new Board(size);
        }
        return board;
    }
    public static Board getBoard() {
        return board;
    }

    private void setBoard(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                cells[i][j] = new Cell(i, j);
            }
        }
        initializePieces();
    }
    private void initializePieces() {
        // Initialize pawns
        for (int i = 0; i < size; i++) {
            cells[1][i].setPiece( PieceFactory.getPiece(PieceType.PAWN, Color.WHITE));
            cells[size - 2][i].setPiece(PieceFactory.getPiece(PieceType.PAWN, Color.BLACK));
        }

        // Initialize rooks
        cells[0][0].setPiece(PieceFactory.getPiece(PieceType.ROOK, Color.WHITE));
        cells[0][size - 1].setPiece(PieceFactory.getPiece(PieceType.ROOK, Color.WHITE));
        cells[size - 1][0].setPiece(PieceFactory.getPiece(PieceType.ROOK, Color.BLACK));
        cells[size - 1][size - 1].setPiece(PieceFactory.getPiece(PieceType.ROOK, Color.BLACK));

        // Initialize knights
        cells[0][1].setPiece(PieceFactory.getPiece(PieceType.KNIGHT, Color.WHITE));
        cells[0][size - 2].setPiece(PieceFactory.getPiece(PieceType.KNIGHT, Color.WHITE));
        cells[size - 1][1].setPiece(PieceFactory.getPiece(PieceType.KNIGHT, Color.BLACK));
        cells[size - 1][size - 2].setPiece(PieceFactory.getPiece(PieceType.KNIGHT, Color.BLACK));

        // Initialize bishops
        cells[0][2].setPiece(PieceFactory.getPiece(PieceType.BISHOP, Color.WHITE));
        cells[0][size - 3].setPiece(PieceFactory.getPiece(PieceType.BISHOP, Color.WHITE));
        cells[size - 1][2].setPiece(PieceFactory.getPiece(PieceType.BISHOP, Color.BLACK));
        cells[size - 1][size - 3].setPiece(PieceFactory.getPiece(PieceType.BISHOP, Color.BLACK));

        // Initialize queens
        cells[0][3].setPiece(PieceFactory.getPiece(PieceType.QUEEN, Color.WHITE));
        cells[size - 1][3].setPiece(PieceFactory.getPiece(PieceType.QUEEN, Color.BLACK));

        // Initialize kings
        cells[0][4].setPiece(PieceFactory.getPiece(PieceType.KING, Color.WHITE));
        cells[size - 1][4].setPiece(PieceFactory.getPiece(PieceType.KING, Color.BLACK));
    }

    public Cell getCell(Integer x, Integer y){
        return x>=0 && x<size && y>=0 && y<size ? this.cells[x][y]: null;
    }

}
