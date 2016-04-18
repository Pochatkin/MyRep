package Util;

/**
 * Created by Sergey on 18.03.2016.
 */
public class Move {
    private Person person;
    private Decision decision;
    private Cell from;
    private Cell to;

    private Move () {}
    private Move (Person person, Decision decision, Cell from, Cell to) {
        this.person = person;
        this.decision = decision;
        this.from = from;
        this.to = to;
    }

    //Все решения, которые игрок может принять за всё время игры
    static Move goFromTo (Person person, Cell from, Cell to){
        return new Move(person, Decision.STEP, from, to);
    }
    static Move giveUp (Person person){
        return new Move(person, Decision.GIVE_UP, null, null);
    }
    //TODO: Castling    //Рокировка
    //TODO: En passant  //Взятие на проходе
    //TODO: Promotion   //Превращение пешки
    //TODO:             //Игрок просрочил время

    //getters
    public Person getPerson(){
        return this.person;
    }
    public Decision getDecision (){
        return this.decision;
    }
    public Cell getFrom (){
        return this.from;
    }
    public Cell getTo () {
        return this.to;
    }
}
