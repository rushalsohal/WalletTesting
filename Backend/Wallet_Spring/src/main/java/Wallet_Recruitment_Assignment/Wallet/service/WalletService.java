package Wallet_Recruitment_Assignment.Wallet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Wallet_Recruitment_Assignment.Wallet.model.Wallet;
import Wallet_Recruitment_Assignment.Wallet.repository.WalletRepository;
import Wallet_Recruitment_Assignment.Wallet.vo.LoginVO;
import Wallet_Recruitment_Assignment.Wallet.vo.Message;

@Service
public class WalletService {

	@Autowired
    WalletRepository walletRepository;
	
	/***User Wallet Registration***/
    public Message registerUser(String username, String password){

        Wallet wallet = new Wallet(username, password);
        try {
        	walletRepository.save(wallet);
        }catch (Exception e){
            return new Message("ERROR: Username has to be unique", 1);
        }
        return new Message("Wallet setup successful", 0);
    }
    
    /***Validate User***/
    public Wallet validateUser(LoginVO loginVO){
        return walletRepository.findByUsernameAndPassword(loginVO.getUsername(), loginVO.getPassword());
    }
    
    /***Get all wallets***/
    public List<Wallet> getAllWallets(){
        return walletRepository.findAll();
    }

	/***Wallet Updates***/
	//add amount
    public Message addAmount(String username, double amount){
        Wallet wallet = walletRepository.findByUsername(username);
        if (wallet == null){
            return new Message("Credentials Don't Exist", 1);
        }
        if(amount < 0) {
        	return new Message("Enter a non-negative value", 1);
        }
        double newBalance = wallet.getBalance() + amount;
        wallet.setBalance(newBalance);
        List<String> tmp = wallet.getHistory();
        tmp.add("+" + amount);
        wallet.setHistory(tmp);
        walletRepository.save(wallet);
        return new Message("Amount added successfully", 0);
    }
    
    //withdraw amount
    public Message withdrawAmount(String username, double amount){
    	Wallet wallet = walletRepository.findByUsername(username);
        if (wallet == null){
            return new Message("Credentials Don't Exist", 1);
        }
        if(amount < 0) {
        	return new Message("Enter a non-negative value", 1);
        }
        double currBalance = wallet.getBalance(); 
        if(amount > currBalance) {
        	return new Message("Insufficient balance", 1);
        }
        wallet.setBalance(currBalance-amount);
        List<String> tmp = wallet.getHistory();
        tmp.add("-" + amount);
        wallet.setHistory(tmp);
        walletRepository.save(wallet);
        return new Message("Amount withdrawn successfully", 0);
    }
    
    //check balance
    public String checkBalance(String username){
    	Wallet wallet = walletRepository.findByUsername(username);
        if (wallet == null){
            return "Credentials Don't Exist";
        }
        return ""+wallet.getBalance();
    }

    //show transaction history
    public List<String> getHistory(String username){
    Wallet wallet = walletRepository.findByUsername(username);
    if (wallet == null){
        return new ArrayList<>();
    }
        return wallet.getHistory();
    }
    
    /***Delete Wallet***/
    public Message deleteWallet(String username) {
    	Wallet wallet = walletRepository.findByUsername(username);
        if (wallet == null){
            return new Message("Credentials Don't Exist", 1);
        }
        walletRepository.delete(wallet);
        return new Message("Delete Successful", 0);
    }
}
