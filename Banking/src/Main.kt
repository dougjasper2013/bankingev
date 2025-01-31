//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var chequingAccount1 = ChequingAccount("1111111",
        "C1001", 200.00, 100.00)

    chequingAccount1.deposit(50.00)
    chequingAccount1.withdraw(100.00)

    chequingAccount1.withdraw(500.00)
    chequingAccount1.deposit(50.00)

    println(chequingAccount1.toString())

    var chequingAccount2 = ChequingAccount("1111112",
        "C1002")
    println(chequingAccount2.toString())

    var chequingAccount3 = ChequingAccount("1111113",
        "C1003", 500.00)
    println(chequingAccount3.toString())

    var savingsAccount1 = SavingsAccount("1111114",
        "C1003", 500.00, false)
    println(savingsAccount1.toString())
    savingsAccount1.deposit(500.00)
    savingsAccount1.payBill(100.00)
    savingsAccount1.withdraw(200.00)
    println(savingsAccount1.toString())
    savingsAccount1.taxFreeStatus = true
    println(savingsAccount1.toString())

}

abstract class BankAccount(
    val accountNumber: String,
    val clientNumber: String,
    var balance: Double = 0.0
)
{
    open fun deposit(amount: Double)
    {
       balance += amount
       println("After a deposit of $amount the new balance is $balance")
    }

    open fun withdraw(amount: Double)
    {
        if (amount <= balance) {
            balance -= amount
            println("After a withdrawal of $amount the new balance is $balance")
        }
        else
        {
            println("Withdrawal amount of $amount exceeds available funds.")
        }
    }

    override fun toString(): String
    {
        return "Account #: $accountNumber  Client #: $clientNumber  Balance: $%.2f".format(balance)
    }

}

open class ChequingAccount(
    accountNumber: String,
    clientNumber: String,
    balance: Double = 0.0,
    var overdraftLimit: Double = 0.0
) : BankAccount(accountNumber, clientNumber, balance)
{
    override fun withdraw(amount: Double)
    {
        if (amount <= balance + overdraftLimit) {
            balance -= amount
            println("After a withdrawal of $amount the new balance is $balance")
        }
        else
        {
            println("Withdrawal amount of $amount exceeds available funds.")
        }
    }

    open fun payBill(amount: Double)
    {
        if (amount <= balance + overdraftLimit) {
            balance -= amount
            println("After a bill payment of $amount the new balance is $balance")
        }
        else
        {
            println("Bill payment amount of $amount exceeds available funds.")
        }
    }

    override fun toString(): String
    {
        return super.toString() + " Overdraft Limit: $%.2f".format(overdraftLimit)
    }

}


open class SavingsAccount(
    accountNumber: String,
    clientNumber: String,
    balance: Double = 0.0,
    var taxFreeStatus: Boolean = false
) : BankAccount(accountNumber, clientNumber, balance)
{
    open fun payBill(amount: Double)
    {
        if (amount <= balance) {
            balance -= amount
            println("After a bill payment of $amount the new balance is $balance")
        }
        else
        {
            println("Bill payment amount of $amount exceeds available funds.")
        }
    }

    override fun toString(): String
    {
        var status: String
        if(taxFreeStatus == true)
        {
            status = "  Tax Free: active"
        }
        else
        {
            status = "  Tax Free: inactive"
        }
        return super.toString() + status
    }
}
