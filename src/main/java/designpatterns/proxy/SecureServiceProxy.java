package designpatterns.proxy;

public class SecureServiceProxy implements ProxyService{
    private RealSecureService realService;
    private User currentUser;

    public SecureServiceProxy(RealSecureService realService, User currentUser) {
        this.realService = realService;
        this.currentUser = currentUser;
    }

    @Override
    public void deleteAllUsers() {
        AccessController.invokeIfAuthorized(realService, currentUser, "deleteAllUsers");
    }

    @Override
    public void help() {
        AccessController.invokeIfAuthorized(realService, currentUser, "viewProfile");
    }

    @Override
    public void viewProfile() {
        AccessController.invokeIfAuthorized(realService, currentUser, "help");
    }
}

