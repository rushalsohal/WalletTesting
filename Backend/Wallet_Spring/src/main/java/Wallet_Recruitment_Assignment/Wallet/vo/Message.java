package Wallet_Recruitment_Assignment.Wallet.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Message {
	private @Getter @Setter String message;
    private @Getter @Setter int errorCode; //0-no error; 1-error
}
