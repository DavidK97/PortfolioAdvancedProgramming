package designpatterns.proxy;

public class RealSecureService implements ProxyService{


    @Role("admin")
    @Log(fileName = "delete.log")
    @Override
    public void deleteAllUsers() {
        System.out.println("Alle brugere er slettet.");
    }

    @Role("user")
    public void viewProfile() {
        System.out.println("Profil vist.");
    }

    public void help() {
        System.out.println("Hjælp åbnet.");
    }
}
