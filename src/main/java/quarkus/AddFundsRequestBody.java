package quarkus;

import java.math.BigDecimal;

public class AddFundsRequestBody {
    public BigDecimal funds;

    public AddFundsRequestBody(BigDecimal funds) {
        this.funds = funds;
    }
}
