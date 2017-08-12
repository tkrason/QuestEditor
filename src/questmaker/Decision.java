package questmaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Tom
 */
public class Decision implements Serializable {

    static final long serialVersionUID = 42L;
    static int decisionId = 0;
    String question;
    String popis;
    LinkedList<Answer> answers;
    int id;
    int posX;
    int posY;
    int size;
    Color color;
    DecisionInput decisionInput;
    DecisionAddNewAnswerSign plusSign;

    public Decision(String question, String popis, int posX, int posY, Color c) {
        this.question = question;
        
        if ("".equals(question)) {
            this.question = "";
        }
        this.popis = popis;
        this.answers = new LinkedList<>();
        this.posX = posX;
        this.posY = posY;
        this.size = popis.length() * 13;
        this.color = c;
        this.id = decisionId;
        decisionInput = new DecisionInput(this, 10, Color.RED);
        decisionInput.updatePosition();
        plusSign = new DecisionAddNewAnswerSign(this);
        decisionId++;
    }

    public void addAnswer(String text) {
        Answer answer = new Answer(this, "", text, posX, posY + 20 * answers.size() + 20, size);
        this.answers.add(answer);
    }

    public boolean MouseOverlaps(Point mousePosition) {
        return mousePosition.x > posX && mousePosition.x < posX + size
                && mousePosition.y > posY && mousePosition.y < posY + 20; // 20 -> Nahardkodene v paintbuffer() v QuestMainFrameDraw
    }

    public void updatePosition(Point mouseCursor) {

        posX = mouseCursor.x - this.size / 2;
        posY = mouseCursor.y - 10;

        this.decisionInput.updatePosition();
        this.plusSign.updatePositon();

        for (int i = 0; i < answers.size(); i++) {
            answers.get(i).updatePosition();
        }
    }

    public void updateAfterNewAnswer() {

        for (int i = 0; i < answers.size(); i++) {
            answers.get(i).updatePosition();
        }
        this.decisionInput.updatePosition();
        this.plusSign.updatePositon();
    }

    public void update() {

        this.size = popis.length() * 13;
        for (int i = 0; i < answers.size(); i++) {
            answers.get(i).updateSize();
        }
        this.decisionInput.updatePosition();
        this.plusSign.updatePositon();

    }

    public void delete() {
        for (Answer an : answers) {
            an.delete();
        }

        plusSign.delete();

    }
    
    public void draw(Graphics g) {
        g.setColor(this.color);            
        g.drawRect(this.posX, this.posY, this.size, 20);
        g.drawString(this.popis, this.posX, this.posY + 18);
        this.plusSign.draw(g);

        this.decisionInput.draw(g);

        for (Answer an : this.answers) {
            an.draw(g);
        }
    }

    public String getQuestion() {
        return question;
    }

    public LinkedList<Answer> getAnswers() {
        return answers;
    }
    
    
    
    

}
