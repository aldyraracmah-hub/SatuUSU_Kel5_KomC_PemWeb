public class LoginChecker {

    // Representasi akun yang sedang aktif/tersimpan
    static class Account {
        String username;
        String password;

        Account(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    // Akun yang lagi tersimpan (anggap ini datang dari database/penyimpanan lain)
    private Account savedAccount;

    public LoginChecker(Account savedAccount) {
        this.savedAccount = savedAccount;
    }

    public void login(String username, String password) {
        boolean usernameMatches = username.equals(savedAccount.username);
        boolean passwordMatches = password.equals(savedAccount.password);

        if (usernameMatches && passwordMatches) {
            // berhasil login
            System.out.println("Login berhasil! Selamat datang, " + username);

        } else if (!usernameMatches && !passwordMatches) {
            // keduanya beda -> simpan sebagai akun baru, langsung masuk
            saveAccount(username, password);
            System.out.println("Akun baru disimpan dan langsung login: " + username);

        } else if (!usernameMatches) {
            // hanya username yang beda
            System.out.println("Username salah. Silakan masukkan ulang.");

        } else {
            // hanya password yang beda
            System.out.println("Password salah. Silakan masukkan ulang.");
        }
    }

    private void saveAccount(String username, String password) {
        this.savedAccount = new Account(username, password);
        // Di aplikasi nyata: simpan ke database / file / SharedPreferences, dll.
    }

    public static void main(String[] args) {
        LoginChecker checker = new LoginChecker(new Account("251401063", "usu12345"));

        checker.login("251401063", "usu12345"); // berhasil
        checker.login("251401063", "salahpw");  // password salah
        checker.login("nimbaru", "pwbaru");     // akun baru disimpan & login
    }
}