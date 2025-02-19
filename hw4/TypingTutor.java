import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TypingTutor extends JFrame implements KeyListener {
    private JTextArea textArea;
    private JButton[][] buttons;
    private Color originalButtonColor;
    private String[] keys = {
        "~1234567890-+=Backspace=",
        "=Tab=QWERTYUIOP[]\\",
        "=Caps=ASDFGHJKL;\"=Enter=",
        "=Shift=ZXCVBNM,.?=Shift=",
    };
    int count = 0;
    private int[][] specialkeys = new int[6][2];
    //Backspace[0] Tab[1] Caps[2] Enter[3] Shift[4][5]

    public TypingTutor() {
        setTitle("Typing Tutor");
        setSize(800, 530);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea(20, 10);
        textArea.addKeyListener(this);

        add(textArea, BorderLayout.NORTH);
        
        
        buttons = new JButton[4][];
        for(int i = 0; i < 4; i++){
            buttons[i] = new JButton[keys[i].length()];
        }

        JPanel all_keyboard = new JPanel(new GridLayout(4, 1));
        all_keyboard.setSize(1000, 300);
        JPanel keyboard[] = new JPanel[4];
        for(int i = 0; i < 4; i++){
            keyboard[i] = new JPanel(new FlowLayout());
            keyboard[i].setSize(1000, 130);
            // keyboard[i].setLayout(new FlowLayout());
            for(int j = 0; j < keys[i].length(); j++){
                if(Character.toString(keys[i].charAt(j)).equals("=")){
                    String temp = "";
                    int check = 1;
                    while(!Character.toString(keys[i].charAt(j+check)).equals("=")){
                        temp += Character.toString(keys[i].charAt(j+check));
                        check++;
                    }
                    buttons[i][j] = new JButton(temp);
                    buttons[i][j].setSize(240, 120);
                    keyboard[i].add(buttons[i][j]);
                    specialkeys[count][0] = i;
                    specialkeys[count][1] = j;
                    count++;
                    j += check;
                }
                else{
                    buttons[i][j] = new JButton(Character.toString(keys[i].charAt(j)));
                    buttons[i][j].setSize(120, 120);
                    keyboard[i].add(buttons[i][j]);
                }
            }
            all_keyboard.add(keyboard[i]);
        }
        
        // for (int i = 0; i < buttons.length; i++) {
        //     buttons[i] = new JButton(Character.toString((char) (i + 'A')));
        //     keyboard.add(buttons[i]);
        // }
        add(all_keyboard, BorderLayout.CENTER);

        originalButtonColor = buttons[0][0].getBackground();

        setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){//0
            buttons[specialkeys[0][0]][specialkeys[0][1]].setBackground(Color.YELLOW);
        }
        else if(e.getKeyCode() == KeyEvent.VK_TAB){//1
            buttons[specialkeys[1][0]][specialkeys[1][1]].setBackground(Color.YELLOW);
        }
        else if(e.getKeyCode() == KeyEvent.VK_CAPS_LOCK){//2
            buttons[specialkeys[2][0]][specialkeys[2][1]].setBackground(Color.YELLOW);
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER){//3
            buttons[specialkeys[3][0]][specialkeys[3][1]].setBackground(Color.YELLOW);
        }
        else if(e.getKeyCode() == KeyEvent.VK_SHIFT){//4
            buttons[specialkeys[4][0]][specialkeys[4][1]].setBackground(Color.YELLOW);
            buttons[specialkeys[5][0]][specialkeys[5][1]].setBackground(Color.YELLOW);
        }
        else{
            char c = Character.toUpperCase(e.getKeyChar());
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < keys[i].length(); j++){
                    if(keys[i].charAt(j) == c){
                        buttons[i][j].setBackground(Color.YELLOW);
                    }
                }
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){//0
            buttons[specialkeys[0][0]][specialkeys[0][1]].setBackground(originalButtonColor);
        }
        else if(e.getKeyCode() == KeyEvent.VK_TAB){//1
            buttons[specialkeys[1][0]][specialkeys[1][1]].setBackground(originalButtonColor);
        }
        else if(e.getKeyCode() == KeyEvent.VK_CAPS_LOCK){//2
            buttons[specialkeys[2][0]][specialkeys[2][1]].setBackground(originalButtonColor);
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER){//3
            buttons[specialkeys[3][0]][specialkeys[3][1]].setBackground(originalButtonColor);
        }
        else if(e.getKeyCode() == KeyEvent.VK_SHIFT){//4
            buttons[specialkeys[4][0]][specialkeys[4][1]].setBackground(originalButtonColor);
            buttons[specialkeys[5][0]][specialkeys[5][1]].setBackground(originalButtonColor);
        }
        else{
            char c = Character.toUpperCase(e.getKeyChar());
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < keys[i].length(); j++){
                    if(keys[i].charAt(j) == c){
                        buttons[i][j].setBackground(originalButtonColor);
                    }
                }
            }
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing for this implementation
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TypingTutor());
    }
}