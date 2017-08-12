package questmaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author Tom
 */
public class Answer implements Serializable {

    static final long serialVersionUID = 42L;
    static int answerId;
    Decision decision;
    AnswerOutput output;
    String answer;
    String popis;
    int posX;
    int posY;
    int size;
    int id;

    public Answer(Decision decision, String answer, String popis, int posX, int posY, int size) {
        this.decision = decision;
        this.answer = answer;
        if ("".equals(answer)) {
            this.answer = "null";
        }
        this.popis = popis;
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.id = answerId;
        answerId++;
        output = new AnswerOutput(this, 10, Color.BLUE);
        output.updatePosition();
    }

    public void updatePosition() {
        this.posX = decision.posX;
        this.posY = decision.posY + 20 * decision.answers.indexOf(this) + 20;
        output.updatePosition();
    }

    public void updateSize() {
        this.size = decision.size;
        output.updatePosition();
    }

    public boolean MouseOverlaps(Point mousePosition) {
        return mousePosition.x > posX && mousePosition.x < posX + size
                && mousePosition.y > posY && mousePosition.y < posY + 20; // 20 -> Nahardkodene v paintbuffer() v QuestMainFrameDraw
    }

    public void delete() {
        output.target = null;
        decision = null;
    }
    
    public void draw (Graphics g) {
        g.setColor(Color.YELLOW);
        g.drawRect(this.posX, this.posY, this.size, 20);
        g.drawString(this.popis, this.posX, this.posY + 18);

        this.output.draw(g);
    }

    public String getAnswer() {
        return answer;
    }

    public AnswerOutput getOutput() {
        return output;
    }
    
    
    
    

}
