package quarkus;

import java.math.BigDecimal;

public class CreateAccountRequestBody {
    public Long customerNumber;
    public String customerName;
    public BigDecimal balance;

    public CreateAccountRequestBody(Long customerNumber, String customerName, BigDecimal balance) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.balance = balance;
    }
}
