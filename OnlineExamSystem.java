import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class User {
    String username = "student";
    String password = "1234";
    String name = "John Doe";
}

class Question {
    String question;
    String[] options;
    int correctAnswer;

    Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class OnlineExamSystem {

    static User user = new User();
    static JFrame frame;
    static ArrayList<Question> questions = new ArrayList<>();
    static int currentQuestion;
    static int score;
    static int timeLeft;
    static Timer timer;

    static JLabel questionLabel, timerLabel;
    static JRadioButton opt1, opt2, opt3, opt4;
    static ButtonGroup bg;

    public static void main(String[] args) {
        initializeQuestions();
        showLogin();
    }

    // ---------------- LOGIN SCREEN ----------------

    static void showLogin() {
        frame = new JFrame("Online Examination - Login");
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel l1 = new JLabel("Username:");
        l1.setBounds(50, 60, 100, 30);

        JTextField tf1 = new JTextField();
        tf1.setBounds(150, 60, 150, 30);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(50, 110, 100, 30);

        JPasswordField pf = new JPasswordField();
        pf.setBounds(150, 110, 150, 30);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 170, 100, 30);

        loginBtn.addActionListener(e -> {
            String uname = tf1.getText();
            String pass = new String(pf.getPassword());

            if (uname.equals(user.username) && pass.equals(user.password)) {
                JOptionPane.showMessageDialog(frame, "Login Successful!");
                frame.dispose();
                showDashboard();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Credentials!");
            }
        });

        frame.add(l1); frame.add(tf1);
        frame.add(l2); frame.add(pf);
        frame.add(loginBtn);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // ---------------- DASHBOARD ----------------

    static void showDashboard() {
        frame = new JFrame("Dashboard - Welcome " + user.name);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        JButton profileBtn = new JButton("Update Profile");
        JButton passBtn = new JButton("Update Password");
        JButton startBtn = new JButton("Start Exam");
        JButton logoutBtn = new JButton("Logout");

        profileBtn.addActionListener(e -> updateProfile());
        passBtn.addActionListener(e -> updatePassword());
        startBtn.addActionListener(e -> {
            frame.dispose();
            startExam();
        });
        logoutBtn.addActionListener(e -> {
            frame.dispose();
            showLogin();
        });

        frame.add(profileBtn);
        frame.add(passBtn);
        frame.add(startBtn);
        frame.add(logoutBtn);

        frame.setVisible(true);
    }

    // ---------------- PROFILE UPDATE ----------------

    static void updateProfile() {
        String newName = JOptionPane.showInputDialog("Enter New Name:");
        if (newName != null && !newName.isEmpty()) {
            user.name = newName;
            JOptionPane.showMessageDialog(frame, "Profile Updated Successfully!");
            frame.dispose();
            showDashboard();
        }
    }

    static void updatePassword() {
        String oldPass = JOptionPane.showInputDialog("Enter Old Password:");
        if (oldPass != null && oldPass.equals(user.password)) {
            String newPass = JOptionPane.showInputDialog("Enter New Password:");
            if (newPass != null && !newPass.isEmpty()) {
                user.password = newPass;
                JOptionPane.showMessageDialog(frame, "Password Updated!");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Incorrect Old Password!");
        }
    }

    // ---------------- EXAM SECTION ----------------

    static void startExam() {

        currentQuestion = 0;
        score = 0;
        timeLeft = 120; // 2 minutes

        frame = new JFrame("Online Examination - Exam");
        frame.setSize(600, 400);
        frame.setLayout(null);

        timerLabel = new JLabel("Time Left: 120");
        timerLabel.setBounds(450, 10, 150, 30);

        questionLabel = new JLabel();
        questionLabel.setBounds(50, 50, 500, 30);

        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt4 = new JRadioButton();

        opt1.setBounds(50, 100, 400, 30);
        opt2.setBounds(50, 130, 400, 30);
        opt3.setBounds(50, 160, 400, 30);
        opt4.setBounds(50, 190, 400, 30);

        bg = new ButtonGroup();
        bg.add(opt1); bg.add(opt2);
        bg.add(opt3); bg.add(opt4);

        JButton nextBtn = new JButton("Next");
        nextBtn.setBounds(250, 250, 100, 30);

        nextBtn.addActionListener(e -> nextQuestion());

        frame.add(timerLabel);
        frame.add(questionLabel);
        frame.add(opt1); frame.add(opt2);
        frame.add(opt3); frame.add(opt4);
        frame.add(nextBtn);

        loadQuestion();
        startTimer();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static void loadQuestion() {
        if (currentQuestion < questions.size()) {
            Question q = questions.get(currentQuestion);
            questionLabel.setText(q.question);
            opt1.setText(q.options[0]);
            opt2.setText(q.options[1]);
            opt3.setText(q.options[2]);
            opt4.setText(q.options[3]);
            bg.clearSelection();
        } else {
            submitExam();
        }
    }

    static void nextQuestion() {
        int selected = -1;

        if (opt1.isSelected()) selected = 0;
        if (opt2.isSelected()) selected = 1;
        if (opt3.isSelected()) selected = 2;
        if (opt4.isSelected()) selected = 3;

        if (selected == questions.get(currentQuestion).correctAnswer) {
            score++;
        }

        currentQuestion++;
        loadQuestion();
    }

    static void startTimer() {
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft);

            if (timeLeft <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(frame, "Time's Up! Auto Submitting...");
                submitExam();
            }
        });
        timer.start();
    }

    static void submitExam() {
        timer.stop();
        int total = questions.size();
        double percentage = (score * 100.0) / total;

        JOptionPane.showMessageDialog(frame,
                "Exam Submitted!\nScore: " + score + "/" + total +
                        "\nPercentage: " + String.format("%.2f", percentage) + "%");

        frame.dispose();
        showDashboard();
    }

    // ---------------- QUESTIONS ----------------

    static void initializeQuestions() {

        questions.add(new Question("1. Which language is platform independent?",
                new String[]{"C", "C++", "Java", "Python"}, 2));

        questions.add(new Question("2. Which keyword is used for inheritance?",
                new String[]{"implement", "extends", "inherits", "super"}, 1));

        questions.add(new Question("3. Entry point of Java program?",
                new String[]{"start()", "main()", "run()", "init()"}, 1));

        questions.add(new Question("4. Scanner class belongs to?",
                new String[]{"java.io", "java.util", "java.lang", "java.awt"}, 1));

        questions.add(new Question("5. Which keyword prevents inheritance?",
                new String[]{"static", "constant", "final", "abstract"}, 2));

        questions.add(new Question("6. Java was developed by?",
                new String[]{"Microsoft", "Sun Microsystems", "Google", "IBM"}, 1));

        questions.add(new Question("7. Comparison operator?",
                new String[]{"=", "==", "!=", "&&"}, 1));

        questions.add(new Question("8. Collection that doesn't allow duplicates?",
                new String[]{"List", "ArrayList", "Set", "Vector"}, 2));

        questions.add(new Question("9. Divide by zero throws?",
                new String[]{"IOException", "ArithmeticException", "NullPointerException", "SQLException"}, 1));

        questions.add(new Question("10. Loop executes at least once?",
                new String[]{"for", "while", "do-while", "foreach"}, 2));

        questions.add(new Question("11. Object creation keyword?",
                new String[]{"create", "new", "class", "object"}, 1));

        questions.add(new Question("12. Decimal data type?",
                new String[]{"int", "float", "char", "boolean"}, 1));

        questions.add(new Question("13. Not OOP concept?",
                new String[]{"Encapsulation", "Polymorphism", "Compilation", "Inheritance"}, 2));

        questions.add(new Question("14. Swing window class?",
                new String[]{"JButton", "JFrame", "JPanel", "JLabel"}, 1));

        questions.add(new Question("15. Exception handling keyword?",
                new String[]{"catch", "throw", "throws", "try"}, 3));
    }
}
