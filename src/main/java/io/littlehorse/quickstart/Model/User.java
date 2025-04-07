package io.littlehorse.quickstart.Model;

public class User {

    public Long userId;
    public int accountBalance;
    public Boolean dashPass;
    public String name ;

    public User(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Boolean getDashPass() {
        return dashPass;
    }

    public void setDashPass(Boolean dashPass) {
        this.dashPass = dashPass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
