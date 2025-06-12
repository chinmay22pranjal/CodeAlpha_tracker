import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class Chatbot extends JFrame {

    public JTextArea chatArea;
    public JTextField inputField;
    public Map<String, String> responses;

    public Chatbot() {
        responses = new HashMap<>();
        responses.put("hello", "Hi! How can I assist you today?");
        responses.put("how are you", "I'm doing great, thanks for asking!");
        responses.put("what is your purpose", "I'm here to help answer any questions you have.");
        responses.put("exit", "Goodbye! It was nice chatting with you.");

        setLayout(new BorderLayout());

        chatArea = new JTextArea(20, 30);
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        inputField = new JTextField();
        add(inputField, BorderLayout.SOUTH);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        add(sendButton, BorderLayout.EAST);

        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputField.getText().toLowerCase();
            chatArea.append("You: " + inputField.getText() + "\n");

            if (responses.containsKey(input)) {
                chatArea.append("Bot: " + responses.get(input) + "\n");
            } else {
                chatArea.append("Bot: Sorry, I didn't understand that.\n");
            }

            inputField.setText("");

            if (input.equals("exit")) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new Chatbot();
    }
}