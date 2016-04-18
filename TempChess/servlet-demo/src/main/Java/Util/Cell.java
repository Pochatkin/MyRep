package Util;

import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Collection;

/**
 * Created by Sergey on 18.03.2016.
 */

public enum Cell {
    A8,B8,C8,D8,E8,F8,G8,H8,
    A7,B7,C7,D7,E7,F7,G7,H7,
    A6,B6,C6,D6,E6,F6,G6,H6,
    A5,B5,C5,D5,E5,F5,G5,H5,
    A4,B4,C4,D4,E4,F4,G4,H4,
    A3,B3,C3,D3,E3,F3,G3,H3,
    A2,B2,C2,D2,E2,F2,G2,H2,
    A1,B1,C1,D1,E1,F1,G1,H1;

    public static Cell getCell(Column c, Row r){
        String name = "" + c.name().charAt(1) + r.name().charAt(1);
        return Cell.valueOf(name);
    }
    public Column getColumn(){
        return Column.valueOf("c" + this.name().charAt(0));
    }
    public Row getRow(){
        return Row.valueOf("r" + this.name().charAt(1));
    }
    @Nullable
    public Cell go (int x, int y){
        int xx = this.getColumn().ordinal();
        int yy = this.getRow().ordinal();

        try {
            return Cell.getCell(Column.values()[xx + x], Row.values()[yy + y]);
        }
        catch (Exception e){
            return null;
        }
    }
}