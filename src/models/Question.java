package models;

/**
 *
 * @author hung
 */
public class Question {
    private String question;
    private String A,B;
    private String answer;

    public Question() {
    }

    public Question(String question, String A, String B, String answer) {
        this.question = question;
        this.A = A;
        this.B = B;
        this.answer = answer;
    }
    
    @Override
    public String toString(){
        return question+"-@-"+A+"-@-"+B+"-@-"+answer;
    }
    
}
