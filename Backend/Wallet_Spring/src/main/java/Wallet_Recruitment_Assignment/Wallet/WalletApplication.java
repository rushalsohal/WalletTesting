package Wallet_Recruitment_Assignment.Wallet;

//import java.awt.EventQueue;
//import java.awt.event.ActionEvent;
//
//import javax.swing.GroupLayout;
//import javax.swing.JButton;
//import javax.swing.JComponent;
//import javax.swing.JFrame;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.annotation.Bean;
//
//import Wallet_Recruitment_Assignment.Wallet.model.Wallet;
//import Wallet_Recruitment_Assignment.Wallet.repository.WalletRepository;
//
//@SpringBootApplication
//@SuppressWarnings("serial")
//public class WalletApplication extends JFrame {
//
//    public WalletApplication() {
//
//        initUI();
//    }
//
//    private void initUI() {
//
//        var quitButton = new JButton("Quit");
//
//        quitButton.addActionListener((ActionEvent event) -> {
//            System.exit(0);
//        });
//
//        createLayout(quitButton);
//
//        setTitle("Quit button");
//        setSize(300, 200);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
//
//    private void createLayout(JComponent... arg) {
//
//        var pane = getContentPane();
//        var gl = new GroupLayout(pane);
//        pane.setLayout(gl);
//
//        gl.setAutoCreateContainerGaps(true);
//
//        gl.setHorizontalGroup(gl.createSequentialGroup()
//                .addComponent(arg[0])
//        );
//
//        gl.setVerticalGroup(gl.createSequentialGroup()
//                .addComponent(arg[0])
//        );
//    }
//
//    public static void main(String[] args) {
//        var context = new SpringApplicationBuilder(WalletApplication.class)
//                .headless(false).run(args);
//
//
//        EventQueue.invokeLater(() -> {
//
//            var ex = context.getBean(WalletApplication.class);
//            ex.setVisible(true);
//        });
//    }
//    
//	@Bean
//	public CommandLineRunner loadData(WalletRepository walletRepository) {
//		return (args) -> {
//			// add a test user/wallet
//			walletRepository.save(new Wallet("test", "test"));
//		};
//	}
//}

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Wallet_Recruitment_Assignment.Wallet.model.Wallet;
import Wallet_Recruitment_Assignment.Wallet.repository.WalletRepository;

@SpringBootApplication
public class WalletApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(WalletRepository walletRepository) {
		return (args) -> {
			// add a test user/wallet
			walletRepository.save(new Wallet("test", "test"));
		};
	}
}
