//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var chequingAccount1 = ChequingAccount("1111111",
        "C1001", 200.00, 100.00)

    chequingAccount1.deposit(50.00)
    chequingAccount1.withdraw(100.00)

    chequingAccount1.withdraw(500.00)
    chequingAccount1.deposit(50.00)
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
}
