package Booking;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Library extends JFrame {

	private JPanel _myContentPane;
	private JTextField _234textField_book1;
	private JTextField _234textField_book2;
	private JButton _btnStart123;
	private JTextArea _123textArea_bp1;
	private JTextArea _123textArea_bp2;
	private JTextArea _123textArea_rp1;
	private JTextArea _123textArea_rp2;
	private JTextArea _s23text_Area;
	
	private BorrowThread th1;
	private BorrowThread th2;
	
	private ReturnThread thr1;
	private ReturnThread thr2;
	
	final AtomicInteger _counter = new AtomicInteger(0);


	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Library _frame = new Library();
					_frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("can't launch the program!!");
				}
			}
		});
	}


	public Library() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(111, 112, 559, 377);
		_myContentPane = new JPanel();
		_myContentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
		_myContentPane.setLayout(null);
		setContentPane(_myContentPane);
		
		
		JLabel _Label = new JLabel("Book1:");
		_Label.setBounds(10, 34, 46, 14);
		_myContentPane.add(_Label);
		
		_234textField_book1 = new JTextField();
		_234textField_book1.setHorizontalAlignment(SwingConstants.LEFT);
		_234textField_book1.setText("300");
		_234textField_book1.setBounds(66, 31, 86, 20);
		_myContentPane.add(_234textField_book1);
		_234textField_book1.setColumns(11);
		
		JLabel lblBook = new JLabel("Book2:");
		lblBook.setBounds(10, 62, 46, 14);
		_myContentPane.add(lblBook);
		
		_234textField_book2 = new JTextField();
		_234textField_book2.setText("1000");
		_234textField_book2.setColumns(11);
		_234textField_book2.setBounds(66, 59, 86, 20);
		_myContentPane.add(_234textField_book2);
		
		JLabel _Label_1;
		_Label_1 = new JLabel("Books in library");
		_Label_1.setBounds(10, 9, 103, 14);
		_myContentPane.add(_Label_1);
		
		JScrollPane _234scroll = new JScrollPane();
		_234scroll.setBounds(393, 34, 140, 279);
		_myContentPane.add(_234scroll);
		
		 _s23text_Area = new JTextArea();
		_234scroll.setViewportView(_s23text_Area);
		
		JLabel _Label_2 = new JLabel("BorrowPlace1:");
		_Label_2.setBounds(10, 124, 86, 14);
		_myContentPane.add(_Label_2);
		
		JScrollPane _234scroll_1 = new JScrollPane();
		_234scroll_1.setBounds(10, 146, 86, 168);
		_myContentPane.add(_234scroll_1);
		
		 _123textArea_bp1 = new JTextArea();
		_234scroll_1.setViewportView(_123textArea_bp1);
		
		JLabel _Label_2_1 = new JLabel("BorrowPlace2:");
		_Label_2_1.setBounds(106, 124, 85, 14);
		_myContentPane.add(_Label_2_1);
		 
		 JScrollPane _234scroll_2 = new JScrollPane();
		 _234scroll_2.setBounds(107, 147, 84, 166);
		 _myContentPane.add(_234scroll_2);
		
		 _123textArea_bp2 = new JTextArea();
		 _234scroll_2.setViewportView(_123textArea_bp2);
		
		JLabel _Label_2_2 = new JLabel("ReturnPlace1:");
		_Label_2_2.setBounds(201, 124, 85, 14);
		_myContentPane.add(_Label_2_2);
		 
		 JScrollPane _234scroll_3 = new JScrollPane();
		 _234scroll_3.setBounds(202, 147, 84, 166);
		 _myContentPane.add(_234scroll_3);
		
		 _123textArea_rp1 = new JTextArea();
		 _234scroll_3.setViewportView(_123textArea_rp1);
		
		JLabel _Label_2_3 = new JLabel("ReturnPlace2:");
		_Label_2_3.setBounds(298, 124, 85, 14);
		_myContentPane.add(_Label_2_3);
		 
		 JScrollPane _234scroll_4 = new JScrollPane();
		 _234scroll_4.setBounds(299, 147, 84, 166);
		 _myContentPane.add(_234scroll_4);
		
		 _123textArea_rp2 = new JTextArea();
		 _234scroll_4.setViewportView(_123textArea_rp2);
		
		_btnStart123 = new JButton("Start");
		_btnStart123.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		_btnStart123.setBounds(201, 30, 103, 46);
		_myContentPane.add(_btnStart123);
	}


	class Book {
        int bookCount = 0;
        
        public Book(int _count) {
        	this.bookCount = _count;
        }
        
        public synchronized void returnBook(int _count) { 
        	bookCount += _count; 
        }
        
        public synchronized void borrowBook(int _count) { 
        	if(bookCount >= _count) {
        		bookCount -= _count;
        	}
        }
        
        public synchronized int get() { return bookCount; }
    }
	
	class BorrowThread extends Thread {
		Book book1;
		Book book2;
		
		public BorrowThread(Book book1, Book book2) {
			this.book1 = book1;
			this.book2 = book2;
		}
		
        public void run() {
        	while(true) {
        		int randNum = getRandom(2);
        		int borrowNum = getRandom(5);
        		if(randNum == 0) {
        			book1.borrowBook(borrowNum);
        			_234textField_book1.setText(String.format("%d",book1.get()));
        			_123textArea_bp1.append("Borrow:" + borrowNum + "\n");
        			_s23text_Area.append("Borrow Book1:" + borrowNum + "\n");
        			//System.out.println("Book1 b:" + book1.get());
        		}
        		else {
        			book2.borrowBook(borrowNum);
        			_234textField_book2.setText(String.format("%d",book2.get()));
        			_123textArea_bp2.append("Borrow:" + borrowNum + "\n");
        			_s23text_Area.append("Borrow Book2:" + borrowNum + "\n");
        			//System.out.println("Book2 b:" + book2.get());
        		}
        		try {
					sleep(50);
				} catch (InterruptedException e) {
				}
        	}
        }
        
        private int getRandom(int nn) {
			double val = nn * Math.random();
    		return (int) Math.round(val - 0.5);
    	}
    }
	
	class ReturnThread extends Thread {
		Book book1;
		Book book2;
		
		public ReturnThread(Book book1, Book book2) {
			this.book1 = book1;
			this.book2 = book2;
		}
		
        public void run() {
        	while(true) {
        		int randNum = getRandom(2);
        		int returnNum = getRandom(5);
        		if(randNum == 0) {
        			book1.returnBook(returnNum);
        			_234textField_book1.setText(String.format("%d",book1.get()));
        			_123textArea_rp1.append("Return:" + returnNum + "\n");
        			_s23text_Area.append("Return Book1:" + returnNum + "\n");
        		}
        		else {
        			book2.returnBook(returnNum);
        			_234textField_book2.setText(String.format("%d",book2.get()));
        			_123textArea_rp2.append("Return:" + returnNum + "\n");
        			_s23text_Area.append("Return Book1:" + returnNum + "\n");
        		}

        		try {
					sleep(50);
				} catch (InterruptedException e) {
				}
        	}
        }
        
		private int getRandom(int nn) {
			double val = nn * Math.random();
    		return (int) Math.round(val - 0.5);
    	}
    }
	
	
	private void start() {
		if(_btnStart123.getText() == "Start") {
			_btnStart123.setText("Stop");
			_234textField_book1.setEditable(false);
			_234textField_book2.setEditable(false);
			
			Book book1 = new Book(Integer.parseInt(_234textField_book1.getText()));
			Book book2 = new Book(Integer.parseInt(_234textField_book2.getText()));
			
			th1 = new BorrowThread(book1,book2);
			thr1 = new ReturnThread(book1, book2);
			
			th1.start();
			thr1.start();
		}
		else {
			_btnStart123.setText("Start");
			_234textField_book1.setEditable(true);
			_234textField_book2.setEditable(true);
			th1.stop();
			thr1.stop();
		}
	}
}
