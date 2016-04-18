package Util;

/**
 * Created by Sergey on 02.04.2016.
 */
public enum ChessPiece {
    KB,  // king, Король
    QB,  // queen, Ферзь
    RB,  // rook, Ладья
    NB,  // kNight, Конь
    BB,  // bishop,Слон
    pB,  // pawn,Пешка

    KW,
    QW,
    RW,
    NW,
    BW,
    pW,

    n;   // Пусто

    public Color getColor(){
        if (this.name() == "n"){
            return Color.None;
        } else if (this.name().charAt(1) == 'W'){
            return Color.WHITE;
        } else if (this.name().charAt(1) == 'B') {
            return Color.BLACK;
        }
        throw new RuntimeException("In ChessPiece getColor()");
    }
    public int getCost(){
        switch (this){
            case KB: case KW: return 100000;
            case QB: case QW: return 9;
            case RB: case RW: return 5;
            case NB: case NW: return 3;
            case BB: case BW: return 3;
            case pB: case pW: return 1;
            default:
                return 0;
        }
    }

    @Override
    public String toString(){
        switch (this) {
            case RB: return "♜";
            case NB: return "♞";
            case BB: return "♝";
            case pB: return "♟";
            case RW: return "♖";
            case NW: return "♘";
            case BW: return "♗";
            case pW: return "♙";
            case n:  return "_";
            default: return this.name();
        }
    }
}