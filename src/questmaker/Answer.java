package questmaker;

/**
 *
 * @author Tom
 */
public class Answer {
    
    
    static int answerId;
    Decision decision;
    AnswerOutput output;
    String answer;
    String popis;
    int posX;
    int posY;
    int size;
    int id;

    public Answer(Decision decision, String answer,String popis,int posX, int posY, int size) {
        this.decision = decision;
        this.answer = answer;
        this.popis = popis;
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.id = answerId;
        answerId++;
        output = new AnswerOutput(this);
    }
    
    public void updatePosition() {
        this.posX = decision.posX;
        this.posY = decision.posY + 20 * decision.answers.indexOf(this) + 20;
        output.updatePosition();
    }
        
    
    
    
}