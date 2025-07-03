import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ChatBotApp extends JFrame implements ActionListener {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private Map<String, String> knowledgeBase;

    public ChatBotApp() {
        setTitle("AI ChatBot");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(this);
        inputField.addActionListener(this);

        initializeKnowledgeBase();

        setVisible(true);
        printBot("Hi there! I'm your AI chatbot. Ask me anything.");
    }

    private void initializeKnowledgeBase() {
        knowledgeBase = new HashMap<>();
        knowledgeBase.put("hello", "Hello! How can I assist you?");
        knowledgeBase.put("hi", "Hi there! Feel free to ask me something.");
        knowledgeBase.put("how are you", "I'm just a bunch of Java code, but I'm here to help!");
        knowledgeBase.put("what is ai", "Artificial Intelligence is the simulation of human intelligence in machines.");
        knowledgeBase.put("bye", "Goodbye! Have a great day!");
        knowledgeBase.put("help", "I can answer simple questions about AI, tech, or just chat!");
        // Add more FAQ-style training data here
    }

    private String getResponse(String input) {
        String lowerInput = input.toLowerCase().trim();

        for (String key : knowledgeBase.keySet()) {
            if (lowerInput.contains(key)) {
                return knowledgeBase.get(key);
            }
        }

        return "I'm not sure how to respond to that. Try asking something else!";
    }

    private void printBot(String message) {
        chatArea.append("Bot: " + message + "\n");
    }

    private void printUser(String message) {
        chatArea.append("You: " + message + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userText = inputField.getText().trim();
        if (!userText.isEmpty()) {
            printUser(userText);
            String response = getResponse(userText);
            printBot(response);
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatBotApp::new);
    }
}