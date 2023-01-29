package Wallet_Recruitment_Assignment.Wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import Wallet_Recruitment_Assignment.Wallet.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findById(int id);
    Wallet findByUsernameAndPassword(String username, String password);
    Wallet findByUsername(String username);
    @Transactional
    void deleteById(int id);
}

