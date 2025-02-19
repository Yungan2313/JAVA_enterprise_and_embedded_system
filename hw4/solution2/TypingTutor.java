import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TypingTutor extends JFrame implements KeyListener {

    private final int WIDTH = 810, LENGTH = 530;
    private JTextArea typeBox;
    private java.util.List<JButton> buttons;
    private String qwerty, qwertyfind;

    private int padding = 130;

    public TypingTutor() {
        this.setLayout(null);
        setVisible(true);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        qwerty = "~1234567890-+BackspaceTabqwertyuiop[]\\Capsasdfghjkl;\"EnterShiftzxcvbnm,.?^<v> ";
        qwertyfind = "`1234567890-=BTqwertyuiop[]\\Casdfghjkl;\'ESzxcvbnm,./^<V> ";
        typeBox = new JTextArea("> ");
        buttons = new ArrayList<JButton>();

        typeBox.setSize(700, 200);
        typeBox.setLocation(150 - padding, 10);
        typeBox.setFocusable(false);
        typeBox.setLineWrap(true);
        typeBox.setWrapStyleWord(true);

        Container pane = getContentPane();
        pane.add(typeBox);
        addButtons();
        addSpacebar();

        setVisible(true);
        setSize(WIDTH, LENGTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void addButtons() {
        int flag = 0;
        for (int i = 0; i < qwerty.length() - 1; ++i) {
            // 由於 "Backspace" 為多個字符組成，需要特別處理
            if (qwerty.substring(i, Math.min(i + 9, qwerty.length())).equals("Backspace")) {
                buttons.add(new JButton("Backspace"));
                i += 8; // 跳過 "Backspace" 中的其他字符
                flag = 1;
            } else if (qwerty.substring(i, Math.min(i + 3, qwerty.length())).equals("Tab")) {
                buttons.add(new JButton("Tab"));
                i += 2; // 跳過 "Backspace" 中的其他字符
                flag = 2;
            } else if (qwerty.substring(i, Math.min(i + 4, qwerty.length())).equals("Caps")) {
                buttons.add(new JButton("Caps"));
                i += 3; // 跳過 "Backspace" 中的其他字符
                flag = 2;
            } else if (qwerty.substring(i, Math.min(i + 5, qwerty.length())).equals("Enter")) {
                buttons.add(new JButton("Enter"));
                i += 4; // 跳過 "Backspace" 中的其他字符
                flag = 1;
            } else if (qwerty.substring(i, Math.min(i + 5, qwerty.length())).equals("Shift")) {
                buttons.add(new JButton("Shift"));
                i += 4; // 跳過 "Backspace" 中的其他字符
                flag = 1;
            } else {
                buttons.add(new JButton(Character.toString(qwerty.charAt(i))));
            }
            buttons.get(buttons.size() - 1).setSize(50, 50);
            if (flag == 1)
                buttons.get(buttons.size() - 1).setSize(100, 50);
            if (flag == 2)
                buttons.get(buttons.size() - 1).setSize(75, 50);
            flag = 0;
            setPosition(buttons.size() - 1);
            this.getContentPane().add(buttons.get(buttons.size() - 1));
            buttons.get(buttons.size() - 1).setFocusable(false);
        }
    }

    public void addSpacebar() {
        JButton spacebar = new JButton();
        buttons.add(spacebar);
        spacebar.setSize(300, 50);
        spacebar.setLocation(350 - padding, 430);
        this.getContentPane().add(spacebar);
    }

    public void setPosition(int i) {
        if (i < 14) { // 新增的 14 個鍵
            buttons.get(i).setLocation(150 + 50 * i - padding, 230);
        } else if (i < 28) { // 原本的第一行
            if (i == 14) {
                buttons.get(i).setLocation(150 + 50 * (i - 14) - padding, 280);
            } else
                buttons.get(i).setLocation(175 + 50 * (i - 14) - padding, 280);
        } else if (i < 41) { // 原本的第二行
            if (i == 28) {
                buttons.get(i).setLocation(150 + 50 * (i - 28) - padding, 330);
            } else
                buttons.get(i).setLocation(175 + 50 * (i - 28) - padding, 330);
        } else if (i < 53) { // 原本的第三行
            if (i == 41) {
                buttons.get(i).setLocation(150 + 50 * (i - 41) - padding, 380);
            } else if (i == 52) {
                buttons.get(i).setLocation(225 + 50 * (i - 41) - padding, 380);
            } else
                buttons.get(i).setLocation(200 + 50 * (i - 41) - padding, 380);
        } else {
            buttons.get(i).setLocation(725 + 50 * (i - 53) - padding, 430);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i;
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            i = qwertyfind.indexOf('B');
        } else if (e.getKeyCode() == KeyEvent.VK_TAB) {
            i = qwertyfind.indexOf('T');
        } else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            i = qwertyfind.indexOf('S');
        } else if (e.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
            i = qwertyfind.indexOf('C');
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            i = qwertyfind.indexOf('E');
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            i = qwertyfind.indexOf('^');
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            i = qwertyfind.indexOf('V');
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            i = qwertyfind.indexOf('<');
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            i = qwertyfind.indexOf('>');
        } else {
            i = qwertyfind.indexOf(e.getKeyChar());
        }
        if (i != -1) {
            buttons.get(i).setBackground(Color.green);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int i;
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            i = qwertyfind.indexOf('B');
        } else if (e.getKeyCode() == KeyEvent.VK_TAB) {
            i = qwertyfind.indexOf('T');
        } else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            i = qwertyfind.indexOf('S');
        } else if (e.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
            i = qwertyfind.indexOf('C');
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            i = qwertyfind.indexOf('E');
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            i = qwertyfind.indexOf('^');
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            i = qwertyfind.indexOf('V');
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            i = qwertyfind.indexOf('<');
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            i = qwertyfind.indexOf('>');
        } else {
            i = qwertyfind.indexOf(e.getKeyChar());
        }

        if (i != -1) {
            buttons.get(i).setBackground(null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String typedText = typeBox.getText() + e.getKeyChar();
        typeBox.setText(typedText);
    }

    public static void main(String[] args) {
        new TypingTutor();
    }
}