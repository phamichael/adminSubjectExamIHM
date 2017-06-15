package facade.securite;

import facade.erreurs.AccesRefuseException;
import modele.personnes.Utilisateur;

import java.lang.reflect.Method;

public class AccessControl {
    public static void checkAnnotationAccess(Method method, Utilisateur utilisateur) {
        Class clazz = method.getDeclaringClass();
        for(Class ifc : clazz.getInterfaces()) {
            try {
                Method ifcMethod = ifc.getMethod(method.getName(),method.getParameterTypes());
                if (ifcMethod.isAnnotationPresent(HasRole.class))
                    verifyAccessAllowed(utilisateur, ifcMethod.getDeclaredAnnotation(HasRole.class).value());
            } catch (NoSuchMethodException e1) {
            }
        }
    }

    private static void verifyAccessAllowed(Utilisateur utilisateur, String[] rolesAllowed) {
        for(String role : rolesAllowed) {
            if (utilisateur.hasRole(role)) return;
        }
        throw new AccesRefuseException();
    }

}
