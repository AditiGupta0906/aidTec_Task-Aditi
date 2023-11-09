import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String address;
    private String phoneNumber;

    public Student(String name, int rollNumber, String address, String phoneNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Address: " + address + ", Phone Number: " + phoneNumber;
    }
}

class StudentRecord implements Serializable {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student getStudentByRollNumber(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StudentRecord loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (StudentRecord) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class StudentRecordGUI extends JFrame {
    private StudentRecord studentRecord;
    private JTextField nameField, rollNumberField, addressField, phoneField;

    public StudentRecordGUI() {
        studentRecord = StudentRecord.loadFromFile("studentRecord.txt");
        if (studentRecord == null) {
            studentRecord = new StudentRecord();
        }

        setTitle("Student Record System");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI();
    }

    private void createUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Roll Number:"));
        rollNumberField = new JTextField();
        panel.add(rollNumberField);

        panel.add(new JLabel("Address:"));
        addressField = new JTextField();
        panel.add(addressField);

        panel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        panel.add(addButton);

        JButton removeButton = new JButton("Remove Student");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });
        panel.add(removeButton);

        JButton displayButton = new JButton("Display Student");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStudent();
            }
        });
        panel.add(displayButton);

        JButton saveButton = new JButton("Save Records");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRecords();
            }
        });
        panel.add(saveButton);

        add(panel);
    }

    private void addStudent() {
        String name = nameField.getText();
        int rollNumber = Integer.parseInt(rollNumberField.getText());
        String address = addressField.getText();
        String phoneNumber = phoneField.getText();

        Student student = new Student(name, rollNumber, address, phoneNumber);
        studentRecord.addStudent(student);

        JOptionPane.showMessageDialog(this, "Student added successfully!");
    }

    private void removeStudent() {
        int rollNumber = Integer.parseInt(rollNumberField.getText());
        studentRecord.removeStudent(rollNumber);

        JOptionPane.showMessageDialog(this, "Student removed successfully!");
    }

    private void displayStudent() {
        int rollNumber = Integer.parseInt(rollNumberField.getText());
        Student student = studentRecord.getStudentByRollNumber(rollNumber);

        if (student != null) {
            JOptionPane.showMessageDialog(this, student.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Student not found!");
        }
    }

    private void saveRecords() {
        studentRecord.saveToFile("studentRecord.txt");
        JOptionPane.showMessageDialog(this, "Records saved successfully!");
    }
}

public class StudentRecordSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentRecordGUI().setVisible(true);
            }
        });
    }
}
