package Wallet_Recruitment_Assignment.Wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Wallet_Recruitment_Assignment.Wallet.model.Wallet;
import Wallet_Recruitment_Assignment.Wallet.service.WalletService;
import Wallet_Recruitment_Assignment.Wallet.vo.LoginVO;
import Wallet_Recruitment_Assignment.Wallet.vo.Message;

@RestController
@RequestMapping("/wallet")
@CrossOrigin
public class WalletController {
	@Autowired
	WalletService walletService;
	
    //POST
    //create a wallet for a user; 0.0 initial balance
    @PostMapping(value = "/register", produces="application/json")
    public @ResponseBody Message registerActor(@RequestBody Wallet wallet){
        return walletService.registerUser(wallet.getUsername(), wallet.getPassword());
    }
    
	
    //verify user
    @PostMapping(value = "/login", produces="application/json")
    public Message getWallet(@RequestBody LoginVO loginVO){
        if (walletService.validateUser(loginVO) == null) return new Message("Invalid credentials", 1);
        return new Message("Login successful", 0);
    }
    
    //GET
    //get all wallets
    @GetMapping(value = "/getAll", produces="application/json")
    public @ResponseBody List<Wallet> getAllUsers(){
        return walletService.getAllWallets();
    }
    
	//check the balance of the user wallet
    @GetMapping(value = "/balance", produces="application/json")
    public @ResponseBody String getBalance(@RequestParam String username){
        return walletService.checkBalance(username);
    }
    
    //get transaction history
    @GetMapping(value = "/history", produces="application/json")
    public @ResponseBody List<String> getTransactionHistory(@RequestParam String username){
        return walletService.getHistory(username);
    }
    
    //PUT
    //add amount
    @PutMapping(value = "/add/{amount}", produces = "application/json")
    public @ResponseBody Message addAmountToWallet(@RequestParam String username, @PathVariable double amount){
        return walletService.addAmount(username, amount);
    }
    
    //withdraw amount
    @PutMapping(value = "/withdraw/{amount}", produces = "application/json")
    public @ResponseBody Message withdrawAmountFromWallet(@RequestParam String username, @PathVariable double amount){
        return walletService.withdrawAmount(username, amount);
    }
    
    //DELETE
    //specific actor
    @DeleteMapping(value = "/delete", produces = "application/json")
    public @ResponseBody Message deleteActor(@RequestParam String username) {
    	return walletService.deleteWallet(username);
    }
}
