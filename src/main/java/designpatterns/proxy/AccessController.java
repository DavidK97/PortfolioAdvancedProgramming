package designpatterns.proxy;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

public class AccessController {

    public static void invokeIfAuthorized(Object service, User user, String methodName) {
        try {
            Method method = service.getClass().getMethod(methodName);
            Role roleAnnotation = method.getAnnotation(Role.class);
            Log logAnnotation = method.getAnnotation(Log.class);

            if (roleAnnotation == null) {
                System.out.println("Metoden '" + methodName + "' kræver ingen rolle – kaldes.");
                method.invoke(service);
                return;
            }

            boolean accessGranted = false;

            if (roleAnnotation == null || user.getRole().equals(roleAnnotation.value())) {
                accessGranted = true;
            }

            if (logAnnotation != null) {
                System.out.println("Delete logged");
                logDelete(logAnnotation.fileName(), user.getName(), accessGranted);
            }

            String requiredRole = roleAnnotation.value();
            if (user.getRole().equals(requiredRole)) {
                System.out.println("Adgang givet til '" + methodName + "' for bruger '" + user.getName() + "'");
                method.invoke(service);
            } else {
                System.out.println("Adgang nægtet til '" + methodName + "' for bruger '" + user.getName() + "'");
            }

        } catch (NoSuchMethodException e) {
            System.out.println("Metoden '" + methodName + "' findes ikke.");
        } catch (Exception e) {
            System.out.println("Fejl under metodekald: " + e.getMessage());
        }
    }


    private static void logDelete (String fileName, String userName, boolean callAllowed) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            String entry = String.valueOf(LocalDateTime.now() + "\n");
            entry += userName + "\n";
            if (callAllowed) {
                entry += "Adgang givet\n";
            } else {
                entry += "Adgang nægtet\n";
            }
            writer.write(entry);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
