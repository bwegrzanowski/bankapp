package sda;

import sda.model.BankAccount;
import sda.model.User;
import sda.repository.BankAccountRepository;
import sda.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Map;

public class App {
    public static void main(String[] args) {

//        User user = new User();
//        user.setFirstName("Jan");
//        user.setLastName("Kowalski");
//
//        BankAccount bankAccount = new BankAccount();
//        bankAccount.setAccountNumber("84000011112222333344445555");
//        bankAccount.setCurrency("PLN");
//        user.setAccount(bankAccount);
//
//        UserRepository.saveUser(user);
//

        BankAccount bankAccount = BankAccount.builder()
                .accountNumber("84000011112222333344445555")
                .availableMoney(new BigDecimal(100))
                .balance(new BigDecimal(100))
                .currency("PLN")
                .build();
        BankAccountRepository.saveAccount(bankAccount);

        User user = User.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .account(bankAccount)
                .email("kowalski@wp.pl")
                .password("kowalski")
                .build();
        UserRepository.saveUser(user);

    }
}
