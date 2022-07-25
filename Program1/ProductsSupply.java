package Program1;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;


public class ProductsSupply extends JFrame {

	private JPanel _4535content_;
	private JTextField _54t23ext_p1;
	private JTextField _54t23ext_p2;
	private JTextField _54t23ext_p3;
	private JTextField _54t23ext_r1;
	private JTextField _54t23ext_r2;
	private JTextField _54t23ext_r3;
	private JTextArea textArea_log;

	
	class BufferForProducerConsumer {

		private int buffSize = 30;

		Buff _spaces = new Buff(buffSize);
		Buff _elements = new Buff(0);

		private int store[] = new int[buffSize];
		private int store_num[] = new int[buffSize];
		private int in = 0;
		private int out = 0;


		public void ProductDeposit(int _sdfsval_,int _num) {
			_spaces.decrease();
			store_num[in] = _num;
			store[in] = _sdfsval_;
			in = (in + 1) % buffSize;
			_elements.increase();
		}

		public int ProductFetch(int _num) {
			int _sdfsval_;
			if(store_num[out] == _num) {
				_elements.decrease();
				_sdfsval_ = store[out];
				out = (out + 1) % buffSize;
				_spaces.increase();
				return _sdfsval_;
			}
			return -1;
		}
	}
	
	class Buff {

		private int _sdfsval_ = 0;

		public Buff(int init) {
			_sdfsval_ = init;
		}

		public synchronized void decrease() {
			_sdfsval_ -= 1;
			if (_sdfsval_ < 0){
				try {
					wait();
				} catch (Exception e) {
				}
			}
		}

		public synchronized void increase() {
			_sdfsval_ += 1;
			if (_sdfsval_ <= 0){
				notify();
			}
		}
	}

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductsSupply _frame = new ProductsSupply();
					_frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("Can't launch program!");
				}
			}
		});
	}


	public ProductsSupply() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(101, 101, 655, 351);
		_4535content_ = new JPanel();
		_4535content_.setBorder(new EmptyBorder(6, 6, 6, 6));
		
		_4535content_.setLayout(null);
		setContentPane(_4535content_);
		
		JLabel _234234Label_ = new JLabel("ProductsForCity1");
		_234234Label_.setBounds(10, 47, 100, 14);
		_4535content_.add(_234234Label_);
		
		_54t23ext_p1 = new JTextField();
		_54t23ext_p1.setText("30");
		_54t23ext_p1.setBounds(120, 41, 86, 20);
		_4535content_.add(_54t23ext_p1);
		_54t23ext_p1.setColumns(10);
		
		JLabel lblProductsforcity = new JLabel("ProductsForCity2");
		lblProductsforcity.setBounds(10, 100, 100, 14);
		_4535content_.add(lblProductsforcity);
		
		_54t23ext_p2 = new JTextField();
		_54t23ext_p2.setText("50");
		_54t23ext_p2.setColumns(10);
		_54t23ext_p2.setBounds(120, 94, 86, 20);
		_4535content_.add(_54t23ext_p2);
		
		JLabel lblProductsforcity_sdfs = new JLabel("ProductsForCity3");
		lblProductsforcity_sdfs.setBounds(10, 154, 100, 14);
		_4535content_.add(lblProductsforcity_sdfs);
		
		_54t23ext_p3 = new JTextField();
		_54t23ext_p3.setText("70");
		_54t23ext_p3.setColumns(10);
		_54t23ext_p3.setBounds(120, 148, 86, 20);
		_4535content_.add(_54t23ext_p3);
		
		JScrollPane _scrollPane = new JScrollPane();
		_scrollPane.setBounds(224, 41, 171, 207);
		_4535content_.add(_scrollPane);
		
		textArea_log = new JTextArea();
		_scrollPane.setViewportView(textArea_log);
		
		JLabel __234234Label__1 = new JLabel("ReceivedNumInCity1");
		__234234Label__1.setBounds(405, 44, 120, 14);
		_4535content_.add(__234234Label__1);
		
		_54t23ext_r1 = new JTextField();
		_54t23ext_r1.setText("0");
		_54t23ext_r1.setColumns(10);
		_54t23ext_r1.setBounds(535, 41, 86, 20);
		_4535content_.add(_54t23ext_r1);
		
		_54t23ext_r2 = new JTextField();
		_54t23ext_r2.setText("0");
		_54t23ext_r2.setColumns(10);
		_54t23ext_r2.setBounds(535, 94, 86, 20);
		_4535content_.add(_54t23ext_r2);
		
		_54t23ext_r3 = new JTextField();
		_54t23ext_r3.setText("0");
		_54t23ext_r3.setColumns(10);
		_54t23ext_r3.setBounds(535, 148, 86, 20);
		_4535content_.add(_54t23ext_r3);
		
		JButton _sfer2323button_sdfqw = new JButton("Transfer");
		_sfer2323button_sdfqw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		_sfer2323button_sdfqw.setBounds(225, 277, 170, 23);
		_4535content_.add(_sfer2323button_sdfqw);
		
		JLabel __234234Label__1_1 = new JLabel("ReceivedNumInCity2");
		__234234Label__1_1.setBounds(405, 100, 120, 14);
		_4535content_.add(__234234Label__1_1);
		
		JLabel __234234Label__1_2 = new JLabel("ReceivedNumInCity3");
		__234234Label__1_2.setBounds(405, 154, 120, 14);
		_4535content_.add(__234234Label__1_2);
	}
	
	private void start() {
		BufferForProducerConsumer buf = new BufferForProducerConsumer();

		Producer _P123 = new Producer(buf,1,Integer.parseInt(_54t23ext_p1.getText()));
		Producer _P223 = new Producer(buf,2,Integer.parseInt(_54t23ext_p2.getText()));
		Producer _P323 = new Producer(buf,3,Integer.parseInt(_54t23ext_p3.getText()));
		Consumer _C123 = new Consumer(buf,1,Integer.parseInt(_54t23ext_p1.getText()));
		Consumer _C223 = new Consumer(buf,2,Integer.parseInt(_54t23ext_p2.getText()));
		Consumer _C323 = new Consumer(buf,3,Integer.parseInt(_54t23ext_p3.getText()));

		_P123.start();
		_P223.start();
		_C123.start();
		_C223.start();
		_P323.start();
		_C323.start();
	}
	
	
	
	class Producer extends Thread {
		BufferForProducerConsumer buf;
		int _num = 0;
		int _sdwer_ewr = 0;
		
		public Producer(BufferForProducerConsumer buf,int _sdwer_ewr, int _num) {
			this.buf = buf;
			this._sdwer_ewr = _sdwer_ewr;
			this._num = _num;
		}

		public void run() {
			for (int ii = 1; ii <= _num; ii++) {
				buf.ProductDeposit(ii, _sdwer_ewr);
				try {
					sleep(50);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	class Consumer extends Thread {
		BufferForProducerConsumer buf;
		int _sdwer_ewr;
		int _num;
		
		public Consumer(BufferForProducerConsumer buf,int _sdwer_ewr,int _num) {
			this.buf = buf;
			this._sdwer_ewr = _sdwer_ewr;
			this._num = _num;
		}

		public void run() {
			for (int ii = 1; ii <= _num; ii++) {
				int num = buf.ProductFetch(_sdwer_ewr);
				if(num == -1) {
					ii --;
				}else {
					if(_sdwer_ewr == 1) _54t23ext_r1.setText(String.format("%d",(Integer.parseInt(_54t23ext_r1.getText()) + 1)));
					if(_sdwer_ewr == 2) _54t23ext_r2.setText(String.format("%d",(Integer.parseInt(_54t23ext_r2.getText()) + 1)));
					if(_sdwer_ewr == 3) _54t23ext_r3.setText(String.format("%d",(Integer.parseInt(_54t23ext_r3.getText()) + 1)));
					
					System.out.println( "City : " + _sdwer_ewr + " , product :" + num + "\n" );
					String text =  "City : " + _sdwer_ewr + " , product :" + num + "\n";
					textArea_log.append(text);
				}
				try {
					sleep(50);
				} catch (InterruptedException e) {
				}
			}
				
		}
	}

	
}
