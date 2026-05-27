package designpatterns.proxy;

public class Main {
    public static void main(String[] args) {
        User admin = new User("admin", "admin");
        User normal = new User("normal", "user");
        RealSecureService service = new RealSecureService();


        // Efter proxy design pattern
        ProxyService adminProxy = new SecureServiceProxy(service, admin);
        adminProxy.deleteAllUsers();
        adminProxy.help();
        adminProxy.viewProfile();

        ProxyService normalProxy = new SecureServiceProxy(service, normal);
        normalProxy.deleteAllUsers();
        normalProxy.help();
        normalProxy.viewProfile();

        // Før Proxy design pattern
        /*
        AccessController.invokeIfAuthorized(service, admin, "deleteAllUsers");
        AccessController.invokeIfAuthorized(service, normal, "deleteAllUsers");
        AccessController.invokeIfAuthorized(service, normal, "viewProfile");
        AccessController.invokeIfAuthorized(service, normal, "help");

         */
    }
}
