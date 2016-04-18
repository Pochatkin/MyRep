package Util;
import java.util.*;

/**
 * Created by Sergey on 18.03.2016.
 */

public class Game {

    private final Person person1;       //Игрок 1
    private final Person person2;       //Игрок 2

    private final Color colorPerson1;   //Цвет фигур
    private final Color colorPerson2;

    private boolean castlingPerson1;    //Пользовался игрок ли рокировкой?
    private boolean castlingPerson2;

    // "Состояния" игры
    //private Color checkmate;  // Мат
    //private Color check;      // Шах
    //private Color stalemate;  // Пат

    // Результат игры
    private boolean isGameOver;
    private boolean win;                //Победа одной из сторон
    private boolean draw;               //Ничья

    private Person winner;              //Победитель
    private Person loser;               //На всякий случай

    private ChessPiece[] board;         //Доска
    private Deque<Move> history;
//
//    private Set<Cell> whiteFigure;
//    private Set<Cell> blackFigure;
//    private Cell coordKingWhite = Cell.E1;
//    private Cell coordKingBlack = Cell.E8;


    Game (Person person1, Person person2) {
        this.person1 = person1;
        this.person2 = person2;

        //whiteFigure =  new LinkedHashSet<Cell>();
        //blackFigure =  new LinkedHashSet<Cell>();

        colorPerson1 = Color.WHITE;
        colorPerson2 = Color.BLACK;

        history = new ArrayDeque<Move>();

        initializeBoard();
    }
    //Game (Deque<Move> history) {} // TODO: restore game

    public boolean doIt (Move move){ /* тут будет Response, а может быть только exception */
        switch (move.getDecision()) {
            case STEP:
                checkMove(move); //Чекнули ход.

                Color myColor =  board[move.getFrom().ordinal()].getColor();
                Color enColor = myColor.equals(Color.BLACK) ? Color.WHITE : Color.BLACK;

                board = step(board.clone(), move); //TODO

                if (checkStalemate(board).equals(enColor)){
                    isGameOver = true;
                }
                if (checkCheckmate(board).equals(enColor)){
                    isGameOver = true;
                }

                history.add(move);
                break;

            case GIVE_UP:
                winner = person1.equals(move.getPerson()) ? person2 : person1;
                isGameOver = true;
                history.add(move);
                break;

            default:
                break;
        }
        return true;
    }
    public Move getLastMove () {
        return history.getLast();
    }

    //Эта штука кидает исключения
    private boolean checkMove (Move move){

        ChessPiece figure = board[move.getFrom().ordinal()];
        Color colorFigure = figure.getColor();

        if (figure.getColor() != getColorOfPerson(move.getPerson())) {
            throw new RuntimeException("It's not your chess");
        }

        if (!getAvailableMove(move.getFrom()).contains(move.getTo())){
            throw new RuntimeException("Incorrect move");
        }

        if (isGameOver) {
            throw new RuntimeException("The game is over");
        } else if (checkCheck(step(board.clone(), move)).equals(colorFigure)){ //TODO: .clone() ?
            throw new RuntimeException("Your king is under attack!");
        }

        return true;
    }


    private boolean isSameColor (ChessPiece[] board, Cell cell1, Cell cell2) {
        return board[cell1.ordinal()].getColor() == board[cell2.ordinal()].getColor();
    }
    private boolean isBLACK(ChessPiece[] board, Cell cell) {
        return board[cell.ordinal()].getColor() == Color.BLACK;
    }
    private boolean isWHITE(ChessPiece[] board, Cell cell) {
        return board[cell.ordinal()].getColor() == Color.WHITE;
    }
    private boolean isNone(ChessPiece[] board, Cell cell) {
        return board[cell.ordinal()].getColor() == Color.None;
    }
    private Set<Cell> stepWhile(ChessPiece[] board, Cell cell, int x, int y){
        Color color = board[cell.ordinal()].getColor();
        Set<Cell> cells = new LinkedHashSet<Cell>();

        try {
            while(board[cell.go(x,y).ordinal()] == ChessPiece.n){ //Шагаем, пока на пути пусто. И добавляем в cells
                cells.add(cell.go(x,y));
                cell = cell.go(x,y);
            }
            Color color2 = board[cell.go(x,y).ordinal()].getColor();
            if (color != color2){ //None не может быть, поэтому тут будет фигура противоположного цвета
                cells.add(cell.go(x,y));
            }
        } catch (NullPointerException npe){} //Когда выпали с доски

        return cells;
    }
    private Set<Cell> getAvailableKingMove (ChessPiece[] board, Cell cell) {
        Set<Cell> avaliableMove =  new LinkedHashSet<Cell>();
        Set<Cell> cells = new LinkedHashSet<Cell>();

        cells.add(cell.go(1,1));
        cells.add(cell.go(1,0));
        cells.add(cell.go(1,-1));

        cells.add(cell.go(0,1));
        cells.add(cell.go(0,-1));

        cells.add(cell.go(-1,1));
        cells.add(cell.go(-1,0));
        cells.add(cell.go(-1,-1));

        for (Cell c : cells){
            if (c != null && !isSameColor(board, c, cell)){
                avaliableMove.add(c);
            }
        }
        return avaliableMove;
    }
    private Set<Cell> getAvailableQueenMove (ChessPiece[] board, Cell cell) {
        Set<Cell> avaliableMove = new LinkedHashSet<Cell>();
        avaliableMove.addAll(getAvailableBishopMove(board, cell));
        avaliableMove.addAll(getAvailableRookMove(board, cell));
        return avaliableMove;
    }
    private Set<Cell> getAvailableRookMove (ChessPiece[] board, Cell cell) {
        Set<Cell> avaliableMove = new LinkedHashSet<Cell>();

        avaliableMove.addAll(stepWhile(board, cell, 1, 0));
        avaliableMove.addAll(stepWhile(board, cell, 0, 1));
        avaliableMove.addAll(stepWhile(board, cell, 0,-1));
        avaliableMove.addAll(stepWhile(board, cell,-1, 0));

        return avaliableMove;
    }
    private Set<Cell> getAvailableKnightMove (ChessPiece[] board, Cell cell) {
        Set<Cell> avaliableMove =  new LinkedHashSet<Cell>();
        Set<Cell> cells = new LinkedHashSet<Cell>();

        cells.add(cell.go(2,1));
        cells.add(cell.go(2,-1));

        cells.add(cell.go(-2,1));
        cells.add(cell.go(-2,-1));

        cells.add(cell.go(1,2));
        cells.add(cell.go(-1,2));

        cells.add(cell.go(1,-2));
        cells.add(cell.go(-1,-2));

        for (Cell c : cells){
            if (c != null && !isSameColor(board, c, cell)){
                avaliableMove.add(c);
            }
        }
        return avaliableMove;
    }
    private Set<Cell> getAvailableBishopMove (ChessPiece[] board, Cell cell) {
        Set<Cell> avaliableMove = new LinkedHashSet<Cell>();

        avaliableMove.addAll(stepWhile(board, cell, 1, 1));
        avaliableMove.addAll(stepWhile(board, cell, 1,-1));
        avaliableMove.addAll(stepWhile(board, cell,-1, 1));
        avaliableMove.addAll(stepWhile(board, cell,-1,-1));

        return avaliableMove;
    }
    private Set<Cell> getAvailablePawnMove (ChessPiece[] board, Cell cell) {
        Set<Cell> avaliableMove =  new LinkedHashSet<Cell>();

        Row fromRow = cell.getRow();
        Color color = board[cell.ordinal()].getColor();

        switch (color){
            case BLACK:
                try {
                    if (isNone(board, cell.go(0,-1))){
                        avaliableMove.add(cell.go(0,-1));
                        if (fromRow.equals(Row.r7) && isNone(board, cell.go(0,-1).go(0,-1))){
                            avaliableMove.add(cell.go(0,-1).go(0,-1));
                        }
                    }


                    if (isWHITE(board, cell.go(1,-1))) avaliableMove.add(cell.go(1,-1));
                    if (isWHITE(board, cell.go(-1,-1))) avaliableMove.add(cell.go(-1,-1));
                } catch (NullPointerException e) {}


                break;
            case WHITE:
                try {
                    if (isNone(board, cell.go(0,1))){
                        avaliableMove.add(cell.go(0,1));
                        if (fromRow.equals(Row.r2) && isNone(board, cell.go(0,1).go(0,1))){
                            avaliableMove.add(cell.go(0,1).go(0,1));
                        }
                    }

                    if (isBLACK(board, cell.go(1,1))) avaliableMove.add(cell.go(1,1));
                    if (isBLACK(board, cell.go(-1,1))) avaliableMove.add(cell.go(-1,1));
                } catch (NullPointerException e) {}

                break;
            case None:
                break;
        }

        return avaliableMove;
    }

    private Set<Cell> getAvailableMove (ChessPiece[] board, Cell cell) {
        ChessPiece figure = board[cell.ordinal()];
        switch (figure) {
            case KB: case KW:   return getAvailableKingMove(board, cell);
            case QB: case QW:   return getAvailableQueenMove(board, cell);
            case RB: case RW:   return getAvailableRookMove(board, cell);
            case NB: case NW:   return getAvailableKnightMove(board, cell);
            case BB: case BW:   return getAvailableBishopMove(board, cell);
            case pB: case pW:   return getAvailablePawnMove(board, cell);
            case n: break;      //Тут не бывает
        }

        throw new RuntimeException("Code 0001");
    }
    private Set<Cell> getAvailableMove (Cell cell){
        return getAvailableMove(this.board, cell);
    }

//    private Person getPersonOfColor (Color color){
//        return colorPerson1 == color ? person1 : person2;
//    }
    private Color getColorOfPerson (Person person){
        return person == person1 ? colorPerson1 : colorPerson2;
    }

    private Color checkCheckmate (ChessPiece[] board) {
        Color color = checkCheck(board);
        if (color.equals(Color.None)) return Color.None;

        Set<Move> aMoves= avaliableMoves(board, color);

        return aMoves.stream().map(mov -> checkCheck(step(board.clone(), mov))).allMatch(col -> col == color) ? color : Color.None;
    }
    private Color checkCheck (ChessPiece[] board) {
        Set<Cell> attackWhile =  new LinkedHashSet<Cell>();
        Set<Cell> attackfBlack =  new LinkedHashSet<Cell>();

        Set<Cell> whiteFigure = findFigure(board, Color.WHITE);
        Set<Cell> blackFigure = findFigure(board, Color.BLACK);

        Cell coordKingWhite = (Cell) whiteFigure.stream().filter((m) -> board[m.ordinal()].equals(ChessPiece.KW)).toArray()[0];
        Cell coordKingBlack = (Cell) blackFigure.stream().filter((m) -> board[m.ordinal()].equals(ChessPiece.KB)).toArray()[0];

        for(Cell cell : whiteFigure){
            attackWhile.addAll(getAvailableMove(board, cell));
        }
        for(Cell cell : blackFigure){
            attackfBlack.addAll(getAvailableMove(board, cell));
        }

        if(attackWhile.contains(coordKingBlack)){
            return Color.BLACK;
        } else if(attackfBlack.contains(coordKingWhite)){
            return Color.WHITE;
        } else {
            return Color.None;
        }
    }
    private Color checkStalemate (ChessPiece[] board) {
        if (!checkCheck(board).equals(Color.None)) return Color.None;

        Set<Move> aWhiteMoves= avaliableMoves(board, Color.WHITE);
        Set<Move> aBlackMoves= avaliableMoves(board, Color.BLACK);

        Color cW = aWhiteMoves.stream().map(mov -> checkCheck(step(board.clone(), mov))).allMatch(col -> col == Color.WHITE) ? Color.WHITE : Color.None;
        Color cB = aBlackMoves.stream().map(mov -> checkCheck(step(board.clone(), mov))).allMatch(col -> col == Color.BLACK) ? Color.BLACK : Color.None;
        return cW.equals(Color.None) ? cW : cB;
    }

    private Color getCellColor (ChessPiece[] board, Cell cell) {
        return board[cell.ordinal()].getColor();
    }
    private Set<Cell> findFigure(ChessPiece[] board, Color color) {
        Set<Cell> figure = new LinkedHashSet<Cell>();

        for (Cell cell : Cell.values()){
            if(getCellColor(board,cell) == color){
                figure.add(cell);
            }
        }
        return figure;
    }

    private ChessPiece[] step(ChessPiece[] board, Move move) {
        board[move.getTo().ordinal()] = board[move.getFrom().ordinal()];
        board[move.getFrom().ordinal()] = ChessPiece.n;
        return board;
    }

    private Set<Move> avaliableMoves(ChessPiece[] board, Color color){
        Set<Cell> myFigure = findFigure(board, color);
        Set<Move> moves = new LinkedHashSet<Move>();

        for(Cell from : myFigure){
            Set<Cell> tos = getAvailableMove(board, from);
            for (Cell to : tos){
                moves.add(Move.goFromTo(person1,from,to));
            }
        }
        return moves;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                builder.append("\t" + board[i*8 + j].toString());
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    //TODO: что с этим сделать?
    private void initializeBoard (){
        board = new ChessPiece[64];
        for(int i = Cell.A8.ordinal(); i < Cell.H1.ordinal(); i++){
            board[i] = ChessPiece.n;
        }

        //Sorry for this -_-
        board[Cell.A1.ordinal()] = ChessPiece.RW;
        board[Cell.B1.ordinal()] = ChessPiece.NW;
        board[Cell.C1.ordinal()] = ChessPiece.BW;
        board[Cell.D1.ordinal()] = ChessPiece.QW;
        board[Cell.E1.ordinal()] = ChessPiece.KW;
        board[Cell.F1.ordinal()] = ChessPiece.BW;
        board[Cell.G1.ordinal()] = ChessPiece.NW;
        board[Cell.H1.ordinal()] = ChessPiece.RW;

        board[Cell.A8.ordinal()] = ChessPiece.RB;
        board[Cell.B8.ordinal()] = ChessPiece.NB;
        board[Cell.C8.ordinal()] = ChessPiece.BB;
        board[Cell.D8.ordinal()] = ChessPiece.QB;
        board[Cell.E8.ordinal()] = ChessPiece.KB;
        board[Cell.F8.ordinal()] = ChessPiece.BB;
        board[Cell.G8.ordinal()] = ChessPiece.NB;
        board[Cell.H8.ordinal()] = ChessPiece.RB;

        board[Cell.A2.ordinal()] = ChessPiece.pW;
        board[Cell.B2.ordinal()] = ChessPiece.pW;
        board[Cell.C2.ordinal()] = ChessPiece.pW;
        board[Cell.D2.ordinal()] = ChessPiece.pW;
        board[Cell.E2.ordinal()] = ChessPiece.pW;
        board[Cell.F2.ordinal()] = ChessPiece.pW;
        board[Cell.G2.ordinal()] = ChessPiece.pW;
        board[Cell.H2.ordinal()] = ChessPiece.pW;

        board[Cell.A7.ordinal()] = ChessPiece.pB;
        board[Cell.B7.ordinal()] = ChessPiece.pB;
        board[Cell.C7.ordinal()] = ChessPiece.pB;
        board[Cell.D7.ordinal()] = ChessPiece.pB;
        board[Cell.E7.ordinal()] = ChessPiece.pB;
        board[Cell.F7.ordinal()] = ChessPiece.pB;
        board[Cell.G7.ordinal()] = ChessPiece.pB;
        board[Cell.H7.ordinal()] = ChessPiece.pB;
    }
    public Person getWinner (){
        return winner;
    }
}
