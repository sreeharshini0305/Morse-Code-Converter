import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Morse {

    public static void appendText(String x, List<String> result) {
        result.add(x);
    }

    public static void appendMorse(String morseCode, List<String> result) {
        result.add(morseCode);
    }

    public static String textToMorse(String text) {
        List<String> result = new ArrayList<>();
        Map<String, String> morseCodeDict = new HashMap<>();
        morseCodeDict.put("A", ".-");
        morseCodeDict.put("B", "-...");
        morseCodeDict.put("C", "-.-.");
        morseCodeDict.put("D", "-..");
        morseCodeDict.put("E", ".");
        morseCodeDict.put("F", "..-.");
        morseCodeDict.put("G", "--.");
        morseCodeDict.put("H", "....");
        morseCodeDict.put("I", "..");
        morseCodeDict.put("J", ".---");
        morseCodeDict.put("K", "-.-");
        morseCodeDict.put("L", ".-..");
        morseCodeDict.put("M", "--");
        morseCodeDict.put("N", "-.");
        morseCodeDict.put("O", "---");
        morseCodeDict.put("P", ".--.");
        morseCodeDict.put("Q", "--.-");
        morseCodeDict.put("R", ".-.");
        morseCodeDict.put("S", "...");
        morseCodeDict.put("T", "-");
        morseCodeDict.put("U", "..-");
        morseCodeDict.put("V", "...-");
        morseCodeDict.put("W", ".--");
        morseCodeDict.put("X", "-..-");
        morseCodeDict.put("Y", "-.--");
        morseCodeDict.put("Z", "--..");
        morseCodeDict.put(" ", "/");

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                String charUpper = Character.toString(Character.toUpperCase(ch));
                if (morseCodeDict.containsKey(charUpper)) {
                    appendMorse(morseCodeDict.get(charUpper), result);
                }
            }
        }
        return String.join(" ", result);
    }

    public static String morseToText(String morseCode) {
        List<String> result = new ArrayList<>();
        Map<String, String> morseCodeDict = new HashMap<>();
        morseCodeDict.put(".-", "A");
        morseCodeDict.put("-...", "B");
        morseCodeDict.put("-.-.", "C");
        morseCodeDict.put("-..", "D");
        morseCodeDict.put(".", "E");
        morseCodeDict.put("..-.", "F");
        morseCodeDict.put("--.", "G");
        morseCodeDict.put("....", "H");
        morseCodeDict.put("..", "I");
        morseCodeDict.put(".---", "J");
        morseCodeDict.put("-.-", "K");
        morseCodeDict.put(".-..", "L");
        morseCodeDict.put("--", "M");
        morseCodeDict.put("-.", "N");
        morseCodeDict.put("---", "O");
        morseCodeDict.put(".--.", "P");
        morseCodeDict.put("--.-", "Q");
        morseCodeDict.put(".-.", "R");
        morseCodeDict.put("...", "S");
        morseCodeDict.put("-", "T");
        morseCodeDict.put("..-", "U");
        morseCodeDict.put("...-", "V");
        morseCodeDict.put(".--", "W");
        morseCodeDict.put("-..-", "X");
        morseCodeDict.put("-.--", "Y");
        morseCodeDict.put("--..", "Z");
        morseCodeDict.put("-----", "0");
        morseCodeDict.put(".----", "1");
        morseCodeDict.put("..---", "2");
        morseCodeDict.put("...--", "3");
        morseCodeDict.put("....-", "4");
        morseCodeDict.put(".....", "5");
        morseCodeDict.put("-....", "6");
        morseCodeDict.put("--...", "7");
        morseCodeDict.put("---..", "8");
        morseCodeDict.put("----.", "9");
        morseCodeDict.put(".-.-.-", ".");
        morseCodeDict.put("--..--", ",");
        morseCodeDict.put("..--..", "?");
        morseCodeDict.put(".----.", "'");
        morseCodeDict.put("-.-.--", "!");
        morseCodeDict.put("-..-.", "/");
        morseCodeDict.put("-.--.", "(");
        morseCodeDict.put("-.--.-", ")");
        morseCodeDict.put(".-...", "&");
        morseCodeDict.put("---...", ":");
        morseCodeDict.put("-.-.-.", ";");
        morseCodeDict.put("-...-", "=");
        morseCodeDict.put(".-.-.", "+");
        morseCodeDict.put("-....-", "-");
        morseCodeDict.put("..--.-", "_");
        morseCodeDict.put(".-..-.", "\"");
        morseCodeDict.put("...-..-", "$");
        morseCodeDict.put(".--.-.", "@");

        String[] words = morseCode.split(" / ");
        for (String word : words) {
            String[] morseCodeList = word.trim().split(" ");
            for (String code : morseCodeList) {
                if (morseCodeDict.containsKey(code)) {
                    appendText(morseCodeDict.get(code), result);
                }
            }
            appendText(" ", result);
        }
        return String.join("", result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MorseGUI gui = new MorseGUI();
                gui.setVisible(true);
            }
        });
    }
}

class MorseGUI extends JFrame {

    private JTextField inputField;
    private JTextArea outputArea;

    public MorseGUI() {
        setTitle("Morse Code Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel inputLabel = new JLabel("Enter text or Morse code:");
        inputField = new JTextField();
        JButton convertButton = new JButton("Convert");
        outputArea = new JTextArea(5,20);
        outputArea.setEditable(false);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText().trim();

                if (!input.isEmpty() && (input.charAt(0) != '.' && input.charAt(0) != '-')) {
                    String morseCode = Morse.textToMorse(input);
                    outputArea.setText("Morse Code: " + morseCode);
                } else {
                    String text = Morse.morseToText(input);
                    outputArea.setText("Text: " + text);
                }
            }
        });

        add(inputLabel);
        add(inputField);
        add(convertButton);
        add(outputArea);
    }
}
