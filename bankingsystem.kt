import kotlin.system.exitProcess

// Define sealed class for TransactionResult
sealed class TransactionResult {
    class Success(val message: String) : TransactionResult()
    class Failure(val errorMessage: String) : TransactionResult()
}

// Define BankAccount class
data class BankAccount(
    val accountNumber: String,
    var accountHolderName: String?,
    var balance: Double
) {
    // Deposit method
    fun deposit(amount: Double): TransactionResult {
        if (amount <= 0) {
            return TransactionResult.Failure("Deposit amount should be positive.")
        }
        balance += amount
        return TransactionResult.Success("Deposit of $amount successful. New balance: $balance")
    }

    // Withdraw method
    fun withdraw(amount: Double): TransactionResult {
        if (amount <= 0) {
            return TransactionResult.Failure("Withdrawal amount should be positive.")
        }
        if (balance - amount < 0) {
            return TransactionResult.Failure("Insufficient balance.")
        }
        balance -= amount
        return TransactionResult.Success("Withdrawal of $amount successful. New balance: $balance")
    }

    // Display account info
    fun displayAccountInfo() {
        val name = accountHolderName ?: "Unknown"
        println("Account Number: $accountNumber, Account Holder Name: $name, Balance: $balance")
    }
}

fun main() {
    val accounts = mutableListOf<BankAccount>()

    // Interactive menu loop
    while (true) {
        println("\nBanking System Menu:")
        println("1. Create Account")
        println("2. Display Account Info")
        println("3. Deposit")
        println("4. Withdraw")
        println("5. Quit")

        print("Enter your choice: ")
        val choice = readLine()?.toIntOrNull()

        when (choice) {
            1 -> {
                println("\nEnter Account Details:")
                print("Account Number: ")
                val accountNumber = readLine() ?: ""
                print("Account Holder Name: ")
                val accountHolderName = readLine()
                print("Initial Balance: ")
                val initialBalance = readLine()?.toDoubleOrNull() ?: 0.0

                val newAccount = BankAccount(accountNumber, accountHolderName, initialBalance)
                accounts.add(newAccount)
                println("\nAccount created successfully!")
            }
            2 -> {
                if (accounts.isEmpty()) {
                    println("\nNo accounts found.")
                } else {
                    print("\nEnter Account Number to Display Info: ")
                    val accountNumber = readLine() ?: ""
                    val account = accounts.find { it.accountNumber == accountNumber }
                    if (account != null) {
                        account.displayAccountInfo()
                    } else {
                        println("Account not found.")
                    }
                }
            }
            3 -> {
                if (accounts.isEmpty()) {
                    println("\nNo accounts found.")
                } else {
                    print("\nEnter Account Number to Deposit: ")
                    val accountNumber = readLine() ?: ""
                    val account = accounts.find { it.accountNumber == accountNumber }
                    if (account != null) {
                        print("Enter Deposit Amount: ")
                        val amount = readLine()?.toDoubleOrNull() ?: 0.0
                        val result = account.deposit(amount)
                        when (result) {
                            is TransactionResult.Success -> println((result as TransactionResult.Success).message)
                            is TransactionResult.Failure -> println((result as TransactionResult.Failure).errorMessage)
                        }
                    } else {
                        println("Account not found.")
                    }
                }
            }
            4 -> {
                if (accounts.isEmpty()) {
                    println("\nNo accounts found.")
                } else {
                    print("\nEnter Account Number to Withdraw: ")
                    val accountNumber = readLine() ?: ""
                    val account = accounts.find { it.accountNumber == accountNumber }
                    if (account != null) {
                        print("Enter Withdrawal Amount: ")
                        val amount = readLine()?.toDoubleOrNull() ?: 0.0
                        val result = account.withdraw(amount)
                        when (result) {
                            is TransactionResult.Success -> println((result as TransactionResult.Success).message)
                            is TransactionResult.Failure -> println((result as TransactionResult.Failure).errorMessage)
                        }

                    } else {
                        println("Account not found.")
                    }
                }
            }
            5 -> {
                println("\nExiting...")
                exitProcess(0)
            }
            else -> {
                println("\nInvalid choice. Please enter a number between 1 and 5.")
            }
        }
    }
}

