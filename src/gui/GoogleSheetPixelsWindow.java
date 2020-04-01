package gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GoogleSheetPixelsWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	BufferedImage pixelArt;
	JButton startDrawButton;

	public GoogleSheetPixelsWindow() {
		configWindow();
	}
	
	public static void main(String[] args) {
		JFrame googleSheetPixels = new GoogleSheetPixelsWindow();
		googleSheetPixels.setLocationRelativeTo(null);
		googleSheetPixels.setVisible(true);
	}
	
	private void configWindow() {
		this.setTitle("Pixels");
		this.setSize(196, 130);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setUndecorated(true);
		this.setResizable(false);
		addComponents();
	}
	
	private void addComponents() {
		JPanel centerPanel = new JPanel();
		centerPanel.setBounds(4, 4, 190, 130);
		centerPanel.setLayout(null);
		
		JButton loadPixelArtButton = new JButton();
		loadPixelArtButton.setText("Open Pixel Art");
		loadPixelArtButton.setBounds(4, 4, 180, 80);
		loadPixelArtButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadPixelArt("");
			}
		});
		
		startDrawButton = new JButton();
		startDrawButton.setText("Open Pixel Art");
		startDrawButton.setBounds(4, 92, 48, 24);
		startDrawButton.setEnabled(false);;
		
		JProgressBar jProgressBar = new JProgressBar();
		jProgressBar.setBounds(56, 92, 128, 24);
		jProgressBar.setValue(10);
		jProgressBar.setMaximum(100);
		
		centerPanel.add(loadPixelArtButton);
		centerPanel.add(startDrawButton);
		centerPanel.add(jProgressBar);
		
		this.getContentPane().add(centerPanel);
		
		final JFrame self = this;
		KeyListener keyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) self.dispose();
			}
		};
		loadPixelArtButton.addKeyListener(keyListener);
		startDrawButton.addKeyListener(keyListener);
	}
	
	private BufferedImage loadPixelArt(String path) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return "PNG image";
			}
			
			@Override
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith("png");
			}
		});
		
		int result = fileChooser.showOpenDialog(this);
		File selectedFile = null;
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile();
		}
		
		BufferedImage pixelArt = null;
		try {
			if(selectedFile != null
					&& selectedFile.getAbsoluteFile() != null) {
				pixelArt = ImageIO.read(selectedFile.getAbsoluteFile());
				startDrawButton.setEnabled(true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return pixelArt;
	}
}
