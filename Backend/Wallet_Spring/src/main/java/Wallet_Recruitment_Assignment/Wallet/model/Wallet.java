package Wallet_Recruitment_Assignment.Wallet.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Wallet {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter int id;
	
	@Column(nullable = false, unique=true)
	private @Getter @Setter String username;
	
	@Column(nullable = false)
	private @Getter @Setter String password;
	
	private @Getter @Setter double balance;
	
	private @Getter @Setter List<String> history;
	
    public Wallet(String username, String password)
    {
    	this.username=username;
    	this.password=password;
    	this.balance=0;
    	this.history = new ArrayList<>();
    }
}
